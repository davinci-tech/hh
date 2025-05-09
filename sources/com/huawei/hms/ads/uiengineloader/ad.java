package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class ad {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4368a = "dl_FileUtil";

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf <= 0 ? str : str.substring(0, lastIndexOf);
    }

    public static boolean b(String str) {
        boolean z;
        boolean z2 = true;
        try {
            File file = new File(str);
            String[] list = file.list();
            if (!file.isDirectory() || list == null || list.length <= 0) {
                z = true;
            } else {
                z = true;
                for (String str2 : list) {
                    try {
                        if (z) {
                            if (b(str + File.separator + str2)) {
                                z = true;
                            }
                        }
                        z = false;
                    } catch (Throwable th) {
                        th = th;
                        z2 = z;
                        af.b(f4368a, " delete err: " + th.getClass().getSimpleName());
                        return z2;
                    }
                }
            }
            if (z) {
                if (file.delete()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            return file.mkdirs();
        } catch (Exception e) {
            af.d(f4368a, "makeDirectory Exception: " + e.getMessage());
            return false;
        }
    }

    public static String a(File file) {
        if (file == null) {
            return null;
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            af.d(f4368a, "getFilePath Exception: " + e.getMessage());
            return null;
        }
    }

    public static String a(Context context) {
        return a(context.createDeviceProtectedStorageContext().getDataDir());
    }
}
