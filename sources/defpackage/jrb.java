package defpackage;

import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jrb {
    public static void b(String str, int i, int i2) {
        ReleaseLogUtil.e("R_" + str, "ServiceId = ", Integer.valueOf(i), ", CommandId = ", Integer.valueOf(i2));
    }

    public static void d(String str, Object... objArr) {
        ReleaseLogUtil.e("R_" + str, objArr);
    }
}
