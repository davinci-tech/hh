package defpackage;

import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class slb {

    /* renamed from: a, reason: collision with root package name */
    private static Method f17099a = null;
    private static Method b = null;
    private static Method c = null;
    private static Method d = null;
    private static Object e = null;
    private static Class<?> g = null;
    private static boolean h = false;
    private static Class<?> i;
    private static Class<?> j;
    private static Map<Integer, String> f = new HashMap();
    private static Map<Integer, String> m = new HashMap();
    private static List<Integer> k = new ArrayList(10);

    static {
        try {
            f.put(0, "haptic.common.long_press");
            f.put(1, "haptic.common.threshold");
            f.put(2, "haptic.slide.type1");
            f.put(3, "haptic.slide.type2");
            f.put(4, "haptic.slide.type3");
            f.put(5, "haptic.slide.type4");
            f.put(6, "haptic.slide.type5");
            f.put(7, "haptic.slide.type6");
            f.put(8, "haptic.common.long_press1");
            f.put(9, "haptic.common.long_press2");
            f.put(10, "watchhaptic.crown.strength1");
            f.put(11, "watchhaptic.crown.strength2");
            f.put(12, "watchhaptic.crown.strength3");
            m.put(0, "haptic.common.long_press");
            m.put(1, "haptic.common.threshold");
            m.put(2, "haptic.control.time_scroll");
            m.put(3, "haptic.control.time_scroll");
            m.put(4, "haptic.control.time_scroll");
            m.put(5, "haptic.control.time_scroll");
            m.put(6, "haptic.control.time_scroll");
            m.put(7, "haptic.slide.type6");
            m.put(8, "haptic.common.long_press1");
            m.put(9, "haptic.common.long_press2");
            m.put(10, "watchhaptic.crown.strength1");
            m.put(11, "watchhaptic.crown.strength2");
            m.put(12, "watchhaptic.crown.strength3");
            Class<?> cls = Class.forName("com.huawei.android.os.VibratorEx");
            j = cls;
            if ("class com.huawei.android.os.VibratorEx".equals(cls.toString())) {
                e = j.newInstance();
                f17099a = j.getMethod("isSupportHwVibrator", String.class);
                d = j.getMethod("setHwVibrator", String.class);
                c = j.getMethod("stopHwVibrator", String.class);
                g = Class.forName("com.huawei.android.view.ViewEx");
                i = Class.forName("com.huawei.android.view.HapticFeedbackConstantsEx");
                Class<?> cls2 = g;
                Class<?> cls3 = Integer.TYPE;
                b = cls2.getMethod("performHwHapticFeedback", View.class, cls3, cls3);
                k.add(0, Integer.valueOf(e("HW_LONG_PRESS")));
                k.add(1, Integer.valueOf(e("HW_THRESHOLD")));
                k.add(2, Integer.valueOf(e("HW_SLIDE_1")));
                k.add(3, Integer.valueOf(e("HW_SLIDE_2")));
                k.add(4, Integer.valueOf(e("HW_SLIDE_3")));
                k.add(5, Integer.valueOf(e("HW_SLIDE_4")));
                k.add(6, Integer.valueOf(e("HW_SLIDE_5")));
                k.add(7, Integer.valueOf(e("HW_SLIDE_6")));
                k.add(8, Integer.valueOf(e("HW_LONG_PRESS1")));
                k.add(9, Integer.valueOf(e("HW_LONG_PRESS2")));
            } else {
                h = true;
                Log.e("HwVibrateUtil", "fail to reflect, class is proguard.");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException unused) {
            Log.e("HwVibrateUtil", "class init failed.");
        } catch (Exception unused2) {
            Log.e("HwVibrateUtil", "Another exception failed.");
        }
    }

    public static boolean a(String str) {
        if (!h && str != null && e != null && d != null && d(str)) {
            try {
                d.invoke(e, str);
                return true;
            } catch (IllegalAccessException unused) {
                Log.e("HwVibrateUtil", "Call doVibrateEx IllegalAccessException Exception.");
            } catch (InvocationTargetException unused2) {
                Log.e("HwVibrateUtil", "Call doVibrateEx InvocationTargetException Exception.");
            }
        }
        return false;
    }

    private static int b(String str) {
        try {
            Object obj = i.getField(str).get(null);
            if (obj instanceof Integer) {
                return ((Integer) obj).intValue();
            }
            return 0;
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            Log.e("HwVibrateUtil", "get field failed.");
            return 0;
        }
    }

    private static boolean d(String str) {
        Object obj;
        Method method = f17099a;
        if (method == null || (obj = e) == null) {
            return false;
        }
        try {
            Object invoke = method.invoke(obj, str);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            Log.e("HwVibrateUtil", "Call isSupportHwVibrator exception.");
            return false;
        }
    }

    private static int e(String str) {
        if (h || i == null) {
            return 0;
        }
        return b(str);
    }

    private static boolean a(View view, int i2, int i3) {
        if (b == null || i2 >= k.size() || i2 < 0 || !d(f.get(Integer.valueOf(i2)))) {
            return false;
        }
        try {
            b.invoke(null, view, k.get(i2), Integer.valueOf(i3));
            return true;
        } catch (IllegalAccessException unused) {
            Log.e("HwVibrateUtil", "Call doViewEx IllegalAccessException Exception.");
            return false;
        } catch (InvocationTargetException unused2) {
            Log.e("HwVibrateUtil", "Call doViewEx InvocationTargetException Exception.");
            return false;
        }
    }

    public static boolean ebC_(View view, int i2, int i3) {
        if (k.size() == 0 || !a(view, i2, i3)) {
            return a(m.get(Integer.valueOf(i2)));
        }
        return true;
    }
}
