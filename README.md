# AndroidFlavorPlugin

This is a android apk flavor maker gradle plugin.安卓渠道包制作插件。

## Thanks

https://github.com/GavinCT/AndroidMultiChannelBuildTool

https://github.com/chrisbanes/gradle-mvn-push

## Usage
### (1)in root project build.gradle

```groovy

depedencies {
...

   classpath 'com.github.lchli:AndroidApkFlavorMaker:1.0.0'
   
   ...
}

```

### (2)in app/build.gradle

```groovy

apply plugin: 'com.lchli.makeApkFlavor'
...

android {
   ...
   //config flavor output dir,channel.txt,sourceApkName.
    LiAnPlugin{
        flavorApksDir="${buildDir}/flavorApks"
        flavorChannelFilePath="${buildDir.parent}/channel.txt"
        sourceApkName="app-debug.apk"
    }
    
    ...
}

```
### (3)build.
```groovy

$ gradle clean build makeApkFlavors

```
## channel util.

**[ChannelUtil](https://github.com/lchli/AndroidFlavorPlugin/blob/master/AndroidApkFlavorMaker/channelUtils/ChannelUtil.java)**

## channel.txt must like below,each line replace a channel name:

```groovy
samsungapps
hiapk
anzhi
360cn

```




