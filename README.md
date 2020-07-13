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

致力于分享一系列 Android 系统源码、逆向分析、算法、翻译、Jetpack 源码相关的文章，欢迎一起来学习，在技术的道路上一起前进，另外我还在维护其他项目 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)、[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin) 、[Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation) 、 [AndroidX-Jetpack-Practice](https://github.com/hi-dhl/AndroidX-Jetpack-Practice) 可以前去查看。

### AndroidX-Jetpack-Practice

正在建立一个最全、最新的 AndroidX Jetpack 相关组件的实战项目 以及 相关组件原理分析文章，目前已经包含了 App Startup、Paging3、Hilt 等等，正在逐渐增加其他 Jetpack 新成员，仓库持续更新，可以前去查看：[AndroidX-Jetpack-Practice](https://github.com/hi-dhl/AndroidX-Jetpack-Practice)。

### Android10-Source-Analysis

正在写一系列的 Android 10 源码分析的文章，了解系统源码，不仅有助于分析问题，在面试过程中，对我们也是非常有帮助的，如果你同我一样喜欢研究 Android 源码，可以关注我 GitHub 上的 [Android10-Source-Analysis](https://github.com/hi-dhl/Android10-Source-Analysis)。

### Leetcode-Solutions-with-Java-And-Kotlin

由于 LeetCode 的题库庞大，每个分类都能筛选出数百道题，由于每个人的精力有限，不可能刷完所有题目，因此我按照经典类型题目去分类、和题目的难易程度去排序。

* 数据结构： 数组、栈、队列、字符串、链表、树……
* 算法： 查找算法、搜索算法、位运算、排序、数学、……

每道题目都会用 Java 和 kotlin 去实现，并且每道题目都有解题思路，如果你同我一样喜欢算法、LeetCode，可以关注我 GitHub 上的 LeetCode 题解：[Leetcode-Solutions-with-Java-And-Kotlin](https://github.com/hi-dhl/Leetcode-Solutions-with-Java-And-Kotlin)。

### Technical-Article-Translation

目前正在整理和翻译一系列精选国外的技术文章，不仅仅是翻译，很多优秀的英文技术文章提供了很好思路和方法，每篇文章都会有**译者思考**部分，对原文的更加深入的解读，可以关注我 GitHub 上的 [Technical-Article-Translation](https://github.com/hi-dhl/Technical-Article-Translation)。

## License

```
Copyright 2020 hi-dhl (Jack Deng)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


