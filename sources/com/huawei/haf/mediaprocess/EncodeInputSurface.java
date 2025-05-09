package com.huawei.haf.mediaprocess;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;
import health.compact.a.LogUtil;
import java.io.IOException;

/* loaded from: classes.dex */
public class EncodeInputSurface {
    private EGLContext b;
    private EGLSurface c;
    private Surface d;
    private EGLDisplay e;

    public EncodeInputSurface(Surface surface) throws IOException {
        if (surface == null) {
            LogUtil.e("EncodeInputSurface", "surface is null");
        }
        this.d = surface;
        a();
    }

    private void a() throws IOException {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.e = eglGetDisplay;
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            LogUtil.e("EncodeInputSurface", "unable to get EGL14 display");
            throw new IOException("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.e, iArr, 0, iArr, 1)) {
            this.e = null;
            LogUtil.e("EncodeInputSurface", "unable to initialize EGL14");
            throw new IOException("unable to initialize EGL14");
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.e, new int[]{12324, 8, 12323, 8, 12322, 8, 12352, 4, 12610, 1, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            LogUtil.e("EncodeInputSurface", "unable to find RGB888+recordable ES2 EGL config");
            throw new IOException("unable to find RGB888+recordable ES2 EGL config");
        }
        EGLContext eglCreateContext = EGL14.eglCreateContext(this.e, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        this.b = eglCreateContext;
        if (eglCreateContext == null) {
            LogUtil.e("EncodeInputSurface", "null eglcontext");
            throw new IOException("null eglcontext");
        }
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.e, eGLConfigArr[0], this.d, new int[]{12344}, 0);
        this.c = eglCreateWindowSurface;
        if (eglCreateWindowSurface != null) {
            return;
        }
        LogUtil.e("EncodeInputSurface", "null eglSurface");
        throw new IOException("null eglSurface");
    }

    public void c() {
        if (EGL14.eglGetCurrentContext().equals(this.b)) {
            EGL14.eglMakeCurrent(this.e, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        }
        EGL14.eglDestroySurface(this.e, this.c);
        EGL14.eglDestroyContext(this.e, this.b);
        this.d.release();
        this.e = null;
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public void d() throws IOException {
        EGLDisplay eGLDisplay = this.e;
        EGLSurface eGLSurface = this.c;
        if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.b)) {
            return;
        }
        LogUtil.e("EncodeInputSurface", "eglMakeCurrent failed");
        throw new IOException("eglMakeCurrent failed");
    }

    public boolean e() {
        return EGL14.eglSwapBuffers(this.e, this.c);
    }

    public void c(long j) {
        EGLExt.eglPresentationTimeANDROID(this.e, this.c, j);
    }
}
