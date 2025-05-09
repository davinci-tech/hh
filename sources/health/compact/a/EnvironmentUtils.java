package health.compact.a;

import android.os.Process;
import com.huawei.haf.application.BaseApplication;

/* loaded from: classes.dex */
public class EnvironmentUtils {
    private EnvironmentUtils() {
    }

    public static boolean c() {
        return ProcessUtil.d();
    }

    public static boolean b() {
        return d().endsWith(":DaemonService");
    }

    public static boolean e() {
        return d().endsWith(":PhoneService");
    }

    public static String d() {
        return ProcessUtil.b();
    }

    public static String a() {
        return BaseApplication.a();
    }

    static String b(int i) {
        return i == Process.myPid() ? ProcessUtil.b() : ProcessUtil.a(i);
    }
}
