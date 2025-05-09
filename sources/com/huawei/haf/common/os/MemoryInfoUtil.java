package com.huawei.haf.common.os;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import health.compact.a.CommonUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReflectionUtils;
import java.io.PrintWriter;

/* loaded from: classes.dex */
final class MemoryInfoUtil {

    /* renamed from: a, reason: collision with root package name */
    private static long f2100a;

    private MemoryInfoUtil() {
    }

    static long c() {
        ActivityManager xx_;
        if (f2100a == 0 && (xx_ = CommonUtils.xx_()) != null) {
            f2100a = xo_(xx_).totalMem >> 20;
        }
        return f2100a;
    }

    static void xn_(PrintWriter printWriter, Debug.MemoryInfo memoryInfo) {
        try {
            AndroidPlatformV23.xq_(printWriter, memoryInfo);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            LogUtil.d("HAF_MemoryInfoUtil", "dumpMemoryInfoTable fail. ex=", LogUtil.a(e));
        }
    }

    static void xm_(StringBuilder sb, Debug.MemoryInfo memoryInfo, int i) {
        AndroidPlatformV23.xr_(sb, memoryInfo);
        sb.append(", threads=");
        sb.append(d());
        e(sb);
        if (i >= 0) {
            xl_(sb, memoryInfo, i);
        }
    }

    static void c(StringBuilder sb) {
        ActivityManager xx_ = CommonUtils.xx_();
        if (xx_ == null) {
            return;
        }
        ActivityManager.MemoryInfo xo_ = xo_(xx_);
        sb.append("System totalMem=");
        sb.append(xo_.totalMem >> 20);
        sb.append("M, availMem=");
        sb.append(xo_.availMem >> 20);
        sb.append("M, threshold=");
        sb.append(xo_.threshold >> 20);
        sb.append("M and isLowMemory=");
        sb.append(xo_.lowMemory);
        sb.append(", heapgrowthlimit=");
        sb.append(xx_.getMemoryClass());
        sb.append(", heapsize=");
        sb.append(xx_.getLargeMemoryClass());
    }

    static int xp_(Debug.MemoryInfo memoryInfo, String str, int i) {
        if (memoryInfo != null && !TextUtils.isEmpty(str)) {
            try {
                Object c = ReflectionUtils.c(ReflectionUtils.b(Debug.MemoryInfo.class, str, Integer.TYPE), memoryInfo, Integer.valueOf(i));
                if (c instanceof Integer) {
                    return ((Integer) c).intValue();
                }
            } catch (NoSuchMethodException unused) {
                LogUtil.d("HAF_MemoryInfoUtil", "getMemoryOtherInfo NoSuchMethodException");
            }
        }
        return 0;
    }

    private static ActivityManager.MemoryInfo xo_(ActivityManager activityManager) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }

    private static int d() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parent = threadGroup.getParent();
        while (true) {
            ThreadGroup threadGroup2 = parent;
            ThreadGroup threadGroup3 = threadGroup;
            threadGroup = threadGroup2;
            if (threadGroup != null) {
                parent = threadGroup.getParent();
            } else {
                return threadGroup3.activeCount();
            }
        }
    }

    private static void e(StringBuilder sb) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        sb.append(", importance=(");
        sb.append(runningAppProcessInfo.importance);
        sb.append(", ");
        sb.append(runningAppProcessInfo.importanceReasonCode);
        sb.append(Constants.RIGHT_BRACKET_ONLY);
    }

    private static void xl_(StringBuilder sb, Debug.MemoryInfo memoryInfo, int i) {
        int xp_ = xp_(memoryInfo, "getOtherPss", i);
        int xp_2 = xp_(memoryInfo, "getOtherPrivate", i);
        int xp_3 = xp_(memoryInfo, "getOtherSwappedOutPss", i);
        int xp_4 = xp_(memoryInfo, "getOtherSwappedOut", i);
        sb.append(", dex-mmap=(");
        sb.append(xp_);
        sb.append(", ");
        sb.append(xp_2);
        sb.append(", ");
        sb.append(xp_3);
        sb.append(", ");
        sb.append(xp_4);
        sb.append(Constants.RIGHT_BRACKET_ONLY);
    }

    static class AndroidPlatformV23 {
        private AndroidPlatformV23() {
        }

        static void xq_(PrintWriter printWriter, Debug.MemoryInfo memoryInfo) throws NoSuchMethodException, ClassNotFoundException {
            ReflectionUtils.c(ReflectionUtils.b(ReflectionUtils.d("android.app.ActivityThread"), "dumpMemInfoTable", PrintWriter.class, Debug.MemoryInfo.class, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Integer.TYPE, String.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE), (Object) null, printWriter, memoryInfo, false, true, false, false, Integer.valueOf(Process.myPid()), ProcessUtil.b(), 0, 0, 0, 0, 0, 0);
        }

        static void xr_(StringBuilder sb, Debug.MemoryInfo memoryInfo) {
            String memoryStat = memoryInfo.getMemoryStat("summary.java-heap");
            String memoryStat2 = memoryInfo.getMemoryStat("summary.native-heap");
            String memoryStat3 = memoryInfo.getMemoryStat("summary.code");
            String memoryStat4 = memoryInfo.getMemoryStat("summary.system");
            sb.append("total-pss=");
            sb.append(memoryInfo.getTotalPss());
            sb.append(", java-heap=");
            sb.append(memoryStat);
            sb.append(", native-heap=");
            sb.append(memoryStat2);
            sb.append(", code=");
            sb.append(memoryStat3);
            sb.append(", system=");
            sb.append(memoryStat4);
        }
    }

    static class AndroidPlatformV19 {
        private AndroidPlatformV19() {
        }
    }
}
