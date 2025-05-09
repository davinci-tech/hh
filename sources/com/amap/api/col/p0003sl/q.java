package com.amap.api.col.p0003sl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public final class q {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Integer, a> f1353a = new ConcurrentHashMap<>();

    final class a<T> {

        /* renamed from: a, reason: collision with root package name */
        public List<T> f1354a = Collections.synchronizedList(new ArrayList());
        public T b = null;

        public a() {
        }
    }

    public final <T> void a(Integer num, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap;
        if (t == null || (concurrentHashMap = this.f1353a) == null) {
            return;
        }
        try {
            a aVar = concurrentHashMap.get(num);
            if (aVar == null) {
                aVar = new a();
                this.f1353a.putIfAbsent(num, aVar);
            }
            if (aVar.f1354a == null || aVar.f1354a.contains(t)) {
                return;
            }
            aVar.f1354a.add(t);
        } catch (Throwable unused) {
        }
    }

    public final <T> void b(Integer num, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap;
        a aVar;
        if (t == null || (concurrentHashMap = this.f1353a) == null) {
            return;
        }
        try {
            if (!concurrentHashMap.containsKey(num) || (aVar = this.f1353a.get(num)) == null || aVar.f1354a == null || !aVar.f1354a.contains(t)) {
                return;
            }
            aVar.f1354a.remove(t);
        } catch (Throwable unused) {
        }
    }

    public final <T> void a(Integer num) {
        a aVar;
        try {
            if (!this.f1353a.containsKey(num) || (aVar = this.f1353a.get(num)) == null || aVar.f1354a == null) {
                return;
            }
            aVar.f1354a.clear();
        } catch (Throwable unused) {
        }
    }

    public final <T> List<T> a(int i) {
        try {
            a aVar = this.f1353a.get(Integer.valueOf(i));
            if (aVar != null) {
                return aVar.f1354a;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> void a(int i, T t) {
        ConcurrentHashMap<Integer, a> concurrentHashMap = this.f1353a;
        if (concurrentHashMap == null) {
            return;
        }
        try {
            a aVar = concurrentHashMap.get(Integer.valueOf(i));
            if (aVar == null) {
                aVar = new a();
                this.f1353a.putIfAbsent(Integer.valueOf(i), aVar);
            }
            if (aVar.b == t) {
                return;
            }
            b(Integer.valueOf(i), aVar.b);
            aVar.b = t;
            a(Integer.valueOf(i), (Integer) t);
        } catch (Throwable unused) {
        }
    }

    public final <T> void a() {
        ConcurrentHashMap<Integer, a> concurrentHashMap = this.f1353a;
        if (concurrentHashMap == null) {
            return;
        }
        try {
            Iterator<Map.Entry<Integer, a>> it = concurrentHashMap.entrySet().iterator();
            while (it.hasNext()) {
                a value = it.next().getValue();
                value.f1354a.clear();
                value.b = null;
            }
            this.f1353a.clear();
        } catch (Throwable unused) {
        }
    }
}
