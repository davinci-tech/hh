package defpackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes9.dex */
class kfx {
    private static final HashMap<Integer, Integer> d;
    private static final Map<String, Integer> e;

    static {
        int i = 16;
        e = new LinkedHashMap(i) { // from class: kfx.5
            {
                put("isSupportMultiLink", 25);
                put("isSupportPreemptLink", 26);
                put("isSupportSingleLink", 51);
            }
        };
        d = new HashMap(i) { // from class: kfx.3
            {
                put(2, 1);
                put(3, 0);
            }
        };
    }

    public static HashMap<Integer, Integer> b() {
        return d;
    }

    public static Map<String, Integer> c() {
        return e;
    }
}
