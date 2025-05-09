package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.google.gson.Gson;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class cas {
    public static final void b(SportLaunchParams sportLaunchParams) {
        if (sportLaunchParams == null) {
            ReleaseLogUtil.d("SportRestartUtils", "setRestartParas is null");
        } else {
            ReleaseLogUtil.e("SportRestartUtils", "setRestartParas:", sportLaunchParams.toString());
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "sport_restart_paras", new Gson().toJson(sportLaunchParams), (StorageParams) null);
        }
    }

    public static final void b() {
        ReleaseLogUtil.e("SportRestartUtils", "clearRestartData:");
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(20002), "sport_restart_paras", "", (StorageParams) null);
    }

    public static final SportLaunchParams e() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(20002), "sport_restart_paras");
        if (b == null || b.isEmpty()) {
            ReleaseLogUtil.d("SportRestartUtils", "getRestartParas is null");
            return null;
        }
        SportLaunchParams sportLaunchParams = (SportLaunchParams) ghb.a(b, SportLaunchParams.class);
        ReleaseLogUtil.e("SportRestartUtils", "getRestartParas:", sportLaunchParams.toString());
        return sportLaunchParams;
    }

    public static final void d(SportLaunchParams sportLaunchParams) {
        if (sportLaunchParams == null) {
            ReleaseLogUtil.e("SportRestartUtils", "restartSport with null paras.");
            return;
        }
        sportLaunchParams.setRestart(true);
        ReleaseLogUtil.e("SportRestartUtils", "restartSport with param:", sportLaunchParams.toString());
        if (sportLaunchParams.getLaunchActivity() == null || sportLaunchParams.getLaunchActivity().isEmpty()) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getContext().getPackageName(), sportLaunchParams.getLaunchActivity());
        intent.putExtra("bundle_key_sport_launch_paras", sportLaunchParams);
        intent.setFlags(268435456);
        try {
            BaseApplication.getContext().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SportRestartUtils", "restartSport() exception: ", LogAnonymous.b((Throwable) e));
        }
    }
}
