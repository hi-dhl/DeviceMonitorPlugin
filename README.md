## 解决在 Android Studio 3.2 找不到 Android Device Monitor 工具

> 升级到 AndroidStudio 最新版本( >3.2 )朋友们都会遇到一个问题，找不到 DDMS [Android Device Monitor], 只能从 SDK 目录下找到 monitor 启动 DDMS [Android Device Monitor]，所以写了一个插件快速启动 Android Device Monitor

### 源码及使用方式

开发工具：IntelliJ IDEA

[Github地址：https://github.com/hi-dhl/DeviceMonitorPlugin](https://github.com/hi-dhl/DeviceMonitorPlugin)

[插件下载地址：https://github.com/hi-dhl/DeviceMonitorPlugin/releases/download/1.0/DeviceMonitorPlugin.jar
](https://github.com/hi-dhl/DeviceMonitorPlugin/releases/download/1.0/DeviceMonitorPlugin.jar)

安装方式：

* 打开 AndroidStudio
* 选择 Preference -> Plugins-> install plugin from disk
* 选择下载好的插件 [DeviceMonitorPlugin.jar] -> 重启 AndroidStudio

![](http://cdn.51git.cn/2020-05-30-1549102936603931.jpg)

如何启动：

* 打开 AndroidStudio
* 菜单栏 tools -> 单击 DeviceMonitor

![](http://cdn.51git.cn/2020-05-30-15491020796229221.jpg)

PS: Google 虽然删除了 AdnroidStudio 启动入口，但是本地 SDK 中还是存在，插件通过动态获取本地 SDK 路径启动 AndroidDeviceMonitor, 由于电脑性能不同，启动速度会有不同

###  Google 为什么弃用 Android Device Monitor

[Android Developers](https://developer.android.com/)官网上的原文[链接](https://developer.android.com/studio/profile/monitor)

![Google](http://cdn.51git.cn/2019-12-12-15490930968670.jpg)

Android Device Monitor 是一个 Android 应用调试和分析工具提供了一个 UI 工具，但是大部分组件在 Android Studio 3.1 已经弃用了, 并且会在 Android Studio 3.2 中移除，将会用新的工具帮助开发人员调试和分析 Android 应用 [详情戳这里](https://developer.android.com/studio/profile/monitor)

### 插件核心代码

```
public class Monitor extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        try {

            Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

            String os = AndroidUtils.getPlatformName();
            String sdkPath = AndroidUtils.getApkLocalProperties(project);
            if (os.toLowerCase().startsWith("win")) {
                sdkPath += File.separator + "tools" + File.separator + "monitor.bat";
            } else {
                sdkPath += File.separator + "tools" + File.separator + "monitor";
            }

            Runtime.getRuntime().exec(sdkPath);
        } catch (Exception e) {

        }

    }
}
```


```
/**
 * 动态获取本地Android SDK的路径
 *
 * @param project
 * @return
 */
public static String getApkLocalProperties(Project project) {

    String sdkPath = "";

    try {

        String path = project.getBasePath() + File.separator + "local.properties";

        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(path);
        properties.load(inputStream);

        sdkPath = properties.getProperty("sdk.dir");
    } catch (Exception e) {

    }
    return sdkPath;
}
```

[Github地址：https://github.com/hi-dhl/DeviceMonitorPlugin](https://github.com/hi-dhl/DeviceMonitorPlugin)

## 结语

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，如果你喜欢这篇文章欢迎 Star 一起来学习，期待与你一起成长

### 文章列表

#### Android 10 源码系列

* [0xA01 Android 10 源码分析：APK 是如何生成的](https://juejin.im/post/5e4366c3f265da57397e1189)
* [0xA02 Android 10 源码分析：APK 的安装流程](https://juejin.im/post/5e5a1e6a6fb9a07cb427d8cd)
* [0xA03 Android 10 源码分析：APK 加载流程之资源加载](https://juejin.im/post/5e6c8c14f265da574b792a1a)
* [0xA04 Android 10 源码分析：APK 加载流程之资源加载（二）](https://juejin.im/post/5e7f0f2c51882573c4676bc7)
* [0xA05 Android 10 源码分析：Dialog 加载绘制流程以及在 Kotlin、DataBinding 中的使用](https://juejin.im/post/5e9199db6fb9a03c7916f635)
* [0xA06 Android 10 源码分析：WindowManager 视图绑定以及体系结构](https://juejin.im/post/5ead0b865188256d545fd2f8)

#### Android 应用系列

* [如何高效获取视频截图](https://juejin.im/post/5d11d8835188251c10631ffd)
* [如何在项目中封装 Kotlin + Android Databinding](https://juejin.im/post/5e9c434a51882573663f6cc6)
* [[译][Google工程师] 刚刚发布了 Fragment 的新特性 “Fragment 间传递数据的新方式” 以及源码分析](https://juejin.im/post/5eb58da05188256d6d6bb248) 
* [[译][2.4K Start] 放弃 Dagger 拥抱 Koin](https://juejin.im/post/5ebc1eb8e51d454dcf45744e?utm_source=gold_browser_extension)
* [[译][5k+] Kotlin 的性能优化那些事](https://juejin.im/post/5ec0f3afe51d454db11f8a94#heading-7)
* [[译][Google工程师] 详解 FragmentFactory 如何优雅使用 Koin 以及源码分析](https://juejin.im/post/5ecc10626fb9a047e25d5aac)
* [[译] 解密 RxJava 的异常处理机制](https://juejin.im/post/5ecc10626fb9a047e25d5aac)

#### 工具系列

* [为数不多的人知道的 AndroidStudio 快捷键(一)](https://juejin.im/post/5df4933e518825126e639d62)
* [为数不多的人知道的 AndroidStudio 快捷键(二)](https://juejin.im/post/5df986d66fb9a016613903da)
* [关于 adb 命令你所需要知道的](https://juejin.im/post/5d57cfff51882505a87a8526)
* [10分钟入门 Shell 脚本编程](https://juejin.im/post/5a6378055188253dc332130a)

#### 逆向系列

* [基于 Smali 文件 Android Studio 动态调试 APP](https://juejin.im/post/5c8ce8b76fb9a049e30900bf)
* [解决在 Android Studio 3.2 找不到 Android Device Monitor 工具](https://juejin.im/post/5c556ff7f265da2dbe02ba3c)


