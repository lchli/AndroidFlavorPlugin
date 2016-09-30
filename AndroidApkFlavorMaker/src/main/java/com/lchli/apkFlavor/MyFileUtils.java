package com.lchli.apkFlavor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.apache.tools.zip.ZipUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;


/**
 * Created by lchli on 2016/9/29.
 */
public class MyFileUtils {
    /**
     * @param srcApk           the source apk.
     * @param flavorDir        the dir to save flavor apks.
     * @param channelsInfoFile the info file which have channel names,each line replace a channel.
     */
    public static void makeFlavorApks(File srcApk, File flavorDir, File channelsInfoFile) {
        try {
            FileUtils.deleteDirectory(flavorDir);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        boolean success = flavorDir.mkdirs();
        if (!success) {
            throw new RuntimeException("exception:create flavorDir fail.");
        }

        File tmpSourceDir = new File(flavorDir, "tmpSourceDir");
        success = tmpSourceDir.mkdirs();
        if (!success) {
            throw new RuntimeException("exception:create tmpSourceDir fail.");
        }

        try {
            ZipUtilities.getInstance().unCompressZipFile(srcApk.getAbsolutePath(), tmpSourceDir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        BufferedReader channelReader = null;
        try {
            channelReader = new BufferedReader(new InputStreamReader(new FileInputStream(channelsInfoFile)));
            String channelName;

            while ((channelName = channelReader.readLine()) != null) {
                /**
                 * create channel apk.
                 */
                String channelApkName = srcApk.getName().replace(".", "_" + channelName + ".");
                File channelApk = new File(flavorDir, channelApkName);
                channelApk.createNewFile();
                /**
                 * create channel file.
                 */
                String channelTag = "channel_" + channelName;
                File channelTagFile = new File(tmpSourceDir.getAbsolutePath() + File.separator + "META-INF", channelTag);
                channelTagFile.createNewFile();
                /**
                 * zip file.
                 */
                ZipUtilities.getInstance().compressDirectory(tmpSourceDir.getAbsolutePath(), channelApk.getAbsolutePath());
                /**
                 * should delete after used.
                 */
                channelTagFile.delete();
            }//while end.

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            FileUtils.deleteQuietly(tmpSourceDir);
            IOUtils.closeQuietly(channelReader);
        }

    }
}
