package com.hi.dhl;


import com.intellij.openapi.project.Project;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * <pre>
 *     author: dhl
 *     date  : 2019/1/14
 *     desc  : 获取Android配置信息的工具类
 * </pre>
 */
public class AndroidUtils {

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

    /**
     * 获取不同平台的 win、mac etc;
     *
     * @return
     */
    public static String getPlatformName() {
        String osName = System.getProperty("os.name");
        return osName;
    }
}
