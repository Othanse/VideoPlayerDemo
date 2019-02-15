# VideoPlayerDemo
视频播放 列表播放-集成GSY播放器、Jiecao播放器、IJK播放内核！GSY播放器也是IJK内核（ 都是可选的，还可以选EXO内核，MediaPlayer内核等等）   
处理了列表滑动自动播放 资源回收 内存泄漏 黑屏问题 卡顿问题 滑动停止自动回收没有在播放的资源


### 目前还没抽取成库直接使用！可以直接clone到本地后看着改吧改吧使用！等抽取成库了再写使用说明！
### 只是刚把功能实现 代码细节还未优化 看官见谅 能力有限 只能先写代码 再优化 没有形成在写的过程中就优化代码的习惯


### 已处理问题（GSY播放器的处理了，jiecao的没有处理，可以仿造着处理一下就OK）：
* 监听RecyclerView的scroll事件 处理自动播放
* 在自动播放时会先检查是否是在播放状态，在播状态应该先释放资源再播放
* 滑动停止后 IDLE状态下 会将需要释放的列表 挨个释放
* 已使用AndroidStudio的Profiler检测 不会出现内存泄漏问题（以下数据是特定机型的数据，不同机型肯定是不一样的，只做参照）
  * 在观看视频时  会长期保持内存占用在 160M左右 (同时播放两个视频的情况) 同时播放4个视频占用在300M左右
  * onPause后会占用130M左右
  * 不播放会占用在90M左右
  
 ### 开发环境：
 * AndroidStudio 3.2.1
 * JDK 1.8.0_152-release
 * compileSdkVersion 26
 * buildToolsVersion "26.0.3"
 * Gradle版本 4.4  https\://services.gradle.org/distributions/gradle-4.4-all.zip
 * classpath 'com.android.tools.build:gradle:3.1.3'
 * 测试手机：OnePlus A6000（一加6）, Android 9, API 28
