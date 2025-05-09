package com.huawei.healthcloud.plugintrack.trackanimation.recorder.glec;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.view.Surface;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hbm;
import defpackage.hbr;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class RecorderEglRender implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a, reason: collision with root package name */
    private OnFrameCallBack f3592a;
    private long c;
    private int i;
    private long j;
    private int k;
    private boolean n;
    private Surface o;
    private int p;
    private hbr q;
    private SurfaceTexture r;
    private long t;
    private EGLDisplay d = EGL14.EGL_NO_DISPLAY;
    private EGLContext b = EGL14.EGL_NO_CONTEXT;
    private EGLContext e = EGL14.EGL_NO_CONTEXT;
    private EGLSurface g = EGL14.EGL_NO_SURFACE;
    private EGLSurface h = EGL14.EGL_NO_SURFACE;
    private boolean l = false;
    private int f = 1;
    private long m = 0;

    public interface OnFrameCallBack {
        void onUpdate();
    }

    public RecorderEglRender(int i, int i2, int i3, Surface surface, long j) {
        this.p = i;
        this.k = i2;
        this.i = i3;
        this.j = j;
        aYF_(surface);
        i();
        j();
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.l = true;
    }

    public void c(OnFrameCallBack onFrameCallBack) {
        this.f3592a = onFrameCallBack;
    }

    public Surface aYH_() {
        return this.o;
    }

    public void b() {
        this.n = false;
    }

    public void a() {
        this.n = true;
        this.t = System.currentTimeMillis();
        while (this.n) {
            d(1);
            e();
            long currentTimeMillis = System.currentTimeMillis();
            this.c = currentTimeMillis;
            if (currentTimeMillis - this.t < 280) {
                a(this.j);
            } else {
                long j = currentTimeMillis - this.m;
                long j2 = this.j;
                if (j >= j2) {
                    c();
                    this.f3592a.onUpdate();
                    b(c(this.f));
                    this.f++;
                    h();
                    this.m = this.c;
                } else {
                    a(j2 - j);
                }
            }
        }
    }

    private void a(long j) {
        if (j < 0) {
            return;
        }
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            ReleaseLogUtil.c("Track_RecorderEglRender", "start sleep ", LogAnonymous.b((Throwable) e));
        }
    }

    private void aYF_(Surface surface) {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        this.d = eglGetDisplay;
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new hbm("unable to get EGL14 display");
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(this.d, iArr, 0, iArr, 1)) {
            this.d = null;
            throw new hbm("unable to initialize EGL14");
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (!EGL14.eglChooseConfig(this.d, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12339, 1, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            throw new hbm("unable to find RGB888+recordable ES2 EGL config");
        }
        int[] iArr2 = {12440, 2, 12344};
        this.b = EGL14.eglCreateContext(this.d, eGLConfigArr[0], EGL14.EGL_NO_CONTEXT, iArr2, 0);
        a("eglCreateContext");
        if (this.b == null) {
            throw new hbm("null context");
        }
        EGLConfig aYG_ = aYG_(2);
        this.e = EGL14.eglCreateContext(this.d, aYG_, this.b, iArr2, 0);
        a("eglCreateContext");
        if (this.e == null) {
            throw new hbm("null context2");
        }
        this.g = EGL14.eglCreatePbufferSurface(this.d, eGLConfigArr[0], new int[]{12375, this.p, 12374, this.k, 12344}, 0);
        a("eglCreatePbufferSurface");
        if (this.g == null) {
            throw new hbm("surface was null");
        }
        this.h = EGL14.eglCreateWindowSurface(this.d, aYG_, surface, new int[]{12344}, 0);
        a("eglCreateWindowSurface");
        if (this.h == null) {
            throw new hbm("surface was null");
        }
    }

    private void i() {
        EGLDisplay eGLDisplay = this.d;
        EGLSurface eGLSurface = this.g;
        if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.b)) {
            throw new hbm("in makeCurrent, eglMakeCurrent failed");
        }
    }

    private void d(int i) {
        if (i == 0) {
            EGLDisplay eGLDisplay = this.d;
            EGLSurface eGLSurface = this.g;
            if (!EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.b)) {
                throw new hbm("eglMakeCurrent failed");
            }
            return;
        }
        EGLDisplay eGLDisplay2 = this.d;
        EGLSurface eGLSurface2 = this.h;
        if (!EGL14.eglMakeCurrent(eGLDisplay2, eGLSurface2, eGLSurface2, this.e)) {
            throw new hbm("eglMakeCurrent failed");
        }
    }

    private void j() {
        hbr hbrVar = new hbr(this.p, this.k);
        this.q = hbrVar;
        hbrVar.a();
        LogUtil.c("Track_RecorderEglRender", "textureID=", Integer.valueOf(this.q.d()));
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.q.d());
        this.r = surfaceTexture;
        surfaceTexture.setDefaultBufferSize(this.p, this.k);
        this.r.setOnFrameAvailableListener(this);
        this.o = new Surface(this.r);
    }

    private EGLConfig aYG_(int i) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.d, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, i >= 3 ? 68 : 4, 12344, 0, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        LogUtil.h("Track_RecorderEglRender", "unable to find RGB8888 / ", Integer.valueOf(i), " EGLConfig");
        return null;
    }

    private void a(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        throw new hbm(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
    }

    private void b(long j) {
        EGLExt.eglPresentationTimeANDROID(this.d, this.h, j);
        a("eglPresentationTimeANDROID");
    }

    private void e() {
        if (this.l) {
            this.l = false;
            this.r.updateTexImage();
        }
    }

    private boolean h() {
        boolean eglSwapBuffers = EGL14.eglSwapBuffers(this.d, this.h);
        a("eglSwapBuffers");
        return eglSwapBuffers;
    }

    private long c(int i) {
        return (i * 1000000000) / this.i;
    }

    private void c() {
        this.q.c();
    }
}
