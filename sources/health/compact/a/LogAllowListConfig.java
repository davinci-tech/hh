package health.compact.a;

import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LogAllowListConfig {
    private static boolean e = !"CN".equals(BaseApplication.e().getResources().getConfiguration().locale.getCountry());
    private static boolean d = true;

    /* renamed from: a, reason: collision with root package name */
    private static boolean f13125a = false;
    private static List<String> b = new ArrayList(10);
    private static List<String> i = new ArrayList(10);
    private static List<String> c = new ArrayList(10);
    private static List<String> j = new ArrayList(10);

    public static void d() {
        d = true;
    }

    public static void c() {
        d = false;
    }

    public static boolean b(int i2, String str) {
        if (!LogConfig.c(i2)) {
            return false;
        }
        if (!d || LogConfig.d()) {
            return true;
        }
        return b(e(), str);
    }

    public static boolean d(String str, Object obj) {
        boolean z;
        synchronized (obj) {
            z = e;
        }
        if (z) {
            return b(f(), str);
        }
        return true;
    }

    private static boolean b(List<String> list, String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(95)) > 0) {
            return list.contains(str.substring(0, indexOf));
        }
        return false;
    }

    public static void b() {
        f13125a = true;
    }

    public static void a() {
        f13125a = false;
    }

    public static boolean e(String str, Object obj) {
        if (str == null) {
            Log.w("LogAllowListConfig", "checkAllowLogcat null");
            return false;
        }
        if ("HWWEAR".equals(str)) {
            return false;
        }
        if (!f13125a || LogConfig.d()) {
            return true;
        }
        return d(str, obj);
    }

    public void a(List<String> list) {
        b.addAll(list);
    }

    public void b(List<String> list) {
        c.addAll(list);
    }

    public static List<String> e() {
        return b;
    }

    public static List<String> f() {
        return c;
    }

    public static boolean h() {
        return e;
    }

    public static void e(boolean z) {
        e = z;
    }
}
