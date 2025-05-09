package com.huawei.uikit.hwcolumnsystem.widget;

import defpackage.slf;

/* loaded from: classes7.dex */
public abstract class akxao {

    /* renamed from: a, reason: collision with root package name */
    public int f10621a;
    protected int b;
    protected float c;
    protected float d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public float j;
    public int k;
    public boolean l;

    public abstract float a(int i);

    public void a(int i, int i2, float f, boolean z) {
        this.f10621a = i;
        this.b = i2;
        this.c = f;
        this.l = z;
    }

    public abstract int b();

    public void b(int i) {
        this.k = i;
    }

    public abstract int c();

    public void c(int i) {
        this.g = i;
    }

    public abstract int d();

    public abstract void e();

    public void a(slf slfVar, int i, boolean z) {
        this.l = z;
        this.e = slfVar.a();
        this.f = slfVar.d();
        this.g = slfVar.e();
        this.h = slfVar.b();
        this.i = i;
        e();
    }

    public int a() {
        return this.k;
    }
}
