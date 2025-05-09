package health.compact.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.manager.util.TotalDetailStepsCacheBean;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.OsType;
import com.huawei.up.model.UserInfomation;
import defpackage.jdl;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.UUID;

/* loaded from: classes.dex */
public class SharedPerferenceUtils {
    private SharedPerferenceUtils() {
    }

    static {
        e();
    }

    private static void e() {
        String[] list;
        File file = new File("data/data/com.huawei.health/shared_prefs");
        if (file.exists() && file.isDirectory() && (list = file.list()) != null && list.length > 0) {
            LogUtil.d("Step_SPUtils", "data/data/com.huawei.health/shared_prefs", "file list: ", Arrays.toString(list));
            for (String str : list) {
                if ("daemonService_perference.xml".equals(str) || (str.contains("daemonService_perference") && !str.contains("_newFile") && !"daemonService_perference_common.xml".equals(str))) {
                    d(str);
                }
            }
        }
    }

    private static void d(String str) {
        File file = new File("data/data/com.huawei.health/shared_prefs/" + str);
        if (file.exists()) {
            StringBuilder sb = new StringBuilder(16);
            sb.append("data/data/com.huawei.health/shared_prefs/daemonService_perference_newFile.xml");
            LogUtil.c("Step_SPUtils", "rename shared file : ", Boolean.valueOf(file.renameTo(new File(sb.toString()))));
            return;
        }
        LogUtil.c("Step_SPUtils", "rename shared file false,file is not exists");
    }

