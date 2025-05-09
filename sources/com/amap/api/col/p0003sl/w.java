package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.TextureView;
import com.amap.api.maps.MapsInitializer;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes2.dex */
public class w extends TextureView implements TextureView.SurfaceTextureListener {

    /* renamed from: a, reason: collision with root package name */
    private static final j f1367a = new j(0);
    private final WeakReference<w> b;
    private i c;
    private GLSurfaceView.Renderer d;
    private boolean e;
    private e f;
    private f g;
    private g h;
    private k i;
    private int j;
    private int k;
    private boolean l;

    public interface e {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface f {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface g {
        EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface k {
        GL a();
    }

    private static boolean f() {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public w(Context context) {
        super(context, null);
        this.b = new WeakReference<>(this);
        a();
    }

    protected void finalize() throws Throwable {
        try {
            i iVar = this.c;
            if (iVar != null) {
                iVar.g();
            }
        } finally {
            super.finalize();
        }
    }

    private void a() {
        setSurfaceTextureListener(this);
    }

    public void setRenderer(GLSurfaceView.Renderer renderer) {
        e();
        if (this.f == null) {
            this.f = new m();
        }
        byte b2 = 0;
        if (this.g == null) {
            this.g = new c(this, b2);
        }
        if (this.h == null) {
            this.h = new d(b2);
        }
        this.d = renderer;
        i iVar = new i(this.b);
        this.c = iVar;
        iVar.start();
    }

    public final void a(f fVar) {
        e();
        this.g = fVar;
    }

    public final void a(e eVar) {
        e();
        this.f = eVar;
    }

    public void setRenderMode(int i2) {
        this.c.a(i2);
    }

    public void requestRender() {
        this.c.b();
    }

    public int getRenderMode() {
        return this.c.a();
    }

    public void b() {
        this.c.e();
    }

    public void c() {
        this.c.f();
    }

    public void queueEvent(Runnable runnable) {
        this.c.a(runnable);
    }

    @Override // android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.e && this.d != null) {
            i iVar = this.c;
            int a2 = iVar != null ? iVar.a() : 1;
            i iVar2 = new i(this.b);
            this.c = iVar2;
            if (a2 != 1) {
                iVar2.a(a2);
            }
            this.c.start();
        }
        this.e = false;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        i iVar = this.c;
        if (iVar != null) {
            iVar.g();
        }
        this.e = true;
        super.onDetachedFromWindow();
    }

    final class c implements f {
        private c() {
        }

        /* synthetic */ c(w wVar, byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.w.f
        public final EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = {12440, w.this.k, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (w.this.k == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        @Override // com.amap.api.col.3sl.w.f
        public final void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                return;
            }
            Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
            h.a("eglDestroyContex", egl10.eglGetError());
        }
    }

    static final class d implements g {
        private d() {
        }

        /* synthetic */ d(byte b) {
            this();
        }

        @Override // com.amap.api.col.3sl.w.g
        public final EGLSurface a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            try {
                return egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (IllegalArgumentException e) {
                Log.e("GLSurfaceView", "eglCreateWindowSurface", e);
                return null;
            }
        }

