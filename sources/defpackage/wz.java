package defpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class wz {
    private final Map<Integer, Integer> c = Collections.synchronizedMap(new HashMap());
    private int e = -1;
    private static final xa d = xa.c();

    /* renamed from: a, reason: collision with root package name */
    private static wz f17739a = null;

    public int a() {
        return 13;
    }

    public static wz c() {
        wz wzVar;
        synchronized (wz.class) {
            if (f17739a == null) {
                f17739a = new wz();
            }
            wzVar = f17739a;
        }
        return wzVar;
    }

    public void d(int i) {
        d.a("SdkVersionManager", "recordServerVersion serverVersion: " + i);
        this.e = i;
    }

    private boolean d() {
        return this.e != -1;
    }

    public boolean b(int i, int i2) {
        if (i2 <= 8) {
            return true;
        }
        d.a("SdkVersionManager", "bothVersionGreaterThanTargetVersion pid: " + i + ", targetVersion: " + i2);
        return d() ? Math.min(this.e, a()) >= i2 : this.c.containsKey(Integer.valueOf(i)) && Math.min(this.c.get(Integer.valueOf(i)).intValue(), a()) >= i2;
    }
}
