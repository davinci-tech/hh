package com.huawei.maps.offlinedata.utils;

import java.io.File;

/* loaded from: classes5.dex */
public class c {
    public static void a(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            b(file);
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                b(file);
                return;
            }
            for (File file2 : listFiles) {
                a(file2);
            }
            b(file);
        }
    }

    public static boolean b(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        return file.delete();
    }
}
