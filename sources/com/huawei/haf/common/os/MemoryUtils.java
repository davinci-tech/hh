package com.huawei.haf.common.os;

import android.os.Debug;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;
import health.compact.a.ReleaseLogUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class MemoryUtils {
    public static final Runnable e = new Runnable() { // from class: com.huawei.haf.common.os.MemoryUtils.1
        @Override // java.lang.Runnable
        public void run() {
            MemoryUtils.d();
        }
    };

    /* renamed from: a, reason: collision with root package name */
    public static final Runnable f2101a = new Runnable() { // from class: com.huawei.haf.common.os.MemoryUtils.2
        @Override // java.lang.Runnable
        public void run() {
            MemoryUtils.e(true);
            MemoryUtils.xs_(null, true);
        }
    };

    private MemoryUtils() {
    }

    public static void d() {
        System.gc();
        System.runFinalization();
        System.gc();
    }

    public static long c() {
        return MemoryInfoUtil.c();
    }

    public static String xs_(Debug.MemoryInfo memoryInfo, boolean z) {
        if (memoryInfo == null) {
            memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
        }
        StringBuilder sb = new StringBuilder(256);
        MemoryInfoUtil.xm_(sb, memoryInfo, 10);
        if (z) {
            sb.append(", ver=");
            sb.append(BaseApplication.a());
            String sb2 = sb.toString();
            ReleaseLogUtil.b("HAF_MemoryUtils", sb2);
            return sb2;
        }
        return sb.toString();
    }

    public static String xt_(Debug.MemoryInfo memoryInfo) {
        if (memoryInfo == null) {
            memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
        }
        StringWriter stringWriter = new StringWriter(4096);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MemoryInfoUtil.xn_(printWriter, memoryInfo);
        printWriter.close();
        return stringWriter.toString();
    }

    public static String e(boolean z) {
        StringBuilder sb = new StringBuilder(128);
        MemoryInfoUtil.c(sb);
        String sb2 = sb.toString();
        if (z) {
            LogUtil.c("HAF_MemoryUtils", sb2);
        }
        return sb2;
    }

    public static int xu_(Debug.MemoryInfo memoryInfo, int i) {
        return MemoryInfoUtil.xp_(memoryInfo, "getOtherPss", i);
    }

    public static int xv_(Debug.MemoryInfo memoryInfo, int i) {
        return MemoryInfoUtil.xp_(memoryInfo, "getOtherSwappedOutPss", i);
    }

    public static boolean a(File file) {
        try {
            Debug.dumpHprofData(file.getPath());
            return true;
        } catch (IOException e2) {
            LogUtil.e("HAF_MemoryUtils", "dumpHprofData ex=", LogUtil.a(e2));
            return false;
        }
    }

    public static void b() {
        LogUtil.c("HAF_MemoryUtils", "trimRenderMemory");
        try {
            Class<?> d = ReflectionUtils.d("android.view.WindowManagerGlobal");
            ReflectionUtils.c(ReflectionUtils.b(d, "trimMemory", Integer.TYPE), ReflectionUtils.c(d, "getInstance"), 80);
        } catch (ClassNotFoundException unused) {
            LogUtil.e("HAF_MemoryUtils", "trimRenderMemory ClassNotFoundException.");
        } catch (NoSuchMethodException unused2) {
            LogUtil.e("HAF_MemoryUtils", "trimRenderMemory NoSuchMethodException.");
        }
    }
}
