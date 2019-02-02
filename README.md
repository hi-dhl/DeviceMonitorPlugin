> 升级到AndroidStudio最新版本(>3.2)朋友们都会遇到一个问题，找不到DDMS [Android Device Monitor], 只能从SDK目录下找到 monitor 启动DDMS [Android Device Monitor]，所以写了一个插件快速启动Android Device Monitor

### 源码及使用方式

开发工具：IntelliJ IDEA
PS: 如果有需要的朋友欢迎留言

[Github地址：https://github.com/hi-dhl/DeviceMonitorPlugin](https://github.com/hi-dhl/DeviceMonitorPlugin)

[插件下载地址：https://github.com/hi-dhl/DeviceMonitorPlugin/releases/download/1.0/DeviceMonitorPlugin.jar
](https://github.com/hi-dhl/DeviceMonitorPlugin/releases/download/1.0/DeviceMonitorPlugin.jar)

安装方式：
* 打开AndroidStudio
* 选择Preference -> Plugins-> install plugin from disk
* 选择下载好的插件[DeviceMonitorPlugin.jar] -> 重启AndroidStudio

 <img src="https://user-gold-cdn.xitu.io/2019/2/2/168adbe46f701880?w=562&h=698&f=png&s=303217" width = "250"/>

如何启动：
* 打开AndroidStudio
* 菜单栏tools -> 单击DeviceMonitor

<img src="https://user-gold-cdn.xitu.io/2019/2/2/168adbece4d11b82?w=428&h=494&f=png&s=172385" width = "250"/>

PS: Google虽然删除了AdnroidStudio启动入口，但是本地SDK中还是存在，插件通过动态获取本地SDK路径启动AndroidDeviceMonitor, 由于电脑性能不同，启动速度会有不同

###  Google为什么弃用Android Device Monitor

[Android Developers](https://developer.android.com/)官网上的原文[链接](https://developer.android.com/studio/profile/monitor)

![Google](https://user-gold-cdn.xitu.io/2019/2/2/168adbf78e9c7102?w=1368&h=238&f=png&s=291471)

Android Device Monitor是一个Android应用调试和分析工具提供了一个UI工具，但是大部分组件在Android Studio 3.1已经弃用了, 并且会在Android Studio 3.2中移除，将会用新的工具帮助开发人员调试和分析Android应用，[详情戳这里](https://developer.android.com/studio/profile/monitor)

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


