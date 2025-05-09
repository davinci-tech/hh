package health.compact.a;

import android.os.Build;

/* loaded from: classes.dex */
public class BaseBuild {
    private static final Class e = d();

    /* renamed from: a, reason: collision with root package name */
    private static final int f13104a = f();
    private static final boolean d = j();

    public static boolean e() {
        return "HONOR".equals(Build.MANUFACTURER);
    }

    protected static boolean c() {
        return e != null;
    }

    protected static int a() {
        return f13104a;
    }

    protected static boolean b() {
        return d;
    }

    private static int f() {
        if (!e() && c()) {
            return SystemProperties.b("ro.build.hw_emui_api_level", 0);
        }
        return 0;
    }

    private static Class d() {
        try {
            return ReflectionUtils.d("com.huawei.android.os.BuildEx");
        } catch (ClassNotFoundException e2) {
            LogUtil.e("HAF_SystemBuild", "getEmuiBuildEx ex=", LogUtil.a(e2));
            return null;
        }
    }

    private static boolean j() {
        String str;
        Object c;
        if (e()) {
            return false;
        }
        try {
            c = ReflectionUtils.c(ReflectionUtils.d("com.huawei.system.BuildEx"), "getOsBrand");
        } catch (ClassNotFoundException e2) {
            LogUtil.e("HAF_SystemBuild", "isHarmonySystemImpl ex=", LogUtil.a(e2));
        } catch (Exception e3) {
            LogUtil.e("HAF_SystemBuild", "isHarmonySystemImpl ex=", LogUtil.a(e3));
        }
        if (c instanceof String) {
            str = (String) c;
            return "harmony".equals(str);
        }
        str = "";
        return "harmony".equals(str);
    }
}
