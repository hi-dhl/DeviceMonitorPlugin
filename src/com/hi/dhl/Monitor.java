package com.hi.dhl;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;

import java.io.File;

/**
 * <pre>
 *     author: dhl
 *     date  : 2019/1/14
 *     desc  : 插件启动入口
 * </pre>
 */
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
