package health.compact.a;

import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import defpackage.jdl;
import defpackage.ntf;

/* loaded from: classes.dex */
public class DaemonServiceSpUtils {
    private DaemonServiceSpUtils() {
    }

    public static boolean d(Context context) {
        if (context == null) {
            LogUtil.c("Step_DaemonSvcSpUt", "isDaemonActive ", "context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonservice_sp_new", 0);
        if (sharedPreferences == null) {
            return false;
        }
        boolean z = sharedPreferences.getBoolean("THE_MAIN_UI_START_DAEMON_SERVICE", false);
        ReleaseLogUtil.b("Step_DaemonSvcSpUt", "isDaemonActive: ", Boolean.valueOf(z));
        return z;
    }

    public static void c(Context context) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonservice_sp_new", 0).edit()) == null) {
            return;
        }
        edit.putBoolean("THE_MAIN_UI_START_DAEMON_SERVICE", true);
        edit.commit();
        LogUtil.c("Step_DaemonSvcSpUt", "enableDaemonActive success: ", true);
    }

    public static boolean a(Context context) {
        if (context == null) {
            LogUtil.c("Step_DaemonSvcSpUt", "isActivityRecognitionControlled ", "context is null");
            return true;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonservice_sp_new", 0);
        if (sharedPreferences == null) {
            return true;
        }
        String string = sharedPreferences.getString("HAS_CONTROLLED_BY_AR_KEY_NEW", "");
        ReleaseLogUtil.b("Step_DaemonSvcSpUt", "isActivityRecognitionControlled: ", string);
        return string != null && string.equals(Boolean.toString(true));
    }

    public static String e(Context context) {
        if (context == null) {
            LogUtil.c("Step_DaemonSvcSpUt", "hasControlledByActivityRecognition ", "context is null");
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonservice_sp_new", 0);
        if (sharedPreferences == null) {
            return "";
        }
        String string = sharedPreferences.getString("HAS_CONTROLLED_BY_AR_KEY_NEW", "");
        ReleaseLogUtil.b("Step_DaemonSvcSpUt", "hasControlledByActivityRecognition: ", string);
        return string;
    }

    public static void d(Context context, boolean z) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonservice_sp_new", 0).edit()) == null) {
            return;
        }
        edit.putString("HAS_CONTROLLED_BY_AR_KEY_NEW", Boolean.toString(z));
        edit.commit();
        ReleaseLogUtil.b("Step_DaemonSvcSpUt", "setIsControlledByAr isControlled: ", Boolean.valueOf(z));
    }

    public static void c(String str) {
        SharedPreferences.Editor edit;
        Context e = BaseApplication.e();
        if (e == null || (edit = e.getSharedPreferences("daemonservice_sp_new", 0).edit()) == null) {
            return;
        }
        edit.putString("FA_FORM_LIST_CACHE_KEY", str);
        edit.commit();
        LogUtil.c("Step_DaemonSvcSpUt", "updateFaFormList success: ", str);
    }

    public static String e() {
        SharedPreferences sharedPreferences;
        Context e = BaseApplication.e();
        if (e == null || (sharedPreferences = e.getSharedPreferences("daemonservice_sp_new", 0)) == null) {
            return "";
        }
        String string = sharedPreferences.getString("FA_FORM_LIST_CACHE_KEY", "");
        LogUtil.c("Step_DaemonSvcSpUt", "getFaFormList : ", string);
        return string;
    }

    public static void a(String str) {
        SharedPreferences.Editor edit;
        Context e = BaseApplication.e();
        if (e == null || (edit = e.getSharedPreferences("daemonservice_sp_new", 0).edit()) == null) {
            return;
        }
        edit.putString("FA_FORM_ABILITY_SET_KEY", str);
        edit.commit();
        LogUtil.c("Step_DaemonSvcSpUt", "saveFaAbility success: ", str);
    }

    public static String d() {
        SharedPreferences sharedPreferences;
        Context e = BaseApplication.e();
        if (e == null || (sharedPreferences = e.getSharedPreferences("daemonservice_sp_new", 0)) == null) {
            return "";
        }
        String string = sharedPreferences.getString("FA_FORM_ABILITY_SET_KEY", "");
        LogUtil.c("Step_DaemonSvcSpUt", "getFaAbilitySet : ", string);
        return string;
    }

    public static boolean g() {
        return jdl.d(SharedPreferenceManager.a("MODULE_DEAMON_SERVER", "ABNORMAL_STEP_DIALOG_DATE", 20140101), DateFormatUtil.b(System.currentTimeMillis())) > ntf.b().c();
    }

    public static void b(long j) {
        SharedPreferenceManager.e("MODULE_DEAMON_SERVER", "ABNORMAL_STEP_GOTO_UNDERSTAND", j);
    }

    public static long a() {
        return SharedPreferenceManager.b("MODULE_DEAMON_SERVER", "ABNORMAL_STEP_GOTO_UNDERSTAND", 0L);
    }

    public static int b() {
        return SharedPreferenceManager.a("MODULE_DEAMON_SERVER", "CONTINUE_ABNORMAL_DAY", 0);
    }

    public static void f() {
        SharedPreferenceManager.e("MODULE_DEAMON_SERVER", "CONTINUE_ABNORMAL_DAY", b() + 1);
    }

    public static void j() {
        SharedPreferenceManager.e("MODULE_DEAMON_SERVER", "CONTINUE_ABNORMAL_DAY", 0L);
    }

    public static void a(boolean z) {
        SharedPreferenceManager.e("MODULE_DEAMON_SERVER", "SHOW_ABNORMAL_STEP_DIALOG", z);
    }

    public static boolean i() {
        return SharedPreferenceManager.a("MODULE_DEAMON_SERVER", "SHOW_ABNORMAL_STEP_DIALOG", false);
    }

    public static void h() {
        SharedPreferenceManager.b("MODULE_DEAMON_SERVER", "ABNORMAL_STEP_DIALOG_DATE", DateFormatUtil.b(System.currentTimeMillis()));
    }

    public static boolean c() {
        return b() >= ntf.b().d();
    }
}
