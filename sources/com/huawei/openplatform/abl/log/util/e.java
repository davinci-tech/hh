package com.huawei.openplatform.abl.log.util;

import android.content.Context;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.io.File;

/* loaded from: classes5.dex */
public abstract class e {
    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                return c.c(externalFilesDir);
            }
        } catch (Exception unused) {
            HiAdLog.w("StorageUtils", "getExternalFilesDir exception, use memory card folder.");
        }
        return "";
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                return c.c(externalCacheDir);
            }
        } catch (Exception unused) {
            HiAdLog.w("StorageUtils", "getExternalFilesDir exception, use memory card folder.");
        }
        return "";
    }

    public static String b(Context context) {
        File filesDir;
        return (context == null || (filesDir = context.getFilesDir()) == null) ? "" : c.c(filesDir);
    }

    public static String a(Context context) {
        File cacheDir;
        return (context == null || (cacheDir = context.getCacheDir()) == null) ? "" : c.c(cacheDir);
    }
}
