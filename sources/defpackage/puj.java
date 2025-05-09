package defpackage;

import com.huawei.haf.application.BaseApplication;
import health.compact.a.GRSManager;

/* loaded from: classes6.dex */
public class puj {
    private static String d() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl");
    }

    public static String b() {
        return d() + "/healthExpansion/3rdService/getDoctorBasicInfo";
    }

    public static String e() {
        return d() + "/healthExpansion/3rdService/getDoctorImInfo";
    }

    public static String a() {
        return d() + "/healthExpansion/3rdService/queryExclusiveGuardianStatus";
    }
}
