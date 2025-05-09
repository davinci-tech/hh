package defpackage;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.constant.WatchFaceConstant;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes8.dex */
public class abo {
    private static String b = "";
    private static String e;
    private static String f;
    private static String h;
    private static String j;
    private static List<String> g = Collections.emptyList();
    private static boolean c = false;
    private static int m = 466;
    private static int o = 466;

    /* renamed from: a, reason: collision with root package name */
    private static float f166a = -1.0f;
    private static String i = WatchFaceConstant.TEMPLATES_CIRCLE_NAME;
    private static boolean d = true;

    public static void b(String str) {
        j = str;
        Log.i("GeneratorConfig", "set model path: " + j);
    }

    public static String e() throws abv {
        String str = j;
        if (str != null) {
            return str;
        }
        throw new abv("not configured");
    }

    public static void a(String str) {
        h = str;
        Log.i("GeneratorConfig", "set template path: " + h);
    }

    public static String d() throws abv {
        String str = h;
        if (str != null) {
            return str;
        }
        throw new abv("not configured");
    }

    public static void e(String str) {
        i = str;
        Log.i("GeneratorConfig", "set template name: " + i);
    }

    public static String c() {
        return i;
    }

    public static void c(int i2, int i3) {
        m = i2;
        o = i3;
        Log.i("GeneratorConfig", "set watch size: " + m + "*" + o);
    }

    public static int j() {
        return m;
    }

    public static int i() {
        return o;
    }

    public static float b() {
        return f166a;
    }

    public static String a() {
        return b;
    }

    public static void b(boolean z) {
        d = z;
        Log.i("GeneratorConfig", "set supported clock coloring: " + d);
    }

    public static boolean g() {
        return d;
    }

    public static void b(List<Integer> list) {
        g = (List) list.stream().map(new Function() { // from class: abu
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String num;
                num = Integer.toString(((Integer) obj).intValue());
                return num;
            }
        }).collect(Collectors.toList());
        Log.i("GeneratorConfig", "set supported clocks: " + g);
    }

    public static boolean d(String str) {
        if (g.isEmpty()) {
            return true;
        }
        return g.contains(str);
    }

    public static boolean f() {
        return c;
    }

    public static boolean h() {
        boolean z = !TextUtils.equals(h, f);
        if (z) {
            f = h;
        }
        boolean z2 = !TextUtils.equals(i, e);
        if (z2) {
            e = i;
        }
        return z || z2;
    }
}
