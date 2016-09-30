# AndroidFlavorPlugin

This is a android apk flavor maker gradle plugin.

## Thanks

https://github.com/GavinCT/AndroidMultiChannelBuildTool

https://github.com/chrisbanes/gradle-mvn-push

## Usage
### (1)In root project build.gradle

```groovy

depedencies {
...

   classpath 'com.github.lchli:AndroidApkFlavorMaker:1.0.0'
   
   ...
}

```

### (2)In app/build.gradle

```groovy

apply plugin: 'com.lchli.makeApkFlavor'
...

android {
   ...
   
    LiAnPlugin{
        flavorApksDir="${buildDir}/flavorApks"
        flavorChannelFilePath="${buildDir.parent}/channel.txt"
        sourceApkName="app-debug.apk"
    }
    
    ...
}

```
