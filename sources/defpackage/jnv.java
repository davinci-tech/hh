package defpackage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jnv {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Integer> f13975a;
    private static final HashMap<Integer, Integer> e;

    static {
        int i = 16;
        f13975a = new LinkedHashMap(i) { // from class: jnv.5
            private static final long serialVersionUID = -8254872593092335747L;

            {
                put("isSupportMultiLink", 25);
                put("isSupportPreemptLink", 26);
                put("isSupportSingleLink", 51);
            }
        };
        e = new HashMap(i) { // from class: jnv.2
            private static final long serialVersionUID = -1234088914796797210L;

            {
                put(2, 1);
                put(3, 0);
            }
        };
    }

    public static HashMap<Integer, Integer> d() {
        return e;
    }

    public static Map<String, Integer> b() {
        return f13975a;
    }
}
