package defpackage;

import androidx.core.view.PointerIconCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class spg {
    private static Map<Integer, Integer> b = new HashMap(16);
    private static List<Integer> e = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private static List<Integer> f17198a = new ArrayList(16);
    private static Map<Integer, Integer> d = new HashMap(16);

    static {
        c();
        j();
        g();
        b();
    }

    public static Map<Integer, Integer> e() {
        return b;
    }

    public static List<Integer> d() {
        return e;
    }

    public static List<Integer> a() {
        return f17198a;
    }

    private static void c() {
        b.put(1, 35);
        b.put(2, 35);
    }

    private static void j() {
        e.add(1005);
        e.add(1006);
        e.add(1007);
        e.add(1008);
        e.add(1009);
        e.add(1010);
        e.add(1011);
        e.add(1012);
        e.add(1013);
        e.add(1016);
        List<Integer> list = e;
        Integer valueOf = Integer.valueOf(PointerIconCompat.TYPE_GRAB);
        list.add(valueOf);
        e.add(1023);
        e.add(1024);
        e.add(1025);
        e.add(1021);
        e.add(valueOf);
        e.add(1029);
    }

    private static void g() {
        f17198a.add(1005);
        f17198a.add(1006);
        f17198a.add(1008);
        f17198a.add(1009);
        f17198a.add(1010);
        f17198a.add(1011);
        f17198a.add(1012);
        f17198a.add(1013);
        f17198a.add(1025);
        f17198a.add(1021);
        f17198a.add(Integer.valueOf(PointerIconCompat.TYPE_GRAB));
        f17198a.add(1029);
    }

    private static void b() {
        d.put(1018, 1);
    }
}
