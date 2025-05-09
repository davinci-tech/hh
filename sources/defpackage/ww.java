package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Properties;

/* loaded from: classes2.dex */
public final class ww {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f17737a;
    public static final boolean b;
    public static final boolean c;
    public static final boolean d;
    public static final String e;
    public static final boolean f;
    public static final String g;
    public static final String h;
    private static final char[] i;
    public static final boolean j;
    private static Properties m;
    private static xa n;
    private static final int o;

    static {
        boolean c2 = wu.c("ro.config.hw_emui_wfd_pc_mode", false);
        j = c2;
        f17737a = c2;
        c = wu.c("ro.config.hw_emui_welink_cast", false);
        String d2 = wu.d("ro.build.characteristics", "phone");
        e = d2;
        d = d2.equalsIgnoreCase("tablet");
        b = wu.c("hw_sc.product.useBrandCust", false);
        String d3 = wu.d("debug.wfd_dump", "0");
        g = d3;
        f = d3.equalsIgnoreCase("1");
        h = wu.d("ro.build.product", "");
        o = wu.b("ro.logsystem.usertype", 0);
        i = "0123456789ABCDEF".toCharArray();
        m = null;
        n = xa.c();
    }

    public static boolean c() {
        int i2 = o;
        return i2 == 3 || i2 == 5;
    }

    public static boolean a(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    n.d("UtilMethod", "isPackageExist: PM service not found");
                    return false;
                }
                packageManager.getApplicationInfo(str, 0);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
                n.d("UtilMethod", "isPackageExist: get app info fail for " + str);
            }
        }
        return false;
    }
}
