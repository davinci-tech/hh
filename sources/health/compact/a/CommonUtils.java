package health.compact.a;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.openalliance.ad.constant.Constants;
import java.io.File;

/* loaded from: classes.dex */
public class CommonUtils {
    protected CommonUtils() {
    }

    public static int h(String str) {
        return e(str, 0);
    }

    public static int e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        String trim = str.trim();
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException e) {
            LogUtil.e("HAF_CommonUtil", "getInteger ex=", e.getMessage());
            return (int) b(trim, i, true);
        }
    }

    public static float j(String str) {
        return c(str, 0.0f);
    }

    public static float c(String str, float f) {
        return TextUtils.isEmpty(str) ? f : b(str.trim(), f, false);
    }

    private static float b(String str, float f, boolean z) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            if (!z) {
                LogUtil.e("HAF_CommonUtil", "getFloat ex=", e.getMessage());
            }
            return f;
        }
    }

    public static double a(String str) {
        return b(str, 0.0d);
    }

    public static double b(String str, double d) {
        if (TextUtils.isEmpty(str)) {
            return d;
        }
        try {
            return Double.valueOf(str.trim()).doubleValue();
        } catch (NumberFormatException e) {
            LogUtil.e("HAF_CommonUtil", "getDouble ex=", e.getMessage());
            return d;
        }
    }

    public static long g(String str) {
        return b(str, 0L);
    }

    public static long b(String str, long j) {
        if (TextUtils.isEmpty(str)) {
            return j;
        }
        try {
            return Long.parseLong(str.trim());
        } catch (NumberFormatException e) {
            LogUtil.e("HAF_CommonUtil", "getLong ex=", e.getMessage());
            return j;
        }
    }

    public static long b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str.trim(), i);
        } catch (NumberFormatException e) {
            LogUtil.e("HAF_CommonUtil", "getLongByRadix ex=", e.getMessage());
            return 0L;
        }
    }

    public static int c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str.trim(), i);
        } catch (NumberFormatException e) {
            LogUtil.e("HAF_CommonUtil", "getIntegerByRadix ex=", e.getMessage());
            return 0;
        }
    }

    public static ActivityManager xx_() {
        Object systemService = BaseApplication.e().getSystemService("activity");
        if (systemService instanceof ActivityManager) {
            return (ActivityManager) systemService;
        }
        return null;
    }

    public static WindowManager xF_() {
        Object systemService = BaseApplication.e().getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (systemService instanceof WindowManager) {
            return (WindowManager) systemService;
        }
        return null;
    }

    public static ConnectivityManager xz_() {
        Object systemService = BaseApplication.e().getSystemService("connectivity");
        if (systemService instanceof ConnectivityManager) {
            return (ConnectivityManager) systemService;
        }
        return null;
    }

    public static TelephonyManager xE_() {
        Object systemService = BaseApplication.e().getSystemService("phone");
        if (systemService instanceof TelephonyManager) {
            return (TelephonyManager) systemService;
        }
        return null;
    }

    public static PowerManager xC_() {
        Object systemService = BaseApplication.e().getSystemService("power");
        if (systemService instanceof PowerManager) {
            return (PowerManager) systemService;
        }
        return null;
    }

    public static AlarmManager xy_() {
        Object systemService = BaseApplication.e().getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (systemService instanceof AlarmManager) {
            return (AlarmManager) systemService;
        }
        return null;
    }

    public static LocationManager xA_() {
        Object systemService = BaseApplication.e().getSystemService("location");
        if (systemService instanceof LocationManager) {
            return (LocationManager) systemService;
        }
        return null;
    }

    public static SensorManager xD_() {
        Object systemService = BaseApplication.e().getSystemService("sensor");
        if (systemService instanceof SensorManager) {
            return (SensorManager) systemService;
        }
        return null;
    }

    public static NotificationManager xB_() {
        Object systemService = BaseApplication.e().getSystemService(RemoteMessageConst.NOTIFICATION);
        if (systemService instanceof NotificationManager) {
            return (NotificationManager) systemService;
        }
        return null;
    }

    public static String i(String str) {
        File filesDir = BaseApplication.e().getFilesDir();
        if (!TextUtils.isEmpty(str)) {
            filesDir = new File(filesDir, str);
        }
        String path = filesDir.getPath();
        if (path.endsWith(File.separator)) {
            return path;
        }
        return path + File.separator;
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return BaseApplication.d();
        }
        return (BaseApplication.d() + str).intern();
    }
}
