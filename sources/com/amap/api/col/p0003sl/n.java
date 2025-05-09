package com.amap.api.col.p0003sl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* loaded from: classes2.dex */
public final class n extends GLSurfaceView implements IGLSurfaceView {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1346a;
    private IAMapDelegate b;
    private GLMapRender c;

    public n(Context context, boolean z) {
        this(context, z, (byte) 0);
    }

    private n(Context context, boolean z, byte b) {
        super(context, null);
        this.b = null;
        this.c = null;
        this.f1346a = false;
        dh.a(this);
        this.b = new l(this, context, z);
    }

    public final IAMapDelegate a() {
        return this.b;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        try {
            return this.b.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // android.opengl.GLSurfaceView, com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setRenderer(GLSurfaceView.Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLConfigChooser(df dfVar) {
        super.setEGLConfigChooser((GLSurfaceView.EGLConfigChooser) dfVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLContextFactory(dg dgVar) {
        super.setEGLContextFactory((GLSurfaceView.EGLContextFactory) dgVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void onDetachedGLThread() {
        dx.a(dw.c, "AMapGLSurfaceView onDetachedGLThread MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            onPause();
            try {
                GLMapRender gLMapRender = this.c;
                if (gLMapRender != null) {
                    gLMapRender.onDetachedFromWindow();
                }
            } catch (Throwable th) {
                th.printStackTrace();
                dv.a(th);
            }
            super.onDetachedFromWindow();
        }
    }

    @Override // android.opengl.GLSurfaceView
    public final void onPause() {
        dx.a(dw.c, "AMapGLSurfaceView onPause mMapRender.mSurfacedestoryed " + this.c.mSurfacedestoryed);
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.n.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (n.this.c != null) {
                        try {
                            n.this.c.onSurfaceDestory();
                        } catch (Throwable th) {
                            th.printStackTrace();
                            dv.a(th);
                        }
                    }
                }
            });
            for (int i = 0; !this.c.mSurfacedestoryed && i < 50; i++) {
                try {
                    Thread.sleep(20L);
                } catch (InterruptedException unused) {
                }
            }
        }
        super.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public final void onResume() {
        super.onResume();
        dx.a(dw.c, "AMapGLSurfaceView onPause");
    }

    @Override // android.view.SurfaceView, android.view.View
    protected final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        dx.a(dw.c, "AMapGLSurfaceView onWindowVisibilityChanged visibility ".concat(String.valueOf(i)));
        try {
            if (i == 8 || i == 4) {
                GLMapRender gLMapRender = this.c;
                if (gLMapRender != null) {
                    gLMapRender.renderPause();
                    this.f1346a = false;
                }
            } else {
                if (i != 0) {
                    return;
                }
                GLMapRender gLMapRender2 = this.c;
                if (gLMapRender2 != null) {
                    gLMapRender2.renderResume();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        dx.a(dw.c, "AMapGLSurfaceView onAttachedToWindow");
        try {
            GLMapRender gLMapRender = this.c;
            if (gLMapRender != null) {
                gLMapRender.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
        onResume();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected final void onDetachedFromWindow() {
        dx.a(dw.c, "AMapGLSurfaceView onDetachedFromWindow MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            return;
        }
        onPause();
        try {
            GLMapRender gLMapRender = this.c;
            if (gLMapRender != null) {
                gLMapRender.onDetachedFromWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.onDetachedFromWindow();
    }
}
