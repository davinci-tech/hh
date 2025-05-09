package com.huawei.healthcloud.plugintrack.golf.cloud;

import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes8.dex */
public class GolfZipUtils {
    private static final String TAG = "GolfZipUtils";

    public static void unZipBinFile(File file, String str) {
        try {
            ZipFile zipFile = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry nextElement = entries.nextElement();
                    if (nextElement.getName().split("\\.")[r2.length - 1].equals("bin")) {
                        byte[] bArr = new byte[1024];
                        InputStream inputStream = zipFile.getInputStream(nextElement);
                        FileOutputStream fileOutputStream = new FileOutputStream(str);
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                        inputStream.close();
                        fileOutputStream.close();
                    }
                }
                zipFile.close();
            } finally {
            }
        } catch (IOException unused) {
            LogUtil.e(TAG, "unZipFile: Failed!");
        }
    }
}
