package defpackage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public final class ib {
    public static final Map<String, c> d = new ConcurrentHashMap();

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        public String f13325a;
        public long c;
        public long e;
    }

    public static void c(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        Map<String, c> map = d;
        c cVar = map.get(str);
        if (cVar == null) {
            cVar = new c();
        }
        cVar.f13325a = str2;
        cVar.c = 86400000L;
        cVar.e = System.currentTimeMillis();
        map.put(str, cVar);
    }

    public static String b(String str) {
        c cVar;
        String str2;
        Map<String, c> map = d;
        if (map == null || (cVar = map.get(str)) == null) {
            return null;
        }
        if (System.currentTimeMillis() - cVar.e < cVar.c && (str2 = cVar.f13325a) != null) {
            return str2;
        }
        map.remove(str);
        return null;
    }
}
