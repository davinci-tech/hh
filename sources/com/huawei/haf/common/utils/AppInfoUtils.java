package com.huawei.haf.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import java.io.File;

/* loaded from: classes.dex */
public class AppInfoUtils {
    private AppInfoUtils() {
    }

    public static File b(String str) {
        File dataDir = BaseApplication.e().getDataDir();
        return TextUtils.isEmpty(str) ? dataDir : new File(dataDir, str);
    }

    public static File f(String str) {
        File filesDir = BaseApplication.e().getFilesDir();
        return TextUtils.isEmpty(str) ? filesDir : new File(filesDir, str);
    }

    public static File e(String str) {
        File cacheDir = BaseApplication.e().getCacheDir();
        return TextUtils.isEmpty(str) ? cacheDir : new File(cacheDir, str);
    }

    public static File c(String str) {
        File externalCacheDir;
        File parentFile = (!"mounted".equals(Environment.getExternalStorageState()) || (externalCacheDir = BaseApplication.e().getExternalCacheDir()) == null) ? null : externalCacheDir.getParentFile();
        if (TextUtils.isEmpty(str)) {
            return parentFile;
        }
        if (parentFile != null) {
            return new File(parentFile, str);
        }
        return null;
    }

    public static File a(String str) {
        File externalFilesDir = "mounted".equals(Environment.getExternalStorageState()) ? BaseApplication.e().getExternalFilesDir(null) : null;
        if (TextUtils.isEmpty(str)) {
            return externalFilesDir;
        }
        if (externalFilesDir != null) {
            return new File(externalFilesDir, str);
        }
        return null;
    }

    public static File d(String str) {
        File externalCacheDir = "mounted".equals(Environment.getExternalStorageState()) ? BaseApplication.e().getExternalCacheDir() : null;
        if (TextUtils.isEmpty(str)) {
            return externalCacheDir;
        }
        if (externalCacheDir != null) {
            return new File(externalCacheDir, str);
        }
        return null;
    }

    public static String a() {
        return BaseApplication.d();
    }

    public static String f() {
        return BaseApplication.a();
    }

    public static int c() {
        return BaseApplication.c();
    }

    public static String b() {
        Context e = BaseApplication.e();
        return e.getApplicationInfo().loadLabel(e.getPackageManager()).toString();
    }

    public static long e() {
        PackageInfo xw_ = xw_(0);
        if (xw_ != null) {
            return xw_.firstInstallTime;
        }
        return 0L;
    }

    public static long d() {
        PackageInfo xw_ = xw_(0);
        if (xw_ != null) {
            return xw_.lastUpdateTime;
        }
        return 0L;
    }

    private static PackageInfo xw_(int i) {
        try {
            Context e = BaseApplication.e();
            return e.getPackageManager().getPackageInfo(e.getPackageName(), i);
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.a("HAF_AppInfoUtils", "getPackageInfo fail, ex=", LogUtil.a(e2));
            return null;
        }
    }
}
