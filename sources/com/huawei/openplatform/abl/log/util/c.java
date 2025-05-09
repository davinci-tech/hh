package com.huawei.openplatform.abl.log.util;

import android.text.TextUtils;
import com.huawei.android.hicloud.sync.util.FileUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.io.File;
import java.io.IOException;

/* loaded from: classes5.dex */
public abstract class c {
    public static boolean d(File file) {
        if (file == null || file.mkdirs()) {
            return true;
        }
        a(file);
        return file.mkdirs();
    }

    public static String c(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            HiAdLog.e(FileUtil.TAG, "get path error, " + e.getClass().getSimpleName());
            return "";
        }
    }

    public static boolean b(File file) {
        try {
            File file2 = new File(file.getCanonicalPath() + System.currentTimeMillis());
            if (file.renameTo(file2)) {
                return file2.delete();
            }
            return false;
        } catch (IOException unused) {
            HiAdLog.w(FileUtil.TAG, "deleteSingleFile IOException");
            return false;
        }
    }

    private static void a(File file) {
        if (file == null) {
            return;
        }
        int i = 0;
        while (i < 10 && file != null && !f.a(c(file))) {
            i++;
            if (file.exists()) {
                HiAdLog.d(FileUtil.TAG, "current file exists");
                if (file.isFile()) {
                    b(file);
                    return;
                }
                return;
            }
            File parentFile = file.getParentFile();
            if (parentFile != null && TextUtils.equals(c(parentFile), c(file))) {
                HiAdLog.w(FileUtil.TAG, "parent file is the same as current");
                return;
            }
            file = parentFile;
        }
    }
}
