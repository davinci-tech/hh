package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class jqp {
    public static void a(String str) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10008), "core_sleep_switch_key_" + knl.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)), str, (StorageParams) null);
        ReleaseLogUtil.e("R_CoreSleepSwitchUtils", "setCoreSleepSwitchToSp switchValue:", str);
    }

    public static String a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10008), "core_sleep_switch_key_" + knl.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011)));
        ReleaseLogUtil.e("R_CoreSleepSwitchUtils", "getCoreSleepSwitchFromSp switchValue:", b);
        return b;
    }
}