        @Override // com.amap.api.col.3sl.w.g
        public final void a(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    abstract class a implements e {

        /* renamed from: a, reason: collision with root package name */
        protected int[] f1368a;

        abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(int[] iArr) {
            this.f1368a = a(iArr);
        }

        @Override // com.amap.api.col.3sl.w.e
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (!egl10.eglChooseConfig(eGLDisplay, this.f1368a, null, 0, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig failed");
            }
            int i = iArr[0];
            if (i <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] eGLConfigArr = new EGLConfig[i];
            if (!egl10.eglChooseConfig(eGLDisplay, this.f1368a, eGLConfigArr, i, iArr)) {
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            EGLConfig a2 = a(egl10, eGLDisplay, eGLConfigArr);
            if (a2 != null) {
                return a2;
            }
            throw new IllegalArgumentException("No config chosen");
        }

        private int[] a(int[] iArr) {
            if (w.this.k != 2 && w.this.k != 3) {
                return iArr;
            }
            int length = iArr.length;
            int[] iArr2 = new int[length + 2];
            int i = length - 1;
            System.arraycopy(iArr, 0, iArr2, 0, i);
            iArr2[i] = 12352;
            if (w.this.k == 2) {
                iArr2[length] = 4;
            } else {
                iArr2[length] = 64;
            }
            iArr2[length + 1] = 12344;
            return iArr2;
        }
    }

    class b extends a {
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        private int[] j;

        public b() {
            super(new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 0, 12325, 16, 12326, 0, 12344});
            this.j = new int[1];
            this.c = 8;
            this.d = 8;
            this.e = 8;
            this.f = 0;
            this.g = 16;
            this.h = 0;
        }

        @Override // com.amap.api.col.3sl.w.a
        public final EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12325);
                int a3 = a(egl10, eGLDisplay, eGLConfig, 12326);
                if (a2 >= this.g && a3 >= this.h) {
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12324);
                    int a5 = a(egl10, eGLDisplay, eGLConfig, 12323);
                    int a6 = a(egl10, eGLDisplay, eGLConfig, 12322);
                    int a7 = a(egl10, eGLDisplay, eGLConfig, 12321);
                    if (a4 == this.c && a5 == this.d && a6 == this.e && a7 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.j)) {
                return this.j[0];
            }
            return 0;
        }
    }

    final class m extends b {
        public m() {
            super();
        }
    }

    static final class h {

        /* renamed from: a, reason: collision with root package name */
        EGL10 f1370a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        EGLContext e;
        private WeakReference<w> f;

        public h(WeakReference<w> weakReference) {
            this.f = weakReference;
        }

        public final void a() {
            EGL10 egl10 = (EGL10) EGLContext.getEGL();
            this.f1370a = egl10;
            EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            this.b = eglGetDisplay;
            if (eglGetDisplay == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (!this.f1370a.eglInitialize(this.b, new int[2])) {
                throw new RuntimeException("eglInitialize failed");
            }
            w wVar = this.f.get();
            if (wVar != null) {
                this.d = wVar.f.chooseConfig(this.f1370a, this.b);
                this.e = wVar.g.createContext(this.f1370a, this.b, this.d);
            } else {
                this.d = null;
                this.e = null;
            }
            EGLContext eGLContext = this.e;
            if (eGLContext == null || eGLContext == EGL10.EGL_NO_CONTEXT) {
                this.e = null;
                a("createContext");
            }
            this.c = null;
        }

        public final boolean b() {
            if (this.f1370a == null) {
                throw new RuntimeException("egl not initialized");
            }
            if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            }
            if (this.d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            }
            g();
            w wVar = this.f.get();
            if (wVar != null) {
                this.c = wVar.h.a(this.f1370a, this.b, this.d, wVar.getSurfaceTexture());
            } else {
                this.c = null;
            }
            EGLSurface eGLSurface = this.c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                if (this.f1370a.eglGetError() == 12299) {
                    Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                }
                return false;
            }
            EGL10 egl10 = this.f1370a;
            EGLDisplay eGLDisplay = this.b;
            EGLSurface eGLSurface2 = this.c;
            if (egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.e)) {
                return true;
            }
            a("EGLHelper", "eglMakeCurrent", this.f1370a.eglGetError());
            return false;
        }

        final GL c() {
            GL gl = this.e.getGL();
            w wVar = this.f.get();
            if (wVar == null) {
                return gl;
            }
            if (wVar.i != null) {
                gl = wVar.i.a();
            }
            if ((wVar.j & 3) != 0) {
                return GLDebugHelper.wrap(gl, (wVar.j & 1) == 0 ? 0 : 1, (wVar.j & 2) != 0 ? new l() : null);
            }
            return gl;
        }

