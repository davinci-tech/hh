package defpackage;

import java.util.Map;

/* loaded from: classes3.dex */
public class bdu {
    public static bdx e(Map map, String str, bdx bdxVar) {
        Object b = b(map, str, bdxVar);
        return b instanceof bdx ? (bdx) b : bdxVar;
    }

    private static <T> Object b(Map map, String str, T t) {
        return (map == null || map.isEmpty()) ? t : map.get(str);
    }
}
