package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class dyd {
    private static Context d;
    private static final dyb[] b = {new dyb(-1.0E-6d, 0.0d, "UseHealthModeFrequency_0"), new dyb(0.0d, 1.0d, "UseHealthModeFrequency_1"), new dyb(1.0d, 2.0d, "UseHealthModeFrequency_2"), new dyb(2.0d, 3.0d, "UseHealthModeFrequency_3"), new dyb(3.0d, 4.0d, "UseHealthModeFrequency_4"), new dyb(4.0d, 7.0d, "UseHealthModeFrequency_5_7"), new dyb(7.0d, 11.0d, "UseHealthModeFrequency_8_11"), new dyb(11.0d, 15.0d, "UseHealthModeFrequency_12_15"), new dyb(15.0d, Double.MAX_VALUE, "UseHealthModeFrequency_15+")};
    private static final dyb[] e = {new dyb(-0.5d, 1.0d, "HealthModeActive_0"), new dyb(1.0d, 7.0d, "HealthModeActive_0_7"), new dyb(7.0d, 30.0d, "HealthModeActive_7_30"), new dyb(30.0d, 60.0d, "HealthModeActive_30_60"), new dyb(60.0d, 90.0d, "HealthModeActive_60_90"), new dyb(90.0d, 180.0d, "HealthModeActive_90_180"), new dyb(180.0d, 360.0d, "HealthModeActive_180_360"), new dyb(360.0d, Double.MAX_VALUE, "HealthModeActive_360+")};

    static class d {
        public static final dyd e = new dyd();
    }

    public static dyd d(Context context) {
        LogUtil.a("QueryRecordManager", "QueryRecordManager getInstance");
        if (context != null) {
            d = context.getApplicationContext();
        }
        return d.e;
    }

    protected List<String> d(String str) {
        ArrayList arrayList = new ArrayList(2);
        d(str, arrayList);
        c(str, arrayList);
        return arrayList;
    }

    private void d(String str, List<String> list) {
        long currentTimeMillis = System.currentTimeMillis();
        int c = dxf.d().c(new dxn(currentTimeMillis - 2592000000L, currentTimeMillis, str, AnalyticsValue.HEALTH_MODEL_HOME_CARD_2119001.value()));
        LogUtil.c("QueryRecordManager", "generateHealthModeFrequencyLabel count = ", Integer.valueOf(c));
        String str2 = "UseHealthModeFrequency_0";
        for (dyb dybVar : b) {
            str2 = dybVar.getMatchResult(c);
            if (str2 != null) {
                break;
            }
        }
        dxw.a(d).b("health_sport_health_mode_frequency", str2, str);
        list.add(str2);
    }

    private void c(String str, List<String> list) {
        long a2 = jec.a(System.currentTimeMillis());
        dxn dxnVar = new dxn(a2 - 31104000000L, a2, str, AnalyticsValue.HEALTH_MODEL_HOME_CARD_2119001.value());
        dxnVar.c(" desc ");
        dxnVar.e(1);
        String str2 = "HealthModeActive_360+";
        if (koq.b(dxf.d().d(dxnVar))) {
            LogUtil.a("QueryRecordManager", "generateHealthLifeActiveLabel queryRecord is empty");
            dxw.a(d).b("health_sport_health_mode_active", "HealthModeActive_360+", str);
            list.add("HealthModeActive_360+");
            return;
        }
        double currentTimeMillis = (System.currentTimeMillis() - r0.get(0).d()) / 8.64E7d;
        LogUtil.c("QueryRecordManager", "generateHealthLifeActiveLabel days = ", Double.valueOf(currentTimeMillis));
        for (dyb dybVar : e) {
            str2 = dybVar.getMatchResult(currentTimeMillis);
            if (str2 != null) {
                break;
            }
        }
        dxw.a(d).b("health_sport_health_mode_active", str2, str);
        list.add(str2);
    }
}
