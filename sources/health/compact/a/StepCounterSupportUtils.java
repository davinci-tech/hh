package health.compact.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;

/* loaded from: classes.dex */
public class StepCounterSupportUtils {
    private StepCounterSupportUtils() {
    }

    public static boolean e(Context context) {
        return a(context) && d(context);
    }

    public static boolean a(Context context) {
        if (j(context)) {
            r1 = ContextCompat.checkSelfPermission(context, "android.permission.ACTIVITY_RECOGNITION") == 0;
            ReleaseLogUtil.b("Step_StepCntSupUt", "allowStepCounter isGranted ", Boolean.valueOf(r1));
        }
        return r1;
    }

    public static boolean d(Context context) {
        if (!c(context)) {
            return true;
        }
        boolean a2 = AuthorizationUtils.a(context);
        ReleaseLogUtil.b("Step_StepCntSupUt", "allowStepCounter isAuth ", Boolean.valueOf(a2));
        return a2;
    }

    public static boolean j(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            return false;
        }
        if (!CommonUtil.bd()) {
            return true;
        }
        if (LanguageUtil.m(context)) {
            return i(context) || b(context);
        }
        return false;
    }

    public static boolean i(Context context) {
        if (EnvironmentInfo.g() || EnvironmentInfo.n()) {
            if (e(context, CommonUtil.bv() ? "step_support_cloud_config" : "step_support_cloud_config_beta")) {
                return true;
            }
        }
        return false;
    }

    public static boolean b(Context context) {
        return EnvironmentInfo.j() && DaemonServiceSpUtils.a(context);
    }

    public static boolean e(Context context, String str) {
        String e = KeyValDbManager.b(context).e("grs_third_party_" + str);
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        return CommonUtil.c(e.split(","));
    }

    public static boolean c(Context context) {
        if (CommonUtil.bd()) {
            return LanguageUtil.m(context) && (EnvironmentInfo.r() || EmuiBuild.e());
        }
        return true;
    }
}
