
- **启动器** ：本质所有任务就是一个有向无环图，通过Systrace确定wallTime和cpuTime，然后选择合适的线程池，这里的线程池有两种（cpu定容线程池，io缓存线程池），cpuTime长的就证明他消耗cpu时间片，多线程并发的本质就是抢夺时间片，所以cpuTime长的要选择定容线程池，防止他并发时候影响cpu效率，反之选择缓存线程池，再构造任务之间的图关系，因为有些任务有先后顺序(正确使用启动速度优化30%很容易)
- **Multdex** ：5.0以下开新进程Activity去加载dex，其实就是为了第一时间显示第一个Activity，属于伪优化，其实在加载dex过程中，Multdex先将dex压缩成了zip，然后又解压zip，而他是可以直接去加载dex的，这里多了一个压缩又解压的过程，所以其实真正的优化应该是避免先解压再压缩。





## 自定义启动任务，继承AppStartTask，重写关键方法，别的方法不用管，也不要动，为任务调度准备的
 
### abstract必须重写的方法
| 方法      |参数或返回值  | 作用  |
| :-------- | :--------| :--: |
| run| void  |  这里写你要执行的任务代码  |
| getDependsTaskList| 返回 List<Class<? extends AppStartTask>> |  这个方法用于构造图，这里返回当前任务需要依赖的任务，只有依赖任务执行完，当前任务才执行，如果没有，返回null  |
| isRunOnMainThread| 返回boolean  |  是否运行在主线程  |

### 根据实际需求选择重写的方法
| 方法      |参数或返回值  | 作用  |
| :-------- | :--------| :--: |
| priority| 返回int  |  这个代表当前线程的优先级，优先级高不一定会优先执行，而是获得cpu时间片的几率会变高，默认返回Process.THREAD_PRIORITY_BACKGROUND |
| runOnExecutor| 返回 Executor |  在isRunOnMainThread返回false的情况下起作用，决定当前任务在哪个线程池执行，需要使用Systrace确定wallTime和cpuTime，占cpuTime多建议的使用cpu线程池：TaskExceutorManager.getInstance().getCPUThreadPoolExecutor() ，默认返回TaskExceutorManager.getInstance().getIOThreadPoolExecutor()  |
| needWait| 返回boolean  |  这个方法决定了你的这个任务是否是需要等待执行完的，需结合启动管理器AppStartTaskDispatcher.await方法使用  |

## 配置任务调度器参数，即AppStartTaskDispatcher的配置方法

| 方法      |参数或返回值  | 作用  |
| :-------- | :--------| :--: |
| create | 返回AppStartTaskDispatcher  |  创建本次的任务分发器 |
| setShowLog| 返回AppStartTaskDispatcher |  是否输出日志，需使用者自行控制，默认为false，设为true可以看到任务的执行顺序，执行情况等信息 |
| setAllTaskWaitTimeOut| 返回AppStartTaskDispatcher |  阻塞等待所有非主线程AppStartTask中needWait返回true的任务的保护时间，即最多等待时间，超过这个时间不再阻塞，只在调用了await方法后生效  |
| addAppStartTask| 返回AppStartTaskDispatcher  |  添加任务  |
| start| 返回AppStartTaskDispatcher  |  开始执行任务  |
| await| void |  阻塞等待所有非主线程AppStartTask中needWait返回true的任务  |

# 启动任务，在Application的oncreate中调用，Demo有完整的测试AppStartTask
## add顺序无所谓，不影响图的关系，注意await()会阻塞等待所有非主线程AppStartTask中needWait返回true的任务，所以需要按需使用，主线程的任务本来就是阻塞的，所以，如果不需要等待非主线程任务的执行，则不用重写AppStartTask的needWait方法，也不用调用AppStartTaskDispatcher.await()方法。
``` java
ParallelTaskDispatcher.create()
		.setShowLog(true)
		.setAllTaskWaitTimeOut(5000)
                .addAppStartTask(new TestAppStartTaskTwo())
                .addAppStartTask(new TestAppStartTaskFour())
                .addAppStartTask(new TestAppStartTaskFive())
                .addAppStartTask(new TestAppStartTaskThree())
                .addAppStartTask(new TestAppStartTaskOne())
                .start()
                .await();
```
