# Vp控件封装库
#### 目录介绍
- 01.基础概念介绍
- 02.常见思路做法
- 03.Api调用说明
- 04.遇到的坑分析
- 05.该库性能分析



### 01.基础概念介绍
#### 1.1 该库简单的介绍
- 通用PagerAdapter封装
    - 方便快速tabLayout+ViewPager。针对页面较少的Fragment选择使用BaseFragmentPagerAdapter，针对页面较多的Fragment选择使用BasePagerStateAdapter。
- BaseLazyFragment懒加载fragment
    - 就是等到该页面的UI展示给用户时，再加载该页面的数据(从网络、数据库等)。
- BaseVisibilityFragment
    - fragment是否可见封装类，比如之前需求在页面可见时埋点就需要这个。弥补了setUserVisibleHint遇到的bug……
- ViewPager2封装库
    - DiffFragmentStateAdapter，可以用作diff操作的适配器；ViewPagerDiffCallback用来做diff计算的工具类


#### 1.3 ViewPager介绍
- 三种Adapter的缓存策略
    - PagerAdapter：缓存三个，通过重写instantiateItem和destroyItem达到创建和销毁view的目的。
    - FragmentPagerAdapter：内部通过FragmentManager来持久化每一个Fragment，在destroyItem方法调用时只是detach对应的Fragment，并没有真正移除！
    - FragmentPagerStateAdapter：内部通过FragmentManager来管理每一个Fragment，在destroyItem方法，调用时移除对应的Fragment。
- 三个Adapter使用场景分析
    - PagerAdapter：当所要展示的视图比较简单时适用
    - FragmentPagerAdapter：当所要展示的视图是Fragment，并且数量比较少时适用
    - FragmentStatePagerAdapter：当所要展示的视图是Fragment，并且数量比较多时适用


#### 1.4 ViewPager2介绍
- ViewPager2是用来替换ViewPager的
    - ViewPager2是final修饰的，直接继承ViewGroup，其内部是使用RecyclerView，ViewPager2默认是使用懒加载。
- ViewPager2与ViewPager的改进：
    - （1）支持Right to Left布局，即从右向左布局；（2）支持竖向滚动；（3）支持notifyDataSetChanged；（4）支持懒加载
- ViewPager2相关api上的变动如下：
    - （1）FragmentStateAdapter替换了原来的 FragmentStatePagerAdapter；（2）RecyclerView.Adapter替换了原来的 PagerAdapter；（3）registerOnPageChangeCallback替换了原来的 addPageChangeListener



### 02.常见思路和做法



### 03.Api调用说明



### 04.遇到的坑分析
#### 4.1 ViewPager事件崩溃
- 抛出的异常：onInterceptTouchEvent(DetailViewPager.java:36)，基本上是一堆的源代码异常堆栈信息。
- 异常分析：类似此种系统性异常，尽可能得去捕获了。
- 解决办法：重写onTouchEvent和onInterceptTouchEvent方法，然后捕获异常。具体可以看：[SecureViewPager]()
  




### 05.该库性能分析










CameraOperation类，setTorch增加catch操作，是否会影响业务逻辑
SplashFragment页面，从init——splash——mainpager页面，中间过渡有个短暂白色
去掉fetchDeviceBySeries方法，该方法使用风险比较大











