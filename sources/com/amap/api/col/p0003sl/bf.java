package com.amap.api.col.p0003sl;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public final class bf {

    /* renamed from: a, reason: collision with root package name */
    private static bf f918a;
    private la b;
    private LinkedHashMap<String, lb> c = new LinkedHashMap<>();
    private boolean d = true;

    public static bf a() {
        return c();
    }

    private static bf c() {
        bf bfVar;
        synchronized (bf.class) {
            try {
                bf bfVar2 = f918a;
                if (bfVar2 == null) {
                    f918a = new bf();
                } else if (bfVar2.b == null) {
                    bfVar2.b = la.b();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            bfVar = f918a;
        }
        return bfVar;
    }

    private bf() {
        try {
            if (this.b == null) {
                this.b = la.c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d() {
        synchronized (this.c) {
            if (this.c.size() <= 0) {
                return;
            }
            for (Map.Entry<String, lb> entry : this.c.entrySet()) {
                entry.getKey();
                ((bb) entry.getValue()).a();
            }
            this.c.clear();
        }
    }

    public final void a(be beVar) {
        synchronized (this.c) {
            bb bbVar = (bb) this.c.get(beVar.b());
            if (bbVar == null) {
                return;
            }
            bbVar.a();
            this.c.remove(beVar.b());
        }
    }

    public final void a(be beVar, Context context) throws hm {
        if (!this.c.containsKey(beVar.b())) {
            bb bbVar = new bb((bv) beVar, context.getApplicationContext(), (byte) 0);
            synchronized (this.c) {
                this.c.put(beVar.b(), bbVar);
            }
        }
        this.b.a(this.c.get(beVar.b()));
    }

    public final void b() {
        d();
        this.b.e();
        this.b = null;
        e();
    }

    private static void e() {
        f918a = null;
    }

    public final void b(be beVar) {
        bb bbVar = (bb) this.c.get(beVar.b());
        if (bbVar != null) {
            synchronized (this.c) {
                bbVar.b();
                this.c.remove(beVar.b());
            }
        }
    }
}
