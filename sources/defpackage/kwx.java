package defpackage;

import com.huawei.health.sportservice.SportDataOutputApi;
import health.compact.a.LogUtil;
import health.compact.a.Services;

/* loaded from: classes5.dex */
public class kwx {
    public static boolean c() {
        return eme.b().isSportingStatus() || e() || d();
    }

    public static boolean d() {
        int b = fhp.c().b();
        LogUtil.c("Track_SportStatusUtils", "isSportOrFitRunning fitState = ", Integer.valueOf(b));
        return b == 2 || b == 5;
    }

    public static boolean e() {
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi == null || sportDataOutputApi.getStatus() == 0 || sportDataOutputApi.getStatus() == 3) {
            return false;
        }
        LogUtil.c("Track_SportStatusUtils", "getIsSporting");
        return true;
    }

    public static boolean a() {
        SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        if (sportDataOutputApi == null || sportDataOutputApi.getStatus() != 0) {
            return false;
        }
        LogUtil.c("Track_SportStatusUtils", "getIsIdle");
        return true;
    }

    public static boolean b() {
        return eme.b().getSportState() == 0 && a();
    }
}
