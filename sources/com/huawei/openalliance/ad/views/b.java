package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.view.Surface;
import com.huawei.openalliance.ad.ee;
import com.huawei.openalliance.ad.ef;
import com.huawei.openalliance.ad.eg;
import com.huawei.openalliance.ad.eh;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.cz;
import com.huawei.openalliance.ad.utils.dj;
import com.huawei.openalliance.ad.views.BaseVideoView;

/* loaded from: classes9.dex */
public abstract class b extends BaseVideoView implements com.huawei.openalliance.ad.views.interfaces.u {
    private volatile boolean A;

    /* renamed from: a, reason: collision with root package name */
    protected final eg f8044a;
    protected ee b;
    protected eh c;
    protected int d;
    protected int e;
    protected g f;
    protected Integer g;
    protected Integer h;
    protected volatile Float i;
    protected volatile boolean j;
    private final ef y;
    private float[] z;

    protected abstract String getLogTag();

    public void setVideoRatio(Float f) {
        ho.a(getLogTag(), "setVideoRatio %s", f);
        this.i = f;
    }

    protected void e() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.4
            @Override // java.lang.Runnable
            public void run() {
                b.this.k();
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, com.huawei.openalliance.ad.views.interfaces.IViewLifeCycle
    public void destroyView() {
        super.destroyView();
        this.j = true;
        this.A = false;
        a();
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView
    public void d() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.3
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                GLES20.glClear(16384);
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void c() {
        if (this.j) {
            ho.c(getLogTag(), "renderVideo, destroyed");
        } else {
            a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (b.this.j) {
                            ho.c(b.this.getLogTag(), "renderVideo, destroyed");
                            return;
                        }
                        if (b.this.q != null) {
                            b.this.q.updateTexImage();
                        }
                        if (b.this.b != null) {
                            GLES20.glViewport(0, 0, b.this.d, b.this.e);
                            b.this.b.c();
                            b.this.j();
                        }
                    } catch (Throwable th) {
                        ho.a(3, b.this.getLogTag(), "render exception", th);
                    }
                }
            });
        }
    }

    protected void b(final int i, final int i2) {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.5
            @Override // java.lang.Runnable
            public void run() {
                b.this.d(i, i2);
                dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.x.a(b.this.v, b.this.w);
                    }
                });
            }
        });
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void b() {
        this.A = true;
    }

    protected void a(Runnable runnable) {
        g gVar = this.f;
        if (gVar != null) {
            gVar.a(runnable);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.u
    public void a(g gVar) {
        this.f = gVar;
        setMediaPlayerAgent(gVar.h());
    }

    protected void a(final Surface surface) {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.6
            @Override // java.lang.Runnable
            public void run() {
                b.this.b(surface);
            }
        });
    }

    protected void a(int i, int i2) {
        GLES20.glViewport(0, 0, i, i2);
        float f = i;
        float f2 = i2;
        Matrix.orthoM(this.z, 0, 0.0f, f, 0.0f, f2, -1.0f, 1.0f);
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        Integer num = this.g;
        if (num != null) {
            i = num.intValue();
        }
        Integer num2 = this.h;
        if (num2 != null) {
            i2 = num2.intValue();
        }
        this.f8044a.a(i, i2);
        this.f8044a.b(f3, f4);
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView
    public void a(float f, float f2, int i, int i2) {
        int i3 = this.s;
        if (i3 == 1) {
            a(this.d, this.e);
            return;
        }
        if (i3 != 2) {
            return;
        }
        if (f2 < f) {
            this.h = Integer.valueOf(i2);
            this.g = Integer.valueOf((int) (i2 * f));
        } else {
            this.g = Integer.valueOf(i);
            this.h = Integer.valueOf((int) (i / f));
        }
        this.f8044a.a(this.g.intValue(), this.h.intValue());
    }

    protected void a() {
        a(new Runnable() { // from class: com.huawei.openalliance.ad.views.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (b.this.b != null) {
                    b.this.b.e();
                    b.this.b = null;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        ho.b(getLogTag(), "onSurfaceDestroyed");
        this.m = false;
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.c == null || this.b == null) {
            ho.c(getLogTag(), "render failed, textureProgram:%s, windowSurface:%s", cz.b(this.c), cz.b(this.b));
            return;
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16384);
        if (this.A) {
            this.f8044a.a(this.c, this.z);
            this.b.d();
        }
    }

    private void e(int i, int i2) {
        this.d = i;
        this.e = i2;
        a(i, i2);
        if (this.i != null) {
            float floatValue = this.i.floatValue();
            int i3 = this.d;
            int i4 = this.e;
            a(floatValue, i3 / i4, i3, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        ho.b(getLogTag(), "onSurfaceChanged");
        e(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Surface surface) {
        ho.b(getLogTag(), "onSurfaceAvailable");
        this.m = true;
        if (this.f != null && surface != null && surface.isValid()) {
            try {
                this.f.b();
                ee eeVar = new ee(this.f.g(), surface);
                this.b = eeVar;
                eeVar.c();
                this.f.a();
                this.p = this.f.c();
                this.c = this.f.f();
                this.f8044a.a(this.f.d());
                this.q = this.f.e();
                this.n.setSurface(this.p);
                e(this.b.a(), this.b.b());
                if (this.u == null) {
                    this.u = new BaseVideoView.h(this.x);
                    this.n.setVideoSizeChangeListener(this.u);
                }
                if (this.l) {
                    play(this.r);
                }
            } catch (Throwable th) {
                ho.c(getLogTag(), "exception: %s", th.getClass().getSimpleName());
            }
        }
        c();
    }

    public b(Context context) {
        super(context);
        ef efVar = new ef();
        this.y = efVar;
        this.f8044a = new eg(efVar);
        this.j = false;
        this.z = new float[16];
        this.A = false;
    }
}