    public static void b(Context context, String str) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setVersion ", "context is null");
            return;
        }
        LogUtil.c("Step_SPUtils", "setVersion=", str);
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("plugin_daemon_version", str);
            edit.commit();
        }
    }

    public static String z(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getVersion ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        String str = UserInfomation.BIRTHDAY_UNSETED;
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("plugin_daemon_version", UserInfomation.BIRTHDAY_UNSETED);
        }
        LogUtil.c("Step_SPUtils", " getVersion= ", str);
        return str;
    }

    public static final String d() {
        try {
            return Base64.a(a().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LogUtil.a("Step_SPUtils", "getSerialNumberForMatBase64 error :", e.getMessage());
            return "";
        }
    }

    public static void e(Context context, int i) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setDeviceClassType=", Integer.valueOf(i));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putInt("device_class_type", i);
        edit.commit();
        LogUtil.c("Step_SPUtils", "setDeviceClassType success");
    }

    public static int ab(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getWriteDbLastDataMinute ", "context is null");
            return -1;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        int i = sharedPreferences != null ? sharedPreferences.getInt("write_db_last_data_time", -1) : -1;
        LogUtil.c("Step_SPUtils", " getWriteDBLastDataMinute= ", Integer.valueOf(i));
        return i;
    }

    public static void a(Context context, int i) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setWriteDbLastDataMinute=", Integer.valueOf(i));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putInt("write_db_last_data_time", i);
        edit.commit();
    }

    public static String s(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getStepsNotificationShowStatus ", "context is null");
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_common", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("steps_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.c("Step_SPUtils", " getStepsNotificationShowStatus= ", string);
        return string;
    }

    public static String i(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getStepCounterStatus ", "context is null");
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_common", 0);
        if (sharedPreferences == null) {
            return UserInfomation.BIRTHDAY_UNSETED;
        }
        String string = sharedPreferences.getString("goal_notification_status", UserInfomation.BIRTHDAY_UNSETED);
        LogUtil.c("Step_SPUtils", " getGoalNotificationShowStatus= ", string);
        return string;
    }

    public static boolean p(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getStepCounterStatus ", "context is null");
            return true;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return true;
        }
        boolean z = sharedPreferences.getBoolean("stepCounter_status", true);
        LogUtil.c("Step_SPUtils", " getStepCounterStatus= ", Boolean.valueOf(z));
        return z;
    }

    public static void b(Context context, boolean z) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setStepCounterStatus=", Boolean.valueOf(z));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putBoolean("stepCounter_status", z);
        edit.commit();
    }

    public static void e(Context context, boolean z) {
        LogUtil.c("Step_SPUtils", "setStepsNotificationShowStatus=", Boolean.valueOf(z));
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_common", 0).edit();
            String bool = Boolean.toString(z);
            if (edit != null) {
                edit.putString("steps_notification_status", bool);
                edit.commit();
            }
        }
    }

    public static void a(Context context, boolean z) {
        LogUtil.c("Step_SPUtils", "setGoalNotificationShowStatus=", Boolean.valueOf(z));
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_common", 0).edit();
            String bool = Boolean.toString(z);
            if (edit != null) {
                edit.putString("goal_notification_status", bool);
                edit.commit();
            }
        }
    }

    public static String[] x(Context context) {
        String str;
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getTodayBasicStandardSteps ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("today_basic_standard_steps", null);
            LogUtil.c("Step_SPUtils", " getTodayBasicStandardSteps= ", str);
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return new String[]{"0", "0", "0"};
        }
        String[] split = str.split("##");
        if (split == null || split.length < 3) {
            return null;
        }
        return split;
    }

    public static void b(Context context, long j, int i, int i2) {
        long t = jdl.t(j);
        LogUtil.c("Step_SPUtils", "setBasicStandardSteps basicStandardStep=", Integer.valueOf(i), " restartSteps=", Integer.valueOf(i2), "timeZero=", Long.valueOf(t));
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
            StringBuilder sb = new StringBuilder(16);
            sb.append(t);
            sb.append("##");
            sb.append(i);
            sb.append("##");
            sb.append(i2);
            String sb2 = sb.toString();
            if (edit != null) {
                edit.putString("today_basic_standard_steps", sb2);
                edit.commit();
            }
        }
    }

    public static String[] y(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getTodayTotalDetailSteps ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("today_total_detail_steps", null) : null;
        if (TextUtils.isEmpty(string)) {
            return new String[]{"0", "0", "0", "0", "0", "0"};
        }
        String[] split = string.split("##");
        if (split == null || split.length != 6) {
            return null;
        }
        return split;
    }

    public static void d(Context context, TotalDetailStepsCacheBean totalDetailStepsCacheBean) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setTodayTotalDetailSteps ", "context is null");
            return;
        }
        long t = jdl.t(totalDetailStepsCacheBean.f());
        StringBuilder sb = new StringBuilder(16);
        sb.append(t);
        sb.append("##");
        sb.append(totalDetailStepsCacheBean.j());
        sb.append("##");
        sb.append(totalDetailStepsCacheBean.g());
        sb.append("##");
        sb.append(totalDetailStepsCacheBean.a());
        sb.append("##");
        sb.append(totalDetailStepsCacheBean.b());
        sb.append("##");
        sb.append(totalDetailStepsCacheBean.d());
        String sb2 = sb.toString();
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("today_total_detail_steps", sb2);
            edit.commit();
        }
    }

    public static void e(Context context, TotalDetailStepsCacheBean totalDetailStepsCacheBean) {
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getTodayTotalDetailSteps ", "context is null");
            return;
        }
        String json = new Gson().toJson(totalDetailStepsCacheBean);
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("today_total_detail_data", json);
            edit.commit();
        }
    }

    public static TotalDetailStepsCacheBean u(Context context) {
        TotalDetailStepsCacheBean totalDetailStepsCacheBean;
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getTodayTotalDetailSteps ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return null;
        }
        try {
            totalDetailStepsCacheBean = (TotalDetailStepsCacheBean) new Gson().fromJson(sharedPreferences.getString("today_total_detail_data", null), TotalDetailStepsCacheBean.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("Step_SPUtils", "stepCardBean exception ");
            totalDetailStepsCacheBean = null;
        }
        if (totalDetailStepsCacheBean != null) {
            return totalDetailStepsCacheBean;
        }
        LogUtil.c("Step_SPUtils", "stepCardBean == null");
        return null;
    }

    public static int q(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getStepsTarget ", "context is null");
            return 10000;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        int i = sharedPreferences != null ? sharedPreferences.getInt("device_steps_target", 10000) : 10000;
        LogUtil.c("Step_SPUtils", "getStepsTarget ", Integer.valueOf(i));
        return i;
    }

    public static void b(Context context, int i) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setStepsTarget ", "context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putInt("device_steps_target", i);
            edit.commit();
        }
    }

    public static String[] g(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getDiffTotalSteps ", "context is null");
            return null;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("0##0");
        String sb2 = sb.toString();
        String sb3 = sb.toString();
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            sb3 = sharedPreferences.getString("device_diff_steps", sb3);
        }
        String[] split = sb3.split("##");
        return (split == null || split.length != 2) ? sb2.split("##") : split;
    }

    public static void d(Context context, long j, int i) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setDiffTotalSteps ", "context is null");
            return;
        }
        long t = jdl.t(j);
        StringBuilder sb = new StringBuilder(16);
        sb.append(t);
        sb.append("##");
        sb.append(i);
        String sb2 = sb.toString();
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("device_diff_steps", sb2);
            edit.commit();
        }
    }

    public static String[] h(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getDiffTotalCalories ", "context is null");
            return null;
        }
        LogUtil.c("Step_SPUtils", "getDiffTotalCalories");
        StringBuilder sb = new StringBuilder(16);
        sb.append("0##0");
        String sb2 = sb.toString();
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            sb2 = sharedPreferences.getString("device_diff_calories", sb2);
        }
        String[] split = sb2.split("##");
        if (split == null || split.length != 2) {
            return null;
        }
        return split;
    }

    public static void c(Context context, long j, double d) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setDiffTotalCalories ", "context is null");
            return;
        }
        long t = jdl.t(j);
        StringBuilder sb = new StringBuilder(16);
        sb.append(t);
        sb.append("##");
        sb.append(d);
        String sb2 = sb.toString();
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("device_diff_calories", sb2);
            edit.commit();
        }
    }

    public static String[] j(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getDiffTotalAltitude ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        String string = sharedPreferences != null ? sharedPreferences.getString("device_diff_altitude", null) : null;
        if (TextUtils.isEmpty(string)) {
            return new String[]{"0", "0"};
        }
        String[] split = string.split("##");
        if (split == null || split.length != 2) {
            return null;
        }
        return split;
    }

    public static void a(Context context, long j, double d) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setDiffTotalAltitude ", "context is null");
            return;
        }
        long t = jdl.t(j);
        StringBuilder sb = new StringBuilder(16);
        sb.append(t);
        sb.append("##");
        sb.append(d);
        String sb2 = sb.toString();
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("device_diff_altitude", sb2);
            edit.commit();
        }
    }

    public static void b(Context context, long j, double d) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setDiffTotalDistance ", "context is null");
            return;
        }
        long t = jdl.t(j);
        StringBuilder sb = new StringBuilder(16);
        sb.append(t);
        sb.append("##");
        sb.append(d);
        String sb2 = sb.toString();
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putString("device_diff_distance", sb2);
            edit.commit();
        }
    }

    public static void c(Context context, long j) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "setDeviceShutdownUtcValue ", "context is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
        if (edit != null) {
            edit.putLong("device_shut_dowm_time_utc_", j);
            edit.commit();
        }
    }

    public static long e(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getDeviceShutdownTimeUtc ", "context is null");
            return 0L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        LogUtil.c("Step_SPUtils", "getDeviceShutdownTime shutdownTimeUTC=", Long.valueOf(sharedPreferences.getLong("device_shut_dowm_time_utc_", 0L)));
        return sharedPreferences.getLong("device_shut_dowm_time_utc_", 0L);
    }

    public static String[] d(Context context) {
        String[] split;
        LogUtil.c("Step_SPUtils", "getArDebugConfig ");
        if (context == null || (split = context.getSharedPreferences("dameonservice_debug", 0).getString("ar_config", "").split("##")) == null || split.length != 3) {
            return null;
        }
        return split;
    }

    public static void c(Context context, boolean z, int i, int i2) {
        LogUtil.c("Step_SPUtils", "setArDebugConfig ", Boolean.valueOf(z), " ", Integer.valueOf(i), " ", Integer.valueOf(i2));
        if (context != null) {
            StringBuilder sb = new StringBuilder(16);
            sb.append(String.valueOf(z));
            sb.append("##");
            sb.append(String.valueOf(i));
            sb.append("##");
            sb.append(String.valueOf(i2));
            SharedPreferences.Editor edit = context.getSharedPreferences("dameonservice_debug", 0).edit();
            edit.putString("ar_config", sb.toString());
            edit.commit();
        }
    }

    public static String t(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getServiceRestartRecord ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString("service_restart_record", null);
        if (!TextUtils.isEmpty(string) && string.length() > 13) {
            string = string.substring(string.length() - 13);
        }
        String str = string;
        LogUtil.c("Step_SPUtils", " getServiceRestartRecord= ", str);
        return str;
    }

    public static void d(Context context, String str) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setServiceRestartRecord=", str);
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putString("service_restart_record", str);
        edit.commit();
    }

    public static long k(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getLastSyncDbTime ", "context is null");
            return -1L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return -1L;
        }
        long j = sharedPreferences.getLong("last_sync_db_time", -1L);
        LogUtil.c("Step_SPUtils", " getLastSyncDbTime= ", Long.valueOf(j));
        return j;
    }

    public static void e(Context context, long j) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setLastSyncDbTime=", Long.valueOf(j));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putLong("last_sync_db_time", j);
        edit.commit();
    }

    public static String[] f(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getGoalNotificationShownRecord ", "context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        String str = "";
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("goal_notifi_shown_record", "");
            LogUtil.c("Step_SPUtils", " getGoalNotificationShownRecord= ", str);
        }
        String[] split = str.split("##");
        if (split == null || split.length != 2) {
            return null;
        }
        try {
            Long.parseLong(split[0]);
        } catch (NumberFormatException unused) {
            split[0] = String.valueOf(System.currentTimeMillis());
            try {
                d(context, System.currentTimeMillis(), Boolean.parseBoolean(split[1]));
            } catch (NumberFormatException e) {
                LogUtil.c("Step_SPUtils", "NumberFormatException ", e.getMessage());
            }
        }
        return split;
    }

    public static void d(Context context, long j, boolean z) {
        long t = jdl.t(j);
        LogUtil.c("Step_SPUtils", "setGoalNotificationShownRecord ", Long.valueOf(j), " ", Boolean.valueOf(z), " timeZero=", Long.valueOf(t));
        if (context != null) {
            StringBuilder sb = new StringBuilder(16);
            sb.append(String.valueOf(t));
            sb.append("##");
            sb.append(String.valueOf(z));
            SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
            if (edit != null) {
                edit.putString("goal_notifi_shown_record", sb.toString());
                edit.commit();
            }
        }
    }

    public static void c(Context context, String str, float f, float f2, int i, int i2, int i3) {
        if (context != null) {
            StringBuilder sb = new StringBuilder(16);
            sb.append(str);
            sb.append("##");
            sb.append(f);
            sb.append("##");
            sb.append(f2);
            sb.append("##");
            sb.append(i);
            sb.append("##");
            sb.append(i2);
            sb.append("##");
            sb.append(i3);
            String sb2 = sb.toString();
            SharedPreferences.Editor edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit();
            if (edit != null) {
                edit.putString("user_info_all", sb2);
                edit.commit();
            }
        }
    }

    public static String[] v(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getUserInfoAllFromPreference ", "context is null");
            return null;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("0##0##0##0##0##0");
        String sb2 = sb.toString();
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            sb2 = sharedPreferences.getString("user_info_all", sb2);
        }
        String[] split = sb2.split("##");
        if (split == null || split.length != 6) {
            return null;
        }
        return split;
    }

    public static void c(Context context) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putBoolean("THE_MAIN_UI_START_DAEMON_SERVICE", true);
        edit.commit();
        LogUtil.c("Step_SPUtils", "setFirstStartDaemonFromUi success: ", true);
    }

    public static boolean ag(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "isFirstStartDaemonFromUi ", "context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return false;
        }
        boolean z = sharedPreferences.getBoolean("THE_MAIN_UI_START_DAEMON_SERVICE", false);
        LogUtil.c("Step_SPUtils", "isFirstStartDaemonFromUi: ", Boolean.valueOf(z));
        return z;
    }

    public static void d(Context context, int i) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putInt("THE_FIRST_INIT_HEALTH_TIME", i);
        edit.commit();
        LogUtil.c("Step_SPUtils", "setTheFirstInitHealthTime success: ", Integer.valueOf(i));
    }

    public static int r(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getTheFirstInitHealthTime ", "context is null");
            return 0;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return 0;
        }
        int i = sharedPreferences.getInt("THE_FIRST_INIT_HEALTH_TIME", 0);
        LogUtil.d("Step_SPUtils", "getTheFirs'tInitHealthTime: ", Integer.valueOf(i));
        return i;
    }

    public static void c(Context context, long j, long j2) {
        if (context != null) {
            StringBuilder sb = new StringBuilder(16);
            sb.append(j);
            sb.append("##");
            sb.append(j2);
            String sb2 = sb.toString();
            SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
            if (sharedPreferences == null) {
                LogUtil.a("Step_SPUtils", "setAliveRecord failed,sharedPreference null");
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                LogUtil.a("Step_SPUtils", "setAliveRecord failed,editor null");
                return;
            }
            edit.putString("alive_record", sb2);
            edit.commit();
            LogUtil.c("Step_SPUtils", "setAliveRecord success: ", "timestampArg=", Long.valueOf(j), " elapsedRealtimeArg=", Long.valueOf(j2));
        }
    }

    public static String[] a(Context context) {
        String sb;
        if (context != null) {
            StringBuilder sb2 = new StringBuilder(16);
            sb2.append("0##0");
            SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
            if (sharedPreferences == null) {
                LogUtil.a("Step_SPUtils", "getAliveRecord failed,sharedPreference null,return null");
                return null;
            }
            try {
                sb = sharedPreferences.getString("alive_record", sb2.toString());
            } catch (ClassCastException e) {
                LogUtil.e("Step_SPUtils", "getAliveRecord ClassCastException ", e.getMessage());
                sb = sb2.toString();
            }
            LogUtil.c("Step_SPUtils", "getAliveRecord: ", sb);
            String[] split = sb.split("##");
            if (split != null && split.length == 2) {
                return split;
            }
        }
        return null;
    }

    public static void b(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
            if (sharedPreferences == null) {
                LogUtil.a("Step_SPUtils", "deleteAliveRecord failed,can not get sharedPreference obj,warning!!!");
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit == null) {
                LogUtil.a("Step_SPUtils", "deleteAliveRecord failed,editor null,warning!!!");
                return;
            }
            edit.remove("alive_record");
            edit.commit();
            LogUtil.c("Step_SPUtils", "deleteAliveRecord success");
        }
    }

    public static void c(Context context, boolean z) {
        SharedPreferences.Editor edit;
        LogUtil.d("Step_SPUtils", "setStepCounterAbility =", Boolean.valueOf(z));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putBoolean("step_counter_ability", z);
        edit.commit();
    }

    public static boolean ae(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null) {
            return false;
        }
        try {
            sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        } catch (IncompatibleClassChangeError unused) {
            LogUtil.e("Step_SPUtils", "isStandardStepCounter IncompatibleClassChangeError exception");
            sharedPreferences = null;
        }
        if (sharedPreferences == null) {
            return false;
        }
        boolean z = sharedPreferences.getBoolean("step_counter_ability", false);
        LogUtil.d("Step_SPUtils", " isStandardStepCounter= ", Boolean.valueOf(z));
        return z;
    }

    public static void c(Context context, boolean z, long j) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setWearDeviceCapability isAbilityOne=", Boolean.valueOf(z), " firstTimeMillis = ", Long.valueOf(j));
        StringBuilder sb = new StringBuilder(16);
        sb.append(j);
        sb.append("##");
        sb.append(z);
        String sb2 = sb.toString();
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putString("WEAR_DEVICE_ABILITY", sb2);
        edit.commit();
    }

    public static String[] ac(Context context) {
        LogUtil.c("Step_SPUtils", "getWearDeviceCapability");
        StringBuilder sb = new StringBuilder(16);
        sb.append("0##false");
        String sb2 = sb.toString();
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            sb2 = sharedPreferences.getString("WEAR_DEVICE_ABILITY", sb2);
        }
        String[] split = sb2.split("##");
        if (split == null || split.length != 2) {
            return null;
        }
        return split;
    }

    public static String aa(Context context) {
        String str = null;
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getVoiceUuid context is null");
            return null;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences != null) {
            str = sharedPreferences.getString("voice_kit_uuid", null);
            LogUtil.c("Step_SPUtils", "getVoiceUuid: ", str);
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String uuid = UUID.randomUUID().toString();
        a(context, uuid);
        return uuid;
    }

    private static void a(Context context, String str) {
        SharedPreferences.Editor edit;
        LogUtil.c("Step_SPUtils", "setVoiceUuid enter");
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putString("voice_kit_uuid", str);
        edit.commit();
    }

    public static String a() {
        String str = Build.SERIAL;
        if (d(BaseApplication.getContext(), new String[]{"android.permission.READ_PHONE_STATE"})) {
            try {
                str = Build.getSerial();
            } catch (SecurityException e) {
                LogUtil.e("Step_SPUtils", "getDeviceSn SecurityException:", e.getMessage());
            }
        }
        String trim = str.trim();
        return TextUtils.isEmpty(trim) ? "unknown" : trim;
    }

    public static boolean d(Context context, String[] strArr) {
        for (String str : strArr) {
            if (context == null || (ActivityCompat.checkSelfPermission(context, str) != 0 && e(context, str))) {
                ReleaseLogUtil.b("Step_SPUtils", "isHasPermissions permissions are not granted: ", str);
                return false;
            }
        }
        return true;
    }

    private static boolean e(Context context, String str) {
        PermissionInfo permissionInfo;
        try {
            permissionInfo = context.getPackageManager().getPermissionInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("Step_SPUtils", "cannot found permission: ", str);
            permissionInfo = null;
        }
        if (permissionInfo == null) {
            return false;
        }
        LogUtil.c("Step_SPUtils", "hwext res: ", Boolean.valueOf(OsType.ANDROID.equals(permissionInfo.packageName)), " android res: ", Boolean.valueOf("androidhwext".equals(permissionInfo.packageName)));
        return OsType.ANDROID.equals(permissionInfo.packageName) || "androidhwext".equals(permissionInfo.packageName);
    }

    public static boolean m(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getIsBrowseMode ", "context is null");
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return false;
        }
        boolean z = sharedPreferences.getBoolean("daemonService_isBrowseMode", false);
        LogUtil.c("Step_SPUtils", "getIsBrowseMode: ", Boolean.valueOf(z));
        return z;
    }

    public static void d(Context context, boolean z) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putBoolean("daemonService_isBrowseMode", z);
        edit.commit();
    }

    public static long l(Context context) {
        if (context == null) {
            LogUtil.c("Step_SPUtils", "getLastQueryDbTime ", "context is null");
            return -1L;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
        if (sharedPreferences == null) {
            return -1L;
        }
        long j = sharedPreferences.getLong("last_query_db_time", -1L);
        ReleaseLogUtil.b("Step_SPUtils", " getLastQueryDbTime= ", Long.valueOf(j));
        return j;
    }

    public static void d(Context context, long j) {
        SharedPreferences.Editor edit;
        ReleaseLogUtil.b("Step_SPUtils", "setLastQueryDbTime=", Long.valueOf(j));
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putLong("last_query_db_time", j);
        edit.commit();
    }

    public static void ah(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
            int i = sharedPreferences.getInt("NOT_REPORT_TIME", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.putInt("NOT_REPORT_TIME", i + 1);
                edit.commit();
                LogUtil.c("Step_SPUtils", "setNotReportTime success: ", Integer.valueOf(i));
            }
        }
    }

    public static int o(Context context) {
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getNotReportNum context == null");
            return 0;
        }
        return context.getSharedPreferences("daemonService_perference_newFile", 0).getInt("NOT_REPORT_TIME", 0);
    }

    public static void ai(Context context) {
        if (context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("daemonService_perference_newFile", 0);
            int i = sharedPreferences.getInt("SAME_REPORT_TIME", 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.putInt("SAME_REPORT_TIME", i + 1);
                edit.commit();
                LogUtil.c("Step_SPUtils", "setSameReportNum success: ", Integer.valueOf(i));
            }
        }
    }

    public static int n(Context context) {
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getNotReportNum context == null");
            return 0;
        }
        return context.getSharedPreferences("daemonService_perference_newFile", 0).getInt("SAME_REPORT_TIME", 0);
    }

    public static void i(Context context, int i) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putInt("YESTERDAY_SUMMARY_REMIND_DAY", i);
        edit.commit();
        LogUtil.c("Step_SPUtils", "setYesterdaySummaryRemindDay success: ", Integer.valueOf(i));
    }

    public static int ad(Context context) {
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getYesterdaySummaryRemindDay context == null");
            return 0;
        }
        return context.getSharedPreferences("daemonService_perference_newFile", 0).getInt("YESTERDAY_SUMMARY_REMIND_DAY", 0);
    }

    public static int w(Context context) {
        if (context == null) {
            LogUtil.a("Step_SPUtils", "getTodaySummaryRemindDay context == null");
            return 0;
        }
        return context.getSharedPreferences("daemonService_perference_newFile", 0).getInt("TODAY_SUMMARY_REMIND_DAY", 0);
    }

    public static void c(Context context, int i) {
        SharedPreferences.Editor edit;
        if (context == null || (edit = context.getSharedPreferences("daemonService_perference_newFile", 0).edit()) == null) {
            return;
        }
        edit.putInt("TODAY_SUMMARY_REMIND_DAY", i);
        edit.commit();
        LogUtil.c("Step_SPUtils", "setTodaySummaryRemindDay success: ", Integer.valueOf(i));
    }
}
