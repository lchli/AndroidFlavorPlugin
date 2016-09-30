package com.lchli.apkFlavor

import com.lchli.apkFlavor.extension.PluginExtension
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by lchli on 2016/9/29.
 */
class LiAnPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        project.extensions.create('LiAnPlugin', PluginExtension)
        def configuration = project.LiAnPlugin

        project.afterEvaluate {
            project.task('makeApkFlavors') << {

                println "---------------start make flavor apks-----------------------------------------"

                if (!project.plugins.hasPlugin('com.android.application')) {
                    throw new GradleException('makeApkFlavors: Android Application plugin required')
                }

                String flavorApksDirPath = configuration.flavorApksDir
                println "flavordir:" + flavorApksDirPath
                if (flavorApksDirPath == null || flavorApksDirPath == "") {
                    throw new GradleException('makeApkFlavors: flavorApksDirPath is null.')
                }
                File flavorDir = new File(flavorApksDirPath)

                String chanelFilePath = configuration.flavorChannelFilePath
                println "chanelFilePath:" + chanelFilePath
                if (chanelFilePath == null || chanelFilePath == "") {
                    throw new GradleException('makeApkFlavors: chanelFilePath is null.')
                }
                File channelFile = new File(chanelFilePath)
                if (!channelFile.exists()) {
                    throw new GradleException('makeApkFlavors: channelFile not exists.')
                }

                String sourceApkName=configuration.sourceApkName
                if (sourceApkName == null || sourceApkName == "") {
                    throw new GradleException('makeApkFlavors: sourceApkName is null.')
                }

                def android = project.extensions.android

                android.applicationVariants.all { variant ->
                    variant.outputs.each { output ->
                        File apkFile = output.outputFile
                        if (!apkFile.name.equals(sourceApkName)) {
                            return
                        }
                        println "source apk path:" + apkFile.absolutePath

                        MyFileUtils.makeFlavorApks(apkFile, flavorDir, channelFile)
                    }

                }


            }//task end.
        }

    }


}
