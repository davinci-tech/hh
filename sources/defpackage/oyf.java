package defpackage;

import com.huawei.haf.application.BaseApplication;
import health.compact.a.ProcessUtil;

/* loaded from: classes6.dex */
public class oyf {

    /* renamed from: a, reason: collision with root package name */
    public static final String f16004a = BaseApplication.d();
    public static final String d = ProcessUtil.b("_PhoneService");
    public static final String e = jsd.c + "com.huawei.health.h5pro";
    public static final String b = ProcessUtil.b("_DaemonService");
    private static int g = 0;
    private static boolean c = false;

    public static int a() {
        return g;
    }

    public static void c(int i) {
        g = i;
    }

    public static boolean d() {
        return c;
    }

    public static void e(boolean z) {
        c = z;
    }
}
