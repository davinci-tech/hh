package com.huawei.openalliance.ad;

import android.opengl.EGL14;
import android.opengl.EGLSurface;
import android.view.Surface;

/* loaded from: classes9.dex */
public class ee {

    /* renamed from: a, reason: collision with root package name */
    private final ed f6834a;
    private EGLSurface b;

    public void e() {
        this.f6834a.a(this.b);
        this.b = EGL14.EGL_NO_SURFACE;
    }

    public void d() {
        this.f6834a.c(this.b);
    }

    public void c() {
        this.f6834a.b(this.b);
    }

    public int b() {
        return this.f6834a.a(this.b, 12374);
    }

    public int a() {
        return this.f6834a.a(this.b, 12375);
    }

    public ee(ed edVar, Surface surface) {
        this.f6834a = edVar;
        this.b = edVar.a(surface);
    }
}
