package com.amap.api.col.p0003sl;

import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/* loaded from: classes2.dex */
public final class dh {
    public static void a(IGLSurfaceView iGLSurfaceView) {
        iGLSurfaceView.setEGLContextFactory(new b());
        iGLSurfaceView.setEGLConfigChooser(new a());
    }

    public static final class b extends dg {
        @Override // com.amap.api.col.p0003sl.dg, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.3sl.w.f
        public final EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            try {
                return egl10.eglCreateContext(eGLDisplay, eGLConfig, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }

        @Override // com.amap.api.col.p0003sl.dg, android.opengl.GLSurfaceView.EGLContextFactory, com.amap.api.col.3sl.w.f
        public final void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            egl10.eglDestroyContext(eGLDisplay, eGLContext);
        }
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        public int[] f968a;
        public int[] b;

        private c() {
            this.f968a = null;
            this.b = new int[1];
        }

        /* synthetic */ c(byte b) {
            this();
        }
    }

    public static final class a extends df {
        private static int g = 4;
        private int[] h = new int[1];

        /* renamed from: a, reason: collision with root package name */
        protected int f967a = 5;
        protected int b = 6;
        protected int c = 5;
        protected int d = 0;
        protected int e = 16;
        protected int f = 8;

        private int[] a(boolean z) {
            return new int[]{12324, this.f967a, 12323, this.b, 12322, this.c, 12321, this.d, 12325, this.e, 12326, this.f, 12338, z ? 1 : 0, 12352, g, 12344};
        }

        private c a(EGL10 egl10, EGLDisplay eGLDisplay) {
            c cVar = new c((byte) 0);
            cVar.f968a = a(true);
            egl10.eglChooseConfig(eGLDisplay, cVar.f968a, null, 0, cVar.b);
            if (cVar.b[0] <= 0) {
                cVar.f968a = a(false);
                egl10.eglChooseConfig(eGLDisplay, cVar.f968a, null, 0, cVar.b);
                if (cVar.b[0] <= 0) {
                    return null;
                }
            }
            return cVar;
        }

        @Override // com.amap.api.col.p0003sl.df, android.opengl.GLSurfaceView.EGLConfigChooser, com.amap.api.col.3sl.w.e
        public final EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            c a2 = a(egl10, eGLDisplay);
            if (a2 == null || a2.f968a == null) {
                return null;
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[a2.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a2.f968a, eGLConfigArr, a2.b[0], a2.b);
            EGLConfig a3 = a(egl10, eGLDisplay, eGLConfigArr);
            if (a3 != null) {
                return a3;
            }
            this.f967a = 8;
            this.b = 8;
            this.c = 8;
            c a4 = a(egl10, eGLDisplay);
            if (a4 == null || a4.f968a == null) {
                return a3;
            }
            EGLConfig[] eGLConfigArr2 = new EGLConfig[a4.b[0]];
            egl10.eglChooseConfig(eGLDisplay, a4.f968a, eGLConfigArr2, a4.b[0], a4.b);
            return a(egl10, eGLDisplay, eGLConfigArr2);
        }

        private EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12325);
                int a3 = a(egl10, eGLDisplay, eGLConfig, 12326);
                if (a2 >= this.e && a3 >= this.f) {
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12324);
                    int a5 = a(egl10, eGLDisplay, eGLConfig, 12323);
                    int a6 = a(egl10, eGLDisplay, eGLConfig, 12322);
                    int a7 = a(egl10, eGLDisplay, eGLConfig, 12321);
                    if (a4 == this.f967a && a5 == this.b && a6 == this.c && a7 == this.d) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.h)) {
                return this.h[0];
            }
            return 0;
        }
    }
}