        public final int d() {
            if (this.f1370a.eglSwapBuffers(this.b, this.c)) {
                return 12288;
            }
            return this.f1370a.eglGetError();
        }

        public final void e() {
            g();
        }

        private void g() {
            EGLSurface eGLSurface = this.c;
            if (eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE) {
                return;
            }
            EGL10 egl10 = this.f1370a;
            EGLDisplay eGLDisplay = this.b;
            EGLSurface eGLSurface2 = EGL10.EGL_NO_SURFACE;
            egl10.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, EGL10.EGL_NO_CONTEXT);
            w wVar = this.f.get();
            if (wVar != null) {
                wVar.h.a(this.f1370a, this.b, this.c);
            }
            this.c = null;
        }

        public final void f() {
            if (this.e != null) {
                w wVar = this.f.get();
                if (wVar != null) {
                    wVar.g.destroyContext(this.f1370a, this.b, this.e);
                }
                this.e = null;
            }
            EGLDisplay eGLDisplay = this.b;
            if (eGLDisplay != null) {
                this.f1370a.eglTerminate(eGLDisplay);
                this.b = null;
            }
        }

        private void a(String str) {
            a(str, this.f1370a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            Log.w(str, b(str2, i));
        }

        private static String b(String str, int i) {
            return str + " failed: " + i;
        }
    }

    static final class i extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1371a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private boolean p;
        private h s;
        private WeakReference<w> t;
        private ArrayList<Runnable> q = new ArrayList<>();
        private boolean r = true;
        private int l = 0;
        private int m = 0;
        private boolean o = true;
        private int n = 1;

        static /* synthetic */ boolean a(i iVar) {
            iVar.b = true;
            return true;
        }

        i(WeakReference<w> weakReference) {
            this.t = weakReference;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            setName("GLThread " + getId());
            try {
                m();
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                w.f1367a.a(this);
                throw th;
            }
            w.f1367a.a(this);
        }

        private void k() {
            if (this.i) {
                this.i = false;
                this.s.e();
            }
        }

