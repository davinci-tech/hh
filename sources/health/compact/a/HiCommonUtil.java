package health.compact.a;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class HiCommonUtil {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f13122a = true;
    public static String b = "processName";
    private static String c = null;
    public static String d = "isRunning";
    public static String e = "com.huawei.health";

    public static double b(double d2, double d3, double d4, double d5) {
        return ((d2 - ((d3 / 100.0d) * d2)) - d4) - d5;
    }

    public static <T> boolean b(T t) {
        return t == null;
    }

    public static double e(double d2) {
        return d2 * 2.2046d;
    }

    private HiCommonUtil() {
    }

    public static String d(Context context) {
        if (TextUtils.isEmpty(c)) {
            c = e(context, Process.myPid());
        }
        return c;
    }

    public static boolean b(Context context) {
        return HuaweiHealth.b().equals(d(context));
    }

    private static String e(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str = "";
        if (context == null) {
            return "";
        }
        ActivityManager activityManager = context.getSystemService("activity") instanceof ActivityManager ? (ActivityManager) context.getSystemService("activity") : null;
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }

    public static boolean b(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean d(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean e(int[] iArr) {
        return iArr == null || iArr.length <= 0;
    }

    public static final boolean c(Context context) {
        if (context == null) {
            return false;
        }
        try {
            Class<?> cls = Class.forName(context.getPackageName() + ".BuildConfig");
            if (cls.getField("BUILD_TYPE").get(null) instanceof String) {
                return "release".equals((String) cls.getField("BUILD_TYPE").get(null));
            }
            return false;
        } catch (ClassNotFoundException e2) {
            health.compact.a.util.LogUtil.e("HiCommonUtil", "isRelease ClassNotFoundException e = ", e2.getMessage());
            return false;
        } catch (IllegalAccessException e3) {
            health.compact.a.util.LogUtil.e("HiCommonUtil", "isRelease IllegalAccessException e = ", e3.getMessage());
            return false;
        } catch (NoSuchFieldException e4) {
            health.compact.a.util.LogUtil.e("HiCommonUtil", "isRelease NoSuchFieldException e = ", e4.getMessage());
            return false;
        }
    }

    public static int d(int i) {
        return (int) Math.round(i / 2.54d);
    }

    public static int a(int[] iArr, int i) {
        if (iArr == null || iArr.length == 0) {
            return -1;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("^-?\\d+(\\.\\d+)?$").matcher(str).matches();
    }

    public static boolean d(String str, long j) {
        if (Thread.currentThread().getId() != j) {
            return false;
        }
        com.huawei.hihealth.util.ReleaseLogUtil.b(str, "can't use singleInsertThread to insert data again, may Causes deadlock.");
        health.compact.a.util.LogUtil.d(str, "the deadlock dubious stackTrace is:", b());
        return true;
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            sb.append(" at ");
            sb.append(stackTraceElement);
            sb.append('\n');
        }
        return sb.toString();
    }

    public static String d(Double d2) {
        return new DecimalFormat("#.###").format(d2);
    }
}
