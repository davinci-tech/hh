package defpackage;

import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jhj {
    private static boolean c = false;

    public static void b(boolean z) {
        LogUtil.a("StressUtil", "StressUtil setIsManualSync isSync = ", Boolean.valueOf(z));
        c = z;
    }
}
