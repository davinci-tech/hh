package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class guk {
    private static boolean c = false;

    public static void a(boolean z) {
        LogUtil.a("Track_SportWakeLockUtil", "set is sporting: ", Boolean.valueOf(z));
        if (z && e()) {
            if (c) {
                guu.c(BaseApplication.e());
                return;
            }
            return;
        }
        guu.d();
    }

    public static void e(boolean z) {
        LogUtil.a("Track_SportWakeLockUtil", "set is screenOff: ", Boolean.valueOf(z));
        c = z;
        if (z && e()) {
            if (gtx.c(BaseApplication.e()).ay()) {
                guu.c(BaseApplication.e());
                return;
            }
            return;
        }
        guu.d();
    }

    public static boolean e() {
        return gtx.c(BaseApplication.e()).n() == 264;
    }
}
