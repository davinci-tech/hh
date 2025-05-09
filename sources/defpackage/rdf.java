package defpackage;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rdf {
    private static Map<Integer, Integer> e = new HashMap();
    private static final Object c = new Object();

    public static int d(int i, String str) {
        int i2;
        synchronized (c) {
            if (!e.isEmpty() && e.containsKey(Integer.valueOf(i)) && e.get(Integer.valueOf(i)) != null) {
                i2 = e.get(Integer.valueOf(i)).intValue();
            } else {
                int c2 = rdu.c(i, str);
                e.put(Integer.valueOf(i), Integer.valueOf(c2));
                i2 = c2;
            }
        }
        return i2;
    }

    public static void e() {
        synchronized (c) {
            e.clear();
        }
    }
}
