package health.compact.a;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import java.util.List;

/* loaded from: classes.dex */
public final class ProcessUtil {
    private static String d;

    private ProcessUtil() {
    }

    public static void c() {
        List<ActivityManager.RunningAppProcessInfo> a2 = a();
        if (a2 == null) {
            return;
        }
        int myUid = Process.myUid();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : a2) {
            if (runningAppProcessInfo.uid == myUid && runningAppProcessInfo.pid != myPid) {
                Process.killProcess(runningAppProcessInfo.pid);
            }
        }
    }

    public static boolean d(String str) {
        if (b().equals(str)) {
            return true;
        }
        List<ActivityManager.RunningAppProcessInfo> a2 = a();
        if (a2 == null) {
            return false;
        }
        int myUid = Process.myUid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : a2) {
            if (runningAppProcessInfo.uid == myUid && str.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean e() {
        List<ActivityManager.RunningAppProcessInfo> a2 = a();
        if (a2 == null) {
            return false;
        }
        int myUid = Process.myUid();
        String d2 = BaseApplication.d();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : a2) {
            if (runningAppProcessInfo.uid == myUid && runningAppProcessInfo.importance == 100 && d2.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        return b().equals(BaseApplication.d());
    }

    public static String b() {
        if (TextUtils.isEmpty(d)) {
            if (Build.VERSION.SDK_INT >= 28) {
                d = BaseApplication.getProcessName();
            }
            if (TextUtils.isEmpty(d)) {
                d = a(Process.myPid());
            }
        }
        return d;
    }

    public static String a(int i) {
        String d2 = d(i);
        return TextUtils.isEmpty(d2) ? "" : d2;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return BaseApplication.d();
        }
        char charAt = str.charAt(0);
        if (charAt == ':') {
            return (BaseApplication.d() + str).intern();
        }
        if (charAt != '_' && charAt != '-') {
            return str;
        }
        return BaseApplication.d() + str;
    }

    private static String d(int i) {
        List<ActivityManager.RunningAppProcessInfo> a2 = a();
        String str = null;
        if (a2 == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : a2) {
            if (runningAppProcessInfo.pid == i) {
                str = runningAppProcessInfo.processName;
            }
        }
        return str;
    }

    private static List<ActivityManager.RunningAppProcessInfo> a() {
        ActivityManager xx_ = CommonUtils.xx_();
        if (xx_ != null) {
            return xx_.getRunningAppProcesses();
        }
        return null;
    }
}
