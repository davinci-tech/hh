package defpackage;

import android.app.ActionBar;
import com.huawei.android.app.ActionBarEx;
import com.huawei.hms.framework.common.EmuiUtil;

/* loaded from: classes5.dex */
public class kst {

    /* renamed from: a, reason: collision with root package name */
    private static int f14580a = -1;
    private static boolean d = false;
    private static int e;

    public static boolean i() {
        return true;
    }

    public static boolean j() {
        return true;
    }

    static {
        f();
    }

    public static boolean d() {
        return e >= 21;
    }

    public static boolean e() {
        return e >= 17;
    }

    public static boolean c() {
        return f14580a == 50;
    }

    public static boolean a() {
        return f14580a == 60;
    }

    public static boolean b() {
        return f14580a == 81;
    }

    private static void g() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.version.emui");
            if (str != null) {
                if (str.contains("EmotionUI_3.0")) {
                    f14580a = 30;
                } else if (str.contains("EmotionUI_3.1")) {
                    f14580a = 31;
                } else if (str.contains("EmotionUI_4.0")) {
                    f14580a = 40;
                } else if (str.contains("EmotionUI_4.1")) {
                    f14580a = 41;
                } else if (str.contains("EmotionUI_5.0")) {
                    f14580a = 50;
                } else if (str.contains("EmotionUI_6.0")) {
                    f14580a = 60;
                }
            }
        } catch (RuntimeException unused) {
            ksy.c("EmuiUtil", "RuntimeException getEmuiType.", true);
        } catch (Exception unused2) {
            ksy.c("EmuiUtil", "getEmuiType Exception.", true);
        }
    }

    private static void f() {
        e = h();
        ksy.b("EmuiUtil", "getEmuiType emuiVersionCode=" + e, true);
        int i = e;
        if (i >= 15) {
            f14580a = 81;
        } else if (i >= 14) {
            f14580a = 60;
        } else if (i >= 11) {
            f14580a = 50;
        } else if (i >= 10) {
            f14580a = 41;
        } else if (i >= 9) {
            f14580a = 40;
        } else if (i >= 8) {
            f14580a = 31;
        } else if (i >= 7) {
            f14580a = 30;
        }
        if (f14580a == -1) {
            g();
        }
        d = o();
        ksy.a("EmuiUtil", " init emui version is" + f14580a + ", hasActionBarEx=" + d, true);
    }

    private static int h() {
        int intValue;
        Object d2 = ksz.d(EmuiUtil.BUILDEX_VERSION, EmuiUtil.EMUI_SDK_INT);
        if (d2 != null) {
            try {
                intValue = ((Integer) d2).intValue();
            } catch (ClassCastException unused) {
                ksy.c("EmuiUtil", "getEMUIVersionCode is not a number", true);
            }
            ksy.b("EmuiUtil", "the emui version code is::" + intValue, true);
            return intValue;
        }
        intValue = 0;
        ksy.b("EmuiUtil", "the emui version code is::" + intValue, true);
        return intValue;
    }

    private static boolean o() {
        boolean z = true;
        try {
            if (f14580a != -1) {
                ActionBarEx.getDragAnimationStage((ActionBar) null);
            } else {
                z = false;
            }
            return z;
        } catch (Exception unused) {
            ksy.c("EmuiUtil", "ActionBarEx class not found: ", true);
            return false;
        }
    }
}