        private void l() {
            if (this.h) {
                this.s.f();
                this.h = false;
                w.f1367a.c(this);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x009b, code lost:
        
            if (com.amap.api.col.p0003sl.w.f1367a.a() != false) goto L43;
         */
        /* JADX WARN: Removed duplicated region for block: B:177:0x021e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void m() throws java.lang.InterruptedException {
            /*
                Method dump skipped, instructions count: 553
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.3sl.w.i.m():void");
        }

        private boolean n() {
            return this.h && this.i && o();
        }

        private boolean o() {
            if (this.d || !this.e || this.f || this.l <= 0 || this.m <= 0) {
                return false;
            }
            return this.o || this.n == 1;
        }

        public final void a(int i) {
            if (i >= 0 && i <= 1) {
                synchronized (w.f1367a) {
                    this.n = i;
                    w.f1367a.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("renderMode");
        }

        public final int a() {
            int i;
            synchronized (w.f1367a) {
                i = this.n;
            }
            return i;
        }

        public final void b() {
            synchronized (w.f1367a) {
                this.o = true;
                w.f1367a.notifyAll();
            }
        }

        public final void c() {
            synchronized (w.f1367a) {
                this.e = true;
                this.j = false;
                w.f1367a.notifyAll();
                while (this.g && !this.j && !this.b) {
                    try {
                        w.f1367a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void d() {
            synchronized (w.f1367a) {
                this.e = false;
                w.f1367a.notifyAll();
                while (!this.g && !this.b) {
                    try {
                        if (MapsInitializer.getTextureViewDestorySync()) {
                            w.f1367a.wait();
                        } else {
                            w.f1367a.wait(2000L);
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void e() {
            synchronized (w.f1367a) {
                this.c = true;
                w.f1367a.notifyAll();
                while (!this.b && !this.d) {
                    try {
                        if (MapsInitializer.getTextureViewDestorySync()) {
                            w.f1367a.wait();
                        } else {
                            w.f1367a.wait(2000L);
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void f() {
            synchronized (w.f1367a) {
                this.c = false;
                this.o = true;
                this.p = false;
                w.f1367a.notifyAll();
                while (!this.b && this.d && !this.p) {
                    try {
                        w.f1367a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void a(int i, int i2) {
            synchronized (w.f1367a) {
                this.l = i;
                this.m = i2;
                this.r = true;
                this.o = true;
                this.p = false;
                w.f1367a.notifyAll();
                while (!this.b && !this.d && !this.p && n()) {
                    try {
                        w.f1367a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void g() {
            synchronized (w.f1367a) {
                this.f1371a = true;
                w.f1367a.notifyAll();
                while (!this.b) {
                    try {
                        w.f1367a.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public final void h() {
            this.k = true;
            w.f1367a.notifyAll();
        }

        public final void a(Runnable runnable) {
            if (runnable != null) {
                synchronized (w.f1367a) {
                    this.q.add(runnable);
                    w.f1367a.notifyAll();
                }
                return;
            }
            throw new IllegalArgumentException("r must not be null");
        }

        public final int i() {
            int i;
            synchronized (w.f1367a) {
                i = this.l;
            }
            return i;
        }

        public final int j() {
            int i;
            synchronized (w.f1367a) {
                i = this.m;
            }
            return i;
        }
    }

    static final class l extends Writer {

        /* renamed from: a, reason: collision with root package name */
        private StringBuilder f1373a = new StringBuilder();

        l() {
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            a();
        }

        @Override // java.io.Writer, java.io.Flushable
        public final void flush() {
            a();
        }

        @Override // java.io.Writer
        public final void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    a();
                } else {
                    this.f1373a.append(c);
                }
            }
        }

        private void a() {
            if (this.f1373a.length() > 0) {
                Log.v("GLSurfaceView", this.f1373a.toString());
                StringBuilder sb = this.f1373a;
                sb.delete(0, sb.length());
            }
        }
    }

    private void e() {
        if (this.c != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    static final class j {

        /* renamed from: a, reason: collision with root package name */
        private static String f1372a = "GLThreadManager";
        private boolean b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private i g;

        private j() {
        }

        /* synthetic */ j(byte b) {
            this();
        }

        public final void a(i iVar) {
            synchronized (this) {
                i.a(iVar);
                if (this.g == iVar) {
                    this.g = null;
                }
                notifyAll();
            }
        }

        public final boolean b(i iVar) {
            i iVar2 = this.g;
            if (iVar2 == iVar || iVar2 == null) {
                this.g = iVar;
                notifyAll();
                return true;
            }
            c();
            if (this.e) {
                return true;
            }
            i iVar3 = this.g;
            if (iVar3 == null) {
                return false;
            }
            iVar3.h();
            return false;
        }

        public final void c(i iVar) {
            if (this.g == iVar) {
                this.g = null;
            }
            notifyAll();
        }

        public final boolean a() {
            boolean z;
            synchronized (this) {
                z = this.f;
            }
            return z;
        }

        public final boolean b() {
            boolean z;
            synchronized (this) {
                c();
                z = this.e;
            }
            return !z;
        }

        public final void a(GL10 gl10) {
            synchronized (this) {
                if (!this.d && gl10 != null) {
                    c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.c < 131072) {
                        this.e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    this.f = !this.e;
                    this.d = true;
                }
            }
        }

        private void c() {
            if (this.b) {
                return;
            }
            this.c = 131072;
            this.e = true;
            this.b = true;
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.c.c();
        if (f() || MapsInitializer.getTextureSizeChangedInvoked()) {
            onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
        } else {
            if (this.c.i() == i2 && this.c.j() == i3) {
                return;
            }
            onSurfaceTextureSizeChanged(surfaceTexture, i2, i3);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.c.d();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.c.a(i2, i3);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i4 - i2, i5 - i3);
        super.onLayout(z, i2, i3, i4, i5);
    }
}
