package com.huawei.openalliance.ad;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.view.Surface;

/* loaded from: classes9.dex */
public final class ed {

    /* renamed from: a, reason: collision with root package name */
    private EGLDisplay f6833a;
    private EGLContext b;
    private EGLConfig c;
    private final int[] d = {12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344};

    protected void finalize() {
        try {
            if (this.f6833a != EGL14.EGL_NO_DISPLAY) {
                a();
            }
        } finally {
            super.finalize();
        }
    }

    void c(EGLSurface eGLSurface) {
        if (EGL14.eglSwapBuffers(this.f6833a, eGLSurface)) {
            return;
        }
        ho.a("GlEngine", "fail to update buffer");
    }

    void b(EGLSurface eGLSurface) {
        if (!EGL14.eglMakeCurrent(this.f6833a, eGLSurface, eGLSurface, this.b)) {
            throw new IllegalStateException("set current fail");
        }
    }

    public void b() {
        if (!EGL14.eglMakeCurrent(this.f6833a, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT)) {
            throw new IllegalStateException("set no current fail");
        }
    }

    void a(EGLSurface eGLSurface) {
        EGL14.eglDestroySurface(this.f6833a, eGLSurface);
    }

    public void a() {
        if (this.f6833a != EGL14.EGL_NO_DISPLAY) {
            EGL14.eglMakeCurrent(this.f6833a, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            EGL14.eglDestroyContext(this.f6833a, this.b);
            EGL14.eglReleaseThread();
            EGL14.eglTerminate(this.f6833a);
        }
        this.f6833a = EGL14.EGL_NO_DISPLAY;
        this.b = EGL14.EGL_NO_CONTEXT;
        this.c = null;
    }

    EGLSurface a(Surface surface) {
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.f6833a, this.c, surface, new int[]{12344}, 0);
        a("create window surface fail");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new IllegalStateException("gl surface is null");
    }

    int a(EGLSurface eGLSurface, int i) {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.f6833a, eGLSurface, i, iArr, 0);
        return iArr[0];
    }

    private EGLConfig e() {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.f6833a, this.d, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        ho.c("GlEngine", "fail to choose gl config");
        return null;
    }

    private void d() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.f6833a = eglGetDisplay;
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new IllegalStateException("fail to get gl display");
        }
        int[] iArr = new int[2];
        if (EGL14.eglInitialize(this.f6833a, iArr, 0, iArr, 1)) {
            return;
        }
        this.f6833a = null;
        throw new IllegalStateException("fail to init gl");
    }

    private void c() {
        EGLConfig e = e();
        if (e == null) {
            throw new IllegalStateException("fail to get config");
        }
        this.b = EGL14.eglCreateContext(this.f6833a, e, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        a("create context fail");
        this.c = e;
    }

    private void a(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        throw new IllegalStateException(str + " err code " + Integer.toHexString(eglGetError));
    }

    public ed() {
        d();
        c();
        EGL14.eglQueryContext(this.f6833a, this.b, 12440, new int[1], 0);
    }
}
