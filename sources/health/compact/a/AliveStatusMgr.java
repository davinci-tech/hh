package health.compact.a;

import android.content.Context;

/* loaded from: classes.dex */
public class AliveStatusMgr {
    private AliveStatusMgr() {
    }

    public static void c(Context context) {
        a(context);
    }

    public static long b(Context context) {
        AliveStatusRecord d = d(context);
        if (d == null || d.b()) {
            com.huawei.hwlogsmodel.LogUtil.a("Step_AliveStatusMgr", "acquireLastAliveStatusRecord is null or aliveStatusRecord.isValidTime().");
            return 0L;
        }
        return d.a();
    }

    private static AliveStatusRecord d(Context context) {
        return AliveStatusRecord.b(context);
    }

    private static void a(Context context) {
        AliveStatusRecord.c(context);
    }

    public static void e(Context context) {
        AliveStatusRecord.d(context);
    }
}
