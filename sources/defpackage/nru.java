package defpackage;

import android.graphics.drawable.Drawable;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class nru {
    public static int d(Map map, String str, int i) {
        Object d = d(map, str, Integer.valueOf(i));
        return d instanceof Integer ? ((Integer) d).intValue() : i;
    }

    public static float e(Map map, String str, float f) {
        Object d = d(map, str, Float.valueOf(f));
        return d instanceof Float ? ((Float) d).floatValue() : f;
    }

    public static long d(Map map, String str, long j) {
        Object d = d(map, str, Long.valueOf(j));
        return d instanceof Long ? ((Long) d).longValue() : j;
    }

    public static boolean d(Map map, String str, boolean z) {
        Object d = d(map, str, Boolean.valueOf(z));
        return d instanceof Boolean ? ((Boolean) d).booleanValue() : z;
    }

    public static String b(Map map, String str, String str2) {
        Object d = d(map, str, str2);
        return d instanceof String ? (String) d : str2;
    }

    public static CharSequence e(Map map, String str, CharSequence charSequence) {
        Object d = d(map, str, charSequence);
        return d instanceof CharSequence ? (CharSequence) d : charSequence;
    }

    public static <T> List<T> d(Map map, String str, Class<T> cls, List<T> list) {
        Object d = d(map, str, list);
        if (!(d instanceof List)) {
            return list;
        }
        List<T> list2 = (List) d;
        return (list2.size() <= 0 || !koq.e((Object) list2, (Class) cls)) ? list : list2;
    }

    public static Drawable cKj_(Map map, String str, Drawable drawable) {
        Object d = d(map, str, drawable);
        return d instanceof Drawable ? (Drawable) d : drawable;
    }

    public static OnClickSectionListener a(Map map, String str, OnClickSectionListener onClickSectionListener) {
        Object d = d(map, str, onClickSectionListener);
        return d instanceof OnClickSectionListener ? (OnClickSectionListener) d : onClickSectionListener;
    }

    public static <T> Object d(Map map, String str, T t) {
        return (map == null || map.isEmpty()) ? t : map.get(str);
    }

    public static <T> T c(Map map, String str, Class<T> cls, T t) {
        T t2 = (T) d(map, str, t);
        return cls.isInstance(t2) ? t2 : t;
    }
}
