package defpackage;

import defpackage.uzx;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes7.dex */
public class vac<T extends uzx<?>> implements Iterable<T> {

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentMap<String, T> f17628a;
    private final String c;

    public vac(String str) {
        this.f17628a = new ConcurrentHashMap();
        this.c = str;
    }

    public vac(vac<T> vacVar) {
        this(vacVar.d(), vacVar);
    }

    public vac(String str, vac<T> vacVar) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        this.f17628a = concurrentHashMap;
        this.c = str;
        concurrentHashMap.putAll(vacVar.f17628a);
    }

    public String d() {
        return this.c;
    }

    public vac<T> d(T t) {
        T e = e(t);
        if (e == null || e == t) {
            return this;
        }
        throw new IllegalArgumentException(this.c + " already contains " + t.getKey() + "!");
    }

    public T e(T t) {
        t.getClass();
        return this.f17628a.putIfAbsent(t.getKey(), t);
    }

    public boolean b(T t) {
        return t == c(t.getKey());
    }

    public T c(String str) {
        return this.f17628a.get(str);
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.f17628a.values().iterator();
    }
}
