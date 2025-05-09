package defpackage;

import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class jrk {
    private static List<Integer> b;
    private static jrk d;

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14036a = new Object();
    private static final Object e = new Object();

    static {
        ArrayList arrayList = new ArrayList(16);
        b = arrayList;
        arrayList.add(0);
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(5);
        b.add(6);
        b.add(7);
        b.add(8);
        b.add(9);
        b.add(10);
        b.add(12);
        b.add(13);
        b.add(14);
        b.add(15);
    }

    private jrk() {
    }

    public static jrk c() {
        jrk jrkVar;
        synchronized (f14036a) {
            if (d == null) {
                d = new jrk();
            }
            jrkVar = d;
        }
        return jrkVar;
    }

    public void a() {
        Collections.sort(b);
        LogUtil.c("ReverseCapabilityConfigUtil", "initCapability sReverseCapability: ", Arrays.asList(b));
    }

    public List<Integer> d() {
        return b;
    }
}
