package com.amap.api.col.p0003sl;

import java.util.Objects;

/* loaded from: classes2.dex */
public abstract class bz implements cd {

    /* renamed from: a, reason: collision with root package name */
    protected int f935a;
    protected av b;

    public bz(int i, av avVar) {
        this.f935a = i;
        this.b = avVar;
    }

    public final int b() {
        return this.f935a;
    }

    public final boolean a(bz bzVar) {
        return bzVar.b() == b();
    }

    public final void b(bz bzVar) {
        b();
        bzVar.b();
        Objects.toString(getClass());
        Objects.toString(bzVar.getClass());
    }

    public void c() {
        b();
        Objects.toString(getClass());
    }

    public void d() {
        b();
        Objects.toString(getClass());
    }

    public void e() {
        b();
        Objects.toString(getClass());
    }

    public void a() {
        b();
        Objects.toString(getClass());
    }

    public void a(int i) {
        b();
        Objects.toString(getClass());
    }

    public void f() {
        b();
        Objects.toString(getClass());
    }

    public void g() {
        b();
        Objects.toString(getClass());
    }
}
