package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.io.File;

/* loaded from: classes5.dex */
public abstract class cw {
    public static String g(Context context) {
        if (context == null) {
            return "";
        }
        try {
            File externalCacheDir = context.getExternalCacheDir();
            if (externalCacheDir != null) {
                return ae.h(externalCacheDir);
            }
            return null;
        } catch (Exception unused) {
            ho.c("StorageUtils", "getExternalFilesDir exception, use memory card folder.");
            return null;
        }
    }

    public static String f(Context context) {
        File cacheDir;
        return (context == null || (cacheDir = context.getCacheDir()) == null) ? "" : ae.h(cacheDir);
    }

    public static String e(Context context) {
        if (context == null) {
            return "";
        }
        try {
            File externalFilesDir = context.getExternalFilesDir(null);
            if (externalFilesDir != null) {
                return ae.h(externalFilesDir);
            }
        } catch (Exception unused) {
            ho.c("StorageUtils", "getExternalFilesDir exception, use memory card folder.");
        }
        return null;
    }

    public static String d(Context context) {
        File filesDir;
        return (context == null || (filesDir = context.getFilesDir()) == null) ? "" : ae.h(filesDir);
    }

    public static String c(Context context) {
        String g;
        return (!a() || Build.VERSION.SDK_INT > 27 || (g = g(context)) == null) ? f(context) : g;
    }

    protected static boolean b() {
        try {
            if (dd.a()) {
                return Environment.isExternalStorageRemovable();
            }
            return true;
        } catch (Throwable th) {
            ho.c("StorageUtils", "isExternalStorageRemovable, " + th.getClass().getSimpleName());
            return true;
        }
    }

    public static String b(Context context) {
        String e;
        return (!a() || Build.VERSION.SDK_INT > 27 || (e = e(context)) == null) ? d(context) : e;
    }

    public static boolean a() {
        return TextUtils.equals("mounted", Environment.getExternalStorageState()) || !b();
    }

    public static String a(Context context) {
        String e;
        return (!a() || (e = e(context)) == null) ? d(context) : e;
    }
}
