package defpackage;

import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class cef {
    public static String a() {
        return e() + "/dataStat/goalAchieve/querySummary";
    }

    public static String b() {
        return e() + "/dataStat/goalAchieve/queryEncourage";
    }

    private static String e() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl");
    }
}
