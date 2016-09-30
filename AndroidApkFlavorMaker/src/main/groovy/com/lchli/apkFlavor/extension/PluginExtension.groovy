package com.lchli.apkFlavor.extension

import org.gradle.api.GradleException

/**
 * Created by lchli on 2016/9/30.
 */
public class PluginExtension {

    String flavorApksDir
    String flavorChannelFilePath
    String sourceApkName

    public PluginExtension(){
        flavorApksDir=""
        flavorChannelFilePath=""
        sourceApkName=""
    }

}
