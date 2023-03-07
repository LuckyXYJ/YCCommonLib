# 框架公共组件层
#### 目录介绍
- 01.框架公共组件层
- 02.组件化建设
- 03.公共组件库依赖
- 04.Activity栈管理库
- 05.通用存储库
- 06.Log日志打印库
- 07.App重新启动库
- 08.通用工具类库
- 09.通用基类封装库
- 10.反射工具类库
- 11.Intent封装库
- 12.基础接口库
- 13.异常上报接口库
- 14.File流读写库
- 15.加密和解密库
- 16.Lru内存缓存库
- 17.Lru磁盘缓存库
- 18.来电和去电监听
- 19.置灰方案实践库
- 20.状态监听实践库


### 01.框架公共组件层
- 组件化开发中基础公共库，activity栈管理；Log日志；通用缓存库(支持sp，mmkv，lru，disk，fastsp等多种存储方式切换)；App重启；通用全面的工具类Utils；通用基类fragment，adpater，activity等简单封装；intent内容打印到控制台库


### 02.组件化建设
- 按照不同层级架构图如下所示
    - ![image](https://github.com/yangchong211/YCAppTool/blob/master/Image/02.App%E7%BB%84%E4%BB%B6%E5%8C%96%E6%9E%B6%E6%9E%84%E5%9B%BE.jpg)


### 03.公共组件库依赖
- 关于依赖库如下所示，可以根据需求选择性使用：
    ``` java
    //base基类
    implementation 'com.github.yangchong211.YCCommonLib:BaseClassLib:1.4.8'
    implementation 'com.github.yangchong211.YCCommonLib:ComponentLib:1.4.8'
    //工具类utils
    implementation 'com.github.yangchong211.YCCommonLib:ToolUtilsLib:1.4.8'
    //activity栈管理
    implementation 'com.github.yangchong211.YCCommonLib:ActivityManager:1.4.8'
    //通用缓存存储库，支持sp，fastsp，mmkv，lruCache，DiskLruCache等
    implementation 'com.github.yangchong211.YCCommonLib:AppBaseStore:1.4.8'
    //通用日志输出库
    implementation 'com.github.yangchong211.YCCommonLib:AppLogLib:1.4.8'
    //app重启库
    implementation 'com.github.yangchong211.YCCommonLib:AppRestartLib:1.4.8'
    //intent内容输出到控制台
    implementation 'com.github.yangchong211.YCCommonLib:SafeIntentLib:1.4.8'
    //通用组件接口库
    implementation 'com.github.yangchong211.YCCommonLib:AppCommonInter:1.4.8'
    //各种广播监听哭
    implementation 'com.github.yangchong211.YCCommonLib:AppStatusLib:1.4.8'
    //基建库
    implementation 'com.github.yangchong211.YCCommonLib:ArchitectureLib:1.4.8'
    //同上上报库
    implementation 'com.github.yangchong211.YCCommonLib:EventUploadLib:1.4.8'
    //权限库
    implementation 'com.github.yangchong211.YCCommonLib:AppPermission:1.4.8'
    //Lru磁盘缓存库
    implementation 'com.github.yangchong211.YCCommonLib:AppLruDisk:1.4.8'
    //Lru内存缓存库
    implementation 'com.github.yangchong211.YCCommonLib:AppLruCache:1.4.8'
    //fragment生命周期监听库
    implementation 'com.github.yangchong211.YCCommonLib:FragmentManager:1.4.8'
    //反射工具库
    implementation 'com.github.yangchong211.YCCommonLib:ReflectionLib:1.4.8'
    //App启动优化库
    implementation 'com.github.yangchong211.YCCommonLib:ParallelTaskLib:1.4.8'
    //Context上下文库
    implementation 'com.github.yangchong211.YCCommonLib:AppContextLib:1.4.8'
    //加解密库
    implementation 'com.github.yangchong211.YCCommonLib:AppEncryptLib:1.4.8'
    //handler包装库
    implementation 'com.github.yangchong211.YCCommonLib:AppHandlerLib:1.4.8'
    //Application库
    implementation 'com.github.yangchong211.YCCommonLib:ApplicationLib:1.4.8'
    //store磁盘分区库
    implementation 'com.github.yangchong211.YCCommonLib:AppMediaStore:1.4.8'
    //内存
    implementation 'com.github.yangchong211.YCCommonLib:ToolMemoryLib:1.4.8'
    //屏幕截屏库
    implementation 'com.github.yangchong211.YCCommonLib:AppScreenLib:1.4.8'
    //Wi-Fi库
    implementation 'com.github.yangchong211.YCCommonLib:AppWiFiLib:1.4.8'
    //Vp相关适配器库
    implementation 'com.github.yangchong211.YCCommonLib:BaseVpAdapter:1.4.8'
    //io流读写库
    implementation 'com.github.yangchong211.YCCommonLib:FileIoHelper:1.4.8'
    //图片工具库
    implementation 'com.github.yangchong211.YCCommonLib:ImageToolLib:1.4.8'
    //网络判断库
    implementation 'com.github.yangchong211.YCCommonLib:NetWorkLib:1.4.8'
    //手机
    implementation 'com.github.yangchong211.YCCommonLib:PhoneSensor:1.4.8'
    //File文件库
    implementation 'com.github.yangchong211.YCCommonLib:ToolFileLib:1.4.8'
    //Zip压缩库
    implementation 'com.github.yangchong211.YCCommonLib:ZipFileLib:1.4.8'
    //图片压缩
    implementation 'com.github.yangchong211.YCCommonLib:AppCompress:1.4.8'
    ```


### 04.Activity栈管理库
- 非常好用的activity任务栈管理库，自动化注册。完全解耦合的activity栈管理，拿来即可用，或者栈顶Activity，移除添加，推出某个页面，获取应用注册Activity列表等，可以注册监听某个页面的生命周期，小巧好用。
    ``` java
    //退出应用程序
    ActivityManager.getInstance().appExist();
    //查找指定的Activity
    Activity commonActivity = ActivityManager.getInstance().get(CommonActivity.class);
    //判断界面Activity是否存在
    boolean exist = ActivityManager.getInstance().isExist(CommonActivity.class);
    //移除栈顶的activity
    ActivityManager.getInstance().pop();
    //结束所有Activity
    ActivityManager.getInstance().finishAll();
    //结束指定的Activity
    ActivityManager.getInstance().finish(CommonActivity.this);
    //判断activity任务栈是否为空
    ActivityManager.getInstance().isEmpty();
    //获取栈顶的Activity
    Activity activity = ActivityManager.getInstance().peek();
    //判断activity是否处于栈顶
    ActivityManager.getInstance().isActivityTop(this,"MainActivity");
    //添加 activity 入栈
    ActivityManager.getInstance().add(CommonActivity.this);
    //移除 activity 出栈
    ActivityManager.getInstance().remove(CommonActivity.this);
    //监听某个activity的生命周期，完全解耦合
    ActivityManager.getInstance().registerActivityLifecycleListener(CommonActivity.class, new ActivityLifecycleListener() {
        @Override
        public void onActivityCreated(@Nullable Activity activity, Bundle savedInstanceState) {
            super.onActivityCreated(activity, savedInstanceState);
        }
    
        @Override
        public void onActivityStarted(@Nullable Activity activity) {
            super.onActivityStarted(activity);
        }
    });
    //移除某个activity的生命周期，完全解耦合
    //ActivityManager.getInstance().registerActivityLifecycleListener(CommonActivity.this,listener);
    ```




### 05.通用存储库
- 通用存储库
    - 支持二级缓存，LRU缓存，磁盘缓存(可以使用sp，mmkv，或者DiskLruCache)。不管你使用那种方式的存储，都是一套通用的api，使用几乎是零成本。
- 第一步：通用存储库初始化
    ``` java
    CacheConfig.Builder builder = CacheConfig.Companion.newBuilder();
    //设置是否是debug模式
    CacheConfig cacheConfig = builder.debuggable(BuildConfig.DEBUG)
            //设置外部存储根目录
            .extraLogDir(null)
            //设置lru缓存最大值
            .maxCacheSize(100)
            //内部存储根目录
            .logDir(null)
            //创建
            .build();
    CacheInitHelper.INSTANCE.init(MainApplication.getInstance(),cacheConfig);
    //最简单的初始化
    //CacheInitHelper.INSTANCE.init(CacheConfig.Companion.newBuilder().build());
    ```
- 第二步：存储数据和获取数据
    ```
    //存储数据
    dataCache.saveBoolean("cacheKey1",true);
    dataCache.saveFloat("cacheKey2",2.0f);
    dataCache.saveInt("cacheKey3",3);
    dataCache.saveLong("cacheKey4",4);
    dataCache.saveString("cacheKey5","doubi5");
    dataCache.saveDouble("cacheKey6",5.20);
    
    //获取数据
    boolean data1 = dataCache.readBoolean("cacheKey1", false);
    float data2 = dataCache.readFloat("cacheKey2", 0);
    int data3 = dataCache.readInt("cacheKey3", 0);
    long data4 = dataCache.readLong("cacheKey4", 0);
    String data5 = dataCache.readString("cacheKey5", "");
    double data6 = dataCache.readDouble("cacheKey5", 0.0);
    ```
- 第三步：一些存储说明
    - 关于设置磁盘缓存的路径，需要注意一些问题。建议使用该库默认的路径
    ``` java
    /**
     * log路径，通常这个缓存比较私有的内容
     * 比如sp，mmkv，存储的用户数据
     * 内部存储根目录，举个例子：
     * file:data/user/0/包名/files
     * cache:/data/user/0/包名/cache
     */
    val logDir: String?
    
    /**
     * 额外的log路径，通常缓存一些不私密的内存
     * 比如缓存图片，缓存视频，缓存下载文件，缓存日志等
     *
     * 外部存储根目录，举个例子
     * files:/storage/emulated/0/Android/data/包名/files
     * cache:/storage/emulated/0/Android/data/包名/cache
     */
    val extraLogDir: File?
    ```


### 06.Log日志打印库
- 通用日志库框架，专用LogCat工具，主要功能全局配置log输出, 个性化设置Tag，可以设置日志打印级别，支持打印复杂对象，可以实现自定义日志接口，支持简化版本将日志写入到文件中。小巧好用！
- 第一步：初始化操作
    ``` java
    String ycLogPath = AppFileUtils.getCacheFilePath(this, "ycLog");
    AppLogConfig config = new AppLogConfig.Builder()
            //设置日志tag总的标签
            .setLogTag("yc")
            //是否将log日志写到文件
            .isWriteFile(true)
            //是否是debug
            .enableDbgLog(true)
            //设置日志最小级别
            .minLogLevel(Log.VERBOSE)
            //设置输出日志到file文件的路径。前提是将log日志写入到文件设置成true
            .setFilePath(ycLogPath)
            .build();
    //配置
    AppLogFactory.init(config);
    ```
- 第二步：使用Log日志，十分简单，如下所示
    ``` java
    //自己带有tag标签
    AppLogHelper.d("MainActivity: ","debug log");
    //使用全局tag标签
    AppLogHelper.d("MainActivity log info no tag");
    //当然，如果不满足你的要求，开发者可以自己实现日志输出的形式。
    AppLogFactory.addPrinter(new AbsPrinter() {
        @NonNull
        @Override
        public String name() {
            return "yc";
        }
    
        @Override
        public void println(int level, String tag, String message, Throwable tr) {
            //todo 这块全局回调日志，你可以任意实现自定义操作
        }
    });
    ```



### 07.App重新启动库
- 使用场景说明
    - 使用到了简单工厂模式，通过工厂类，只要提供一个参数传给工厂，就可以直接得到一个想要的产品对象，并且可以按照接口规范来调用产品对象的所有功能（方法）。
    - 比如App切换了debug或者release环境，需要进行app重新启动，可以使用该库，小巧好用。一行代码搞定，傻瓜式使用！
- 第一种方式，开启一个新的服务KillSelfService，用来重启本APP。
    ``` java
    RestartAppHelper.restartApp(this,RestartFactory.SERVICE);
    ```
- 第二种方式，使用闹钟延时，使用PendingIntent延迟意图，然后重启app
    ``` java
    RestartAppHelper.restartApp(this,RestartFactory.ALARM);
    ```
- 第三种方式，检索获取项目中LauncherActivity，然后设置该activity的flag和component启动app
    ``` java
    RestartAppHelper.restartApp(this,RestartFactory.MAINIFEST);
    ```


### 08.通用工具类库
- ToolUtils 是一个 Android 工具库。便于开发人员，快捷高效开发需求。
- 常用的基本上都有，还包括图片压缩，日历事件，异常上报工具，网络，手机方向监听，防爆力点击等工具类。
    ``` java
    FastClickUtils              防爆力点击，可以自定义设置间距时间
    PerfectClickListener        避免在1秒内出发多次点击
    AppFileUtils                file文件工具类
    FileShareUtils              file文件分享工具类
    ScreenShotUtils             监听屏幕被截图事件
    SensorManagerUtils          手机系统方向监听器工具类
    AppNetworkUtils             网络工具类
    AppProcessUtils             进程工具类，高效获取进程名称
    CompressUtils               图片压缩工具类
    ExceptionReporter           异常上报工具类
    OnceInvokeUtils             避免多次执行工具类
    StatusBarUtils              状态栏工具类
    
    //还有很多其他常用工具类，这里就不一一罗列呢
    ……
    ```


### 09.通用基类封装库
- 通用PagerAdapter封装
    - 方便快速tabLayout+ViewPager。针对页面较少的Fragment选择使用BaseFragmentPagerAdapter，针对页面较多的Fragment选择使用BasePagerStateAdapter。
- BaseLazyFragment懒加载fragment
    - 就是等到该页面的UI展示给用户时，再加载该页面的数据(从网络、数据库等)。
- BaseVisibilityFragment
    - fragment是否可见封装类，比如之前需求在页面可见时埋点就需要这个。弥补了setUserVisibleHint遇到的bug……
- ViewPager2封装库
    - DiffFragmentStateAdapter，可以用作diff操作的适配器；ViewPagerDiffCallback用来做diff计算的工具类


### 10.反射工具类库


### 11.Intent封装库
- 通用打印Intent对象内容到log日志栏中，支持普通intent和延迟PendingIntent。超级方便检查，可以打印action，category，data，flags，extras等等
    ``` java
    //打印intent所有的数据
    IntentLogger.print("intent test : ", intent)
    //打印intent中component
    IntentLogger.printComponentName("intent component : " , intent)
    //打印intent中extras参数
    IntentLogger.printExtras("intent test : ", intent)
    //打印intent中flags参数
    IntentLogger.printFlags("intent test : ", intent)
    
    //PendingIntent
    //打印intent所有的数据
    PendingIntentLogger.print("intent test : ", intent)
    //打印intent中content
    PendingIntentLogger.printContentIntent("intent content : " , intent)
    //打印intent的tag
    PendingIntentLogger.printTag("intent tag : " , intent)
    ```


### 12.基础接口库
- 背景说明：由于组件化开发中有很多基础组件，由于某些需求，需要统计一些事件，异常上报到平台上，获取控制降级，自定义日志打印等，因此采用接口回调方式实现
- IEventTrack，event事件接口，一般用于特殊事件上报作用
- IExceptionTrack，异常事件接口，一般可以用在组件库中catch捕获的时候，上报日志到服务平台操作
- ILogger，log自定义日志接口，一般用于组件库日志打印，暴露给外部开发者
- IMonitorToggle，AB测试开关接口，也可以叫降级开关，一般用于组件库某功能降级操作，暴露给开发者设置



### 13.异常上报接口库
- 基础库作为一个功能比较独立的lib，总不可能依赖APM组件吧。因此，就采用抽象类隔离！App壳工程继承抽象类，lib库直接调用抽象类。
- api调用如下所示，直接拿来用即可。下面这些调用可以用在lib库中，特轻量级上报
    ``` java
    //上报日志
    LoggerReporter.report("DiskLruCacheHelper" , "lru disk file path : " + directory.getPath());
    LoggerReporter.report("DiskLruCacheHelper clear cache");
    //上报异常
    ExceptionReporter.report("Unable to get from disk cache", e);
    ExceptionReporter.report(ioe);
    //上报event事件，通常用来埋点
    EventReporter.report("initCacheSuccess")
    EventReporter.report("initCacheSuccess",map)
    ```
- 需要在壳工程代码中，添加具体实现类。代码如下所示：
    ``` java
    //LoggerReporter，ExceptionReporter，EventReporter都是类似的
    public class LoggerReporterImpl extends LoggerReporter {
        @Override
        protected void reportLog(String eventName) {
            
        }
    
        @Override
        protected void reportLog(String eventName, String message) {
    
        }
    }
    ```


### 14.File流读写库
#### 14.1 从文件中读数据
- 从文件中读数据，使用普通字节流或者字符流方式，如下所示
    ``` java
    //字节流读取file文件，转化成字符串
    String file2String = FileIoUtils.readFile2String1(fileName);
    //字符流读取file文件，转化成字符串
    String file2String = FileIoUtils.readFile2String2(fileName);
    ```
- 从文件中读数据，使用高效流方式，如下所示
    ``` java
    //高效字节流读取file文件，转化成字符串
    String file2String = BufferIoUtils.readFile2String1(fileName);
    //高效字符流读取file文件，转化成字符串
    String file2String = BufferIoUtils.readFile2String2(fileName);
    ```


#### 14.2 将内容写入文件
- 从文件中读数据，使用普通字节流或者字符流方式，如下所示
    ``` java
    //使用字节流，写入字符串内容到文件中
    FileIoUtils.writeString2File1(content,fileName);
    //使用字符流，写入字符串内容到文件中
    FileIoUtils.writeString2File2(content,fileName);
    ```
- 从文件中读数据，使用高效流方式，如下所示
    ``` java
    //高效字节流写入字符串内容到文件中
    BufferIoUtils.writeString2File1(content,fileName);
    //高效字符流写入字符串内容到文件中
    BufferIoUtils.writeString2File2(content,fileName);
    ```


#### 14.3 文件复制操作
- 使用字节流&字符流复制
    ``` java
    //使用字节流复制文件，根据文件路径拷贝文件。
    FileIoUtils.copyFile1(fileName,newFileName);
    //使用字符流复制文件，根据文件路径拷贝文件。
    FileIoUtils.copyFile2(fileName,newFileName);
    ```
- 使用高效流复制
    ``` java
    //使用高效字符缓冲流，根据文件路径拷贝文件。
    BufferIoUtils.copyFile1(fileName,newFileName);
    //使用高效字节缓冲流，根据文件路径拷贝文件
    BufferIoUtils.copyFile2(fileName,newFileName);
    ```


#### 14.4 将流对象写入文件
- 将InputStream流对象写入到本地文件中
    ``` java
    //使用字符流读取流数据到新的file文件中
    FileIoUtils.writeFileFromIS1(is,srcFile);
    //使用字节流将流数据写到文件中
    FileIoUtils.writeFileFromIS1(is,fileName);
    ```



### 15.加密和解密库
- 关于MD5加密Api如下所示
    ``` java
    //对字符串md5加密
    String md2 = Md5EncryptUtils.encryptMD5ToString(string);
    //对字符串md5加密，加盐处理
    String md3 = Md5EncryptUtils.encryptMD5ToString(string,"doubi");
    //对字节数据md5加密
    String md4 = Md5EncryptUtils.encryptMD5ToString(bytes);
    //对字节数据md5加密，加盐处理
    String md5 = Md5EncryptUtils.encryptMD5ToString(bytes,"doubi".getBytes());
    //对文件进行md5加密
    String md6 = Md5EncryptUtils.encryptMD5File1(txt);
    //对文件进行md5加密
    String md7 = Md5EncryptUtils.encryptMD5File2(new File(txt));
    ```
- 关于base64加密和解密的Api如下所示
    ``` java
    //字符Base64加密
    String strBase64_2 = Base64Utils.encodeToString(str);
    //字符Base64解密
    String strBase64_3 = Base64Utils.decodeToString(strBase64_2);
    ```
- 关于DES加密和解密的Api如下所示
    ``` java
    //DES加密字符串
    String encrypt1 = DesEncryptUtils.encrypt(string,password);
    //DES解密字符串
    String decrypt1 = DesEncryptUtils.decrypt(encrypt1 , password);
    //DES加密文件
    String encryptFile1 = DesEncryptUtils.encryptFile(password, fileName, destFile);
    //DES解密文件
    String decryptFile1 = DesEncryptUtils.decryptFile(password, destFile, destFile3);
    //DES 加密后转为 Base64 编码
    String encrypt2 = DesEncryptUtils.encrypt(string.getBytes(), password.getBytes());
    //DES解密字符串 Base64 解码
    String decrypt2 = DesEncryptUtils.decrypt(encrypt2.getBytes(), password.getBytes());
    ```
- 关于AES加密和解密的Api如下所示
    ``` java
    //AES加密字符串
    String encrypt1 = AesEncryptUtils.encrypt(string,password);
    //AES解密字符串
    String decrypt1 = AesEncryptUtils.decrypt(encrypt1 , password);
    ```
- 关于RC4加密和解密的Api如下所示
    ``` java
    //RC4加密
    String encrypt1 = Rc4EncryptUtils.encryptString(string, secretKey);
    //RC4解密
    String decrypt1 = Rc4EncryptUtils.decryptString(encrypt1, secretKey);
    //RC4加密base64编码数据
    String encrypt2 = Rc4EncryptUtils.encryptToBase64(bytes1, secretKey);
    //RC4解密base64解码数据
    byte[] bytes = Rc4EncryptUtils.decryptFromBase64(encrypt2, secretKey);
    ```


### 16.Lru内存缓存库



### 17.Lru磁盘缓存库


### 18.来电和去电监听
- 业务场景说明
    - 在App进行音视频聊天的时，这个时候来电了，电话接通后，需要关闭音视频聊天。这个时候就需要监听电话来电和去电状态。
- 监听来电去电能干什么
    - 第一：能够针对那些特殊的电话进行自动挂断，避免打扰到用户。
    - 第二：在业务中，针对来电和去电接通后，需要关闭音视频通话。
- api调用如下所示，直接拿来用即可
    ``` java
    PhoneManager.getInstance().setOnPhoneListener(new OnPhoneListener() {
        @Override
        public void callIdle() {
            ToastUtils.showRoundRectToast("挂断");
        }

        @Override
        public void callOffHook() {
            ToastUtils.showRoundRectToast("接听");
        }

        @Override
        public void callRunning() {
            ToastUtils.showRoundRectToast("响铃");
        }
    });
    PhoneManager.getInstance().registerPhoneStateListener(this);
    ```


### 19.置灰方案实践库
- 当在特殊的某一个日子
    - 我们会表达我们的悼念，缅怀、纪念之情，APP会在某一日设置成黑灰色。比如清明节这天很多App都设置了符合主题的灰色模式。
- App置灰目标
    - 可以设置全局置灰，也可以设置单独的页面置灰，最好是简单化调用封装的API更好。
- Api调用如下所示
    ``` java
    AppGrayHelper.getInstance().setType(1).setGray(true).initGrayApp(this,true);
    ```
- 如何实现App全局灰色
    ```
    //使用注册ActivityLifecycleCallbacks监听，设置所有activity布局灰色
    AppGrayHelper.getInstance().setGray(true).initGrayApp(this,true)
    //使用hook设置全局灰色
    AppGrayHelper.getInstance().setGray(true).setGray3()
    ```
- 如何实现单独页面灰色
    ```
    AppGrayHelper.getInstance().setGray1(window)
    AppGrayHelper.getInstance().setGray2(window.decorView)
    ```
- 如何实现Dialog和PopupWindow灰色
    ```
    AppGrayHelper.getInstance().setGray(true).setGray2(view)
    ```



### 20.状态监听实践库
- api调用如下所示，直接拿来用即可。可以监听wifi，网络，gps，蓝牙，屏幕亮和灭等。完全解耦合。
    ``` kotlin
    AppStatusManager manager = new AppStatusManager.Builder()
            .context(MainApplication.getInstance())
            .file(file)
            .threadSwitchOn(false)
            .builder();
    manager.registerAppStatusListener(new BaseStatusListener() {
        @Override
        public void wifiStatusChange(boolean isWifiOn) {
            super.wifiStatusChange(isWifiOn);
            if (isWifiOn){
                AppLogUtils.i("app status Wifi 打开");
            } else {
                AppLogUtils.i("app status Wifi 关闭");
            }
        }
    
        @Override
        public void gpsStatusChange(boolean isGpsOn) {
            super.gpsStatusChange(isGpsOn);
            if (isGpsOn){
                AppLogUtils.i("app status Gps 打开");
            } else {
                AppLogUtils.i("app status Gps 关闭");
            }
        }
    
        @Override
        public void networkStatusChange(boolean isConnect) {
            super.networkStatusChange(isConnect);
            if (isConnect){
                AppLogUtils.i("app status Network 打开");
            } else {
                AppLogUtils.i("app status Network 关闭");
            }
        }
    
        @Override
        public void screenStatusChange(boolean isScreenOn) {
            super.screenStatusChange(isScreenOn);
            if (isScreenOn){
                AppLogUtils.i("app status Screen 打开");
            } else {
                AppLogUtils.i("app status Screen 关闭");
            }
        }
    
        @Override
        public void screenUserPresent() {
            super.screenUserPresent();
            AppLogUtils.i("app status Screen 使用了");
        }
    
        @Override
        public void bluetoothStatusChange(boolean isBluetoothOn) {
            super.bluetoothStatusChange(isBluetoothOn);
            if (isBluetoothOn){
                AppLogUtils.i("app status 蓝牙 打开");
            } else {
                AppLogUtils.i("app status 蓝牙 关闭");
            }
        }
    
        @Override
        public void batteryStatusChange(AppBatteryInfo batteryInfo) {
            super.batteryStatusChange(batteryInfo);
            AppLogUtils.i("app status 电量 " + batteryInfo.toStringInfo());
        }
    
        @Override
        public void appThreadStatusChange(AppThreadInfo threadInfo) {
            super.appThreadStatusChange(threadInfo);
            AppLogUtils.i("app status 所有线程数量 " + threadInfo.getThreadCount());
            AppLogUtils.i("app status run线程数量 " + threadInfo.getRunningThreadCount().size());
            AppLogUtils.i("app status wait线程数量 " + threadInfo.getWaitingThreadCount().size());
            AppLogUtils.i("app status block线程数量 " + threadInfo.getBlockThreadCount().size());
            AppLogUtils.i("app status timewait线程数量 " + threadInfo.getTimeWaitingThreadCount().size());
        }
    });
    ```








