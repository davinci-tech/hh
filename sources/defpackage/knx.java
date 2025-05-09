package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.EnvironmentUtils;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes.dex */
public class knx {
    private static final String b = "AnalyticsUtils";

    public static boolean e() {
        String str = b;
        LogUtil.c(str, "enter getAnalyticsStatus");
        boolean equals = String.valueOf(true).equals(a());
        boolean z = !String.valueOf(false).equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "key_wether_to_auth"));
        LogUtil.c(str, "getAnalyticsStatus() isShareStatus = ", Boolean.valueOf(equals), ", isWhetherToAuth = ", Boolean.valueOf(z));
        return equals && z;
    }

    public static void b(boolean z) {
        LogUtil.a(b, "setShareStatus() isShareStatus = ", Boolean.valueOf(z));
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1005), "analytics_status", String.valueOf(z), new StorageParams());
        KeyValDbManager.b(BaseApplication.getContext()).e("key_user_experience_plan_check_box", String.valueOf(z));
    }

    public static String a() {
        if (EnvironmentUtils.c()) {
            return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1005), "analytics_status");
        }
        return KeyValDbManager.b(BaseApplication.getContext()).e("key_user_experience_plan_check_box");
    }

    public static void d() {
        LogUtil.a(b, "enter clearShareStatus");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1005), "analytics_status", "", new StorageParams());
        KeyValDbManager.b(BaseApplication.getContext()).e("key_user_experience_plan_check_box", "");
    }
}
