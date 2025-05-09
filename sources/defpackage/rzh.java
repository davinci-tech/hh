package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rzh {
    private static Map<String, Object> c;

    public static <T> void e(String str, T t) {
        if (c == null) {
            c = new HashMap();
        }
        c.put(str, t);
    }

    public static <T> T d(String str, Class<T> cls) {
        Map<String, Object> map = c;
        if (map == null) {
            return null;
        }
        Object obj = map.get(str);
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        return null;
    }

    public static void d() {
        Map<String, Object> map = c;
        if (map != null) {
            map.clear();
            c = null;
        }
    }
}
