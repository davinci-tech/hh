package health.compact.a;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import com.huawei.health.R;
import com.huawei.health.manager.util.UnitUtilExt;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
public class NotificationUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final double[] f13132a = {6.7d, 10.0d, 13.3d, 16.7d, 20.0d, 23.3d, 26.7d, 30.0d, 33.3d, 36.7d, 40.0d, 43.3d, 46.7d, 50.0d, 53.3d, 56.7d, 60.0d, 63.3d, 66.7d, 70.0d, 73.3d, 76.7d, 80.0d, 83.3d, 86.7d, 90.0d, 96.0d, 100.0d};
    private static final int[] e = {R.drawable._2131431122_res_0x7f0b0ed2, R.drawable._2131431132_res_0x7f0b0edc, R.drawable._2131431112_res_0x7f0b0ec8, R.drawable._2131431114_res_0x7f0b0eca, R.drawable._2131431115_res_0x7f0b0ecb, R.drawable._2131431116_res_0x7f0b0ecc, R.drawable._2131431117_res_0x7f0b0ecd, R.drawable._2131431118_res_0x7f0b0ece, R.drawable._2131431119_res_0x7f0b0ecf, R.drawable._2131431120_res_0x7f0b0ed0, R.drawable._2131431121_res_0x7f0b0ed1, R.drawable._2131431123_res_0x7f0b0ed3, R.drawable._2131431124_res_0x7f0b0ed4, R.drawable._2131431125_res_0x7f0b0ed5, R.drawable._2131431126_res_0x7f0b0ed6, R.drawable._2131431127_res_0x7f0b0ed7, R.drawable._2131431128_res_0x7f0b0ed8, R.drawable._2131431129_res_0x7f0b0ed9, R.drawable._2131431130_res_0x7f0b0eda, R.drawable._2131431131_res_0x7f0b0edb, R.drawable._2131431133_res_0x7f0b0edd, R.drawable._2131431134_res_0x7f0b0ede, R.drawable._2131431135_res_0x7f0b0edf, R.drawable._2131431136_res_0x7f0b0ee0, R.drawable._2131431137_res_0x7f0b0ee1, R.drawable._2131431138_res_0x7f0b0ee2, R.drawable._2131431139_res_0x7f0b0ee3, R.drawable._2131431140_res_0x7f0b0ee4};

    private NotificationUtil() {
    }

    public static String b(Context context, String str) {
        int parseInt;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_NotificationUtil", "context is null");
            return "";
        }
        if (str == null) {
            parseInt = 0;
        } else {
            try {
                parseInt = Integer.parseInt(str);
            } catch (Resources.NotFoundException e2) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_NotificationUtil", "getStepsString() NotFoundException", e2.getMessage());
                return null;
            } catch (NumberFormatException e3) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_NotificationUtil", "getStepsString() NumberFormatException", e3.getMessage());
                return null;
            }
        }
        return context.getResources().getString(R.string.IDS_plugindameon_hw_show_notification_steps, context.getResources().getQuantityText(R.plurals.IDS_plugindameon_hw_show_sport_timeline_steps_string, parseInt).toString().replace("%d", UnitUtilExt.e(context, parseInt, 1, 0)));
    }

    public static String a(Context context, String str) {
        int parseInt;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        if (str == null) {
            parseInt = 0;
        } else {
            try {
                parseInt = Integer.parseInt(str);
            } catch (Resources.NotFoundException e2) {
                com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "loadDayData() Exception", e2.getMessage());
                return "";
            } catch (NumberFormatException e3) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_NotificationUtil", "getHeadString() NumberFormatException", e3.getMessage());
                return "";
            }
        }
        return context.getResources().getString(R.string.IDS_plugindameon_hw_show_notification_info, context.getResources().getString(R.string.IDS_plugindameon_hw_show_sport_kcal_string, UnitUtilExt.e(context, parseInt, 1, 0)), "");
    }

    public static String a(Object obj, String str) {
        if (obj == null || str == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "object or pattern is null");
            return "";
        }
        try {
            return new DecimalFormat(str).format(obj);
        } catch (IllegalArgumentException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "formatString()", e2.getMessage());
            return null;
        }
    }

    public static void a(Context context, int i) {
        com.huawei.hwlogsmodel.LogUtil.a("Step_NotificationUtil", "deleteHealthNotification() ");
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return;
        }
        try {
            Object systemService = context.getSystemService(RemoteMessageConst.NOTIFICATION);
            if (!(systemService instanceof NotificationManager)) {
                com.huawei.hwlogsmodel.LogUtil.a("Step_NotificationUtil", "deleteHealthNotification object is not instanceof NotificationManager");
            } else {
                ((NotificationManager) systemService).cancel(i);
            }
        } catch (IllegalStateException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "deleteHealthNotification() Exception", e2.getMessage());
        }
    }

    public static PendingIntent aOw_(Context context) {
        Intent intent = null;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return null;
        }
        try {
            intent = aOv_(context);
            return PendingIntent.getActivity(context, 0, intent, 201326592);
        } catch (IllegalStateException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "getPendingIntent() Exception", e2.getMessage());
            return PendingIntent.getActivity(context, 0, intent, 201326592);
        }
    }

    public static Intent aOv_(Context context) {
        Intent launchIntentForPackage;
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return null;
        }
        Intent intent = new Intent();
        try {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        } catch (IllegalStateException e2) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "getHealthAPPIntent()", e2.getMessage());
        }
        if (launchIntentForPackage == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_NotificationUtil", "getHealthAppIntent launchIntent is null");
            return intent;
        }
        intent.setComponent(new ComponentName(context.getPackageName(), launchIntentForPackage.getComponent().getClassName()));
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setFlags(268435456);
        return intent;
    }

    public static int b(int i) {
        if (i <= 1) {
            return R.drawable._2131431111_res_0x7f0b0ec7;
        }
        int i2 = 0;
        while (true) {
            double[] dArr = f13132a;
            if (i2 >= dArr.length) {
                return R.drawable._2131431113_res_0x7f0b0ec9;
            }
            if (i < dArr[i2]) {
                return e[i2];
            }
            i2++;
        }
    }

    public static String d(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        return UnitUtilExt.e(context, i, 1, 0);
    }

    public static String c(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        return UnitUtilExt.e(context, i, 2, 0);
    }

    public static String e(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        return UnitUtilExt.e(context, Math.round(i / 1000.0f), 1, 0);
    }

    public static String b(Context context, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        return UnitUtilExt.e(context, i, 1, 0);
    }

    public static String d(Context context, float f) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_NotificationUtil", "context is null");
            return "";
        }
        return UnitUtilExt.e(context, f, 1, 2);
    }

    public static boolean a() {
        return EnvironmentInfo.b("ro.config.hw_tint", false) || Build.VERSION.SDK_INT > 28;
    }

    public static boolean j() {
        int i = Calendar.getInstance(Locale.getDefault()).get(11);
        return i >= 17 && i < 19;
    }

    public static boolean f() {
        int i = Calendar.getInstance(Locale.getDefault()).get(11);
        return i >= 17 && i < 18;
    }

    public static float d() {
        return BaseApplication.getContext().getResources().getConfiguration().fontScale;
    }

    public static boolean e() {
        return d() > 1.0f;
    }

    public static boolean b() {
        return d() > 1.45f;
    }

    public static boolean c() {
        return d() > 3.2f;
    }

    public static PendingIntent aOx_(Context context) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_NotificationUtil", "getStartAppIntent() context is null");
            return null;
        }
        Intent aOv_ = aOv_(context);
        aOv_.putExtra("mLaunchSource", 12);
        return PendingIntent.getActivity(context, 0, aOv_, 201326592);
    }
}
