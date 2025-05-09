package com.amap.api.col.p0003sl;

import com.amap.api.col.p0003sl.fz;
import com.huawei.hms.network.embedded.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
class ga {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1063a = true;
    private long b = k.b.m;
    private int c = 10;
    private long d = 0;
    private final LinkedHashMap<fz.b, Object> e = new LinkedHashMap<>();
    private final Object f = new Object();
    private final LinkedHashMap<fz.b, Object> g = new LinkedHashMap<>();
    private final Object h = new Object();
    private ArrayList<String> i = new ArrayList<>();

    public ga(String... strArr) {
        a(strArr);
    }

    private void a(String... strArr) {
        this.d = System.currentTimeMillis();
        this.e.clear();
        this.i.clear();
        for (String str : strArr) {
            if (str != null) {
                this.i.add(str);
            }
        }
    }

    protected boolean a(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        if (linkedHashMap == null || bVar == null) {
            return false;
        }
        return linkedHashMap.containsKey(bVar);
    }

    protected Object b(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        if (linkedHashMap == null || bVar == null) {
            return null;
        }
        return linkedHashMap.get(bVar);
    }

    protected Object c(LinkedHashMap<fz.b, Object> linkedHashMap, fz.b bVar) {
        if (linkedHashMap == null || bVar == null) {
            return null;
        }
        return linkedHashMap.remove(bVar);
    }

    public final fz.c a(fz.b bVar) {
        if (!this.f1063a || bVar == null || !b(bVar)) {
            return null;
        }
        b();
        synchronized (this.f) {
            if (a(this.e, bVar)) {
                return new fz.c(b(this.e, bVar), true);
            }
            synchronized (this.h) {
                if (a(this.g, bVar)) {
                    while (!a(this.e, bVar) && a(this.g, bVar)) {
                        try {
                            this.h.wait(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    this.g.put(bVar, null);
                }
            }
            return new fz.c(b(this.e, bVar), false);
        }
    }

    public final void a(fz.b bVar, Object obj) {
        if (this.f1063a && bVar != null && b(bVar)) {
            b(bVar, obj);
            synchronized (this.h) {
                c(this.g, bVar);
                this.h.notify();
            }
        }
    }

    private void b(fz.b bVar, Object obj) {
        synchronized (this.f) {
            a();
            b();
            this.e.put(bVar, obj);
        }
    }

    private void a() {
        fz.b bVar;
        int size = this.e.size();
        if (size <= 0 || size < this.c) {
            return;
        }
        Iterator<fz.b> it = this.e.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                bVar = null;
                break;
            } else {
                bVar = it.next();
                if (bVar != null) {
                    break;
                }
            }
        }
        c(this.e, bVar);
    }

    private void b() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((currentTimeMillis - this.d) / 1000 > this.b) {
            this.e.clear();
            this.d = currentTimeMillis;
        }
    }

    public final boolean b(fz.b bVar) {
        if (bVar == null || bVar.f1059a == null) {
            return false;
        }
        Iterator<String> it = this.i.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next != null && bVar.f1059a.contains(next)) {
                return true;
            }
        }
        return false;
    }

    public void a(fz.a aVar) {
        if (aVar != null) {
            this.f1063a = aVar.a();
            this.b = aVar.b();
            this.c = aVar.c();
        }
    }
}
