package com.huawei.ui.commonui.linechart.anchor;

import android.content.Context;
import defpackage.nmq;

/* loaded from: classes6.dex */
public class Layout {

    /* renamed from: a, reason: collision with root package name */
    public float f8852a;
    private Context ac;
    public float b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public boolean i = false;
    public float j;
    public int k;
    public int l;
    public float m;
    public float n;
    public float o;
    public float p;
    public int q;
    public float r;
    public int s;
    public float t;
    public float u;
    public float v;
    public float w;
    public float x;
    float y;

    public Layout(Context context) {
        this.ac = context;
    }

    public nmq e(float f, float f2, float f3) {
        float f4 = this.l;
        float f5 = this.c;
        float f6 = this.b;
        float f7 = this.o;
        float f8 = this.n;
        float f9 = this.q;
        float f10 = this.s;
        float f11 = this.m;
        float f12 = this.j;
        float f13 = this.e;
        float f14 = this.k;
        float f15 = this.v + f14 + this.x + this.h + this.u + this.w + this.r;
        float f16 = f14 + this.t + this.p;
        if (f16 > f15) {
            f15 = f16;
        }
        this.d = f - (((((f4 + f5) + f6) + f7) + f8) + f9);
        this.f8852a = f2 - ((((f10 + f11) + f12) + f13) + f15);
        return new nmq(this.ac, this);
    }

    public Layout c(float f, float f2) {
        this.g = f;
        this.f = f2;
        return this;
    }

    public Layout i(float f) {
        this.m = f;
        return this;
    }

    public Layout c(float f) {
        this.e = f;
        return this;
    }

    public Layout a(float f) {
        this.b = f;
        return this;
    }

    public Layout b(float f) {
        this.c = f;
        return this;
    }

    public Layout j(float f) {
        this.n = f;
        return this;
    }

    public Layout h(float f) {
        this.o = f;
        return this;
    }

    public Layout l(float f) {
        this.v = f;
        return this;
    }

    public Layout m(float f) {
        this.x = f;
        return this;
    }

    public Layout d(float f) {
        this.h = f;
        return this;
    }

    public Layout b(boolean z) {
        this.i = z;
        return this;
    }

    public float g() {
        return this.v;
    }

    public float j() {
        return this.x;
    }

    public float e() {
        return this.h;
    }

    public float a() {
        return this.b;
    }

    public float c() {
        return this.n;
    }

    public Layout e(float f) {
        this.j = f;
        return this;
    }

    public Layout k(float f) {
        this.u = f;
        return this;
    }

    public Layout n(float f) {
        this.w = f;
        return this;
    }

    public Layout o(float f) {
        this.r = f;
        return this;
    }

    public Layout g(float f) {
        this.p = f;
        return this;
    }

    public float f() {
        return this.u;
    }

    public float h() {
        return this.w;
    }

    public float b() {
        return this.r;
    }

    public float d() {
        return this.p;
    }

    public Layout f(float f) {
        this.t = f;
        return this;
    }

    public Layout s(float f) {
        this.y = f;
        return this;
    }

    public float i() {
        return this.y;
    }

    public Layout a(int i) {
        this.l = i;
        return this;
    }

    public Layout e(int i) {
        this.q = i;
        return this;
    }

    public Layout b(int i) {
        this.s = i;
        return this;
    }

    public Layout d(int i) {
        this.k = i;
        return this;
    }
}
