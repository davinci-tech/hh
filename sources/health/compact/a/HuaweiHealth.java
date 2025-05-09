package health.compact.a;

import android.content.Context;

/* loaded from: classes.dex */
public class HuaweiHealth {
    private static Context b;

    private HuaweiHealth() {
    }

    public static void c(Context context) {
        b = context;
    }

    public static Context a() {
        return b;
    }

    public static String b() {
        Context context = b;
        return context != null ? context.getPackageName() : "com.huawei.health";
    }
}
