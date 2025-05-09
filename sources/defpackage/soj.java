package defpackage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class soj {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, sok> f17178a;
    private CopyOnWriteArrayList<sol> b;
    private ConcurrentHashMap<String, soo> c;
    private ConcurrentHashMap<String, sol> e;

    private soj() {
        this.b = new CopyOnWriteArrayList<>();
        this.e = new ConcurrentHashMap<>(20);
        this.c = new ConcurrentHashMap<>(20);
        this.f17178a = new ConcurrentHashMap<>(20);
    }

    public static soj a() {
        return d.c;
    }

    public ConcurrentHashMap<String, sok> d() {
        return this.f17178a;
    }

    public ConcurrentHashMap<String, soo> e() {
        return this.c;
    }

    public CopyOnWriteArrayList<sol> b() {
        return this.b;
    }

    public ConcurrentHashMap<String, sol> c() {
        return this.e;
    }

    static class d {
        private static soj c = new soj();
    }
}
