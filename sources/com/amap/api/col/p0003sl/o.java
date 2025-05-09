package com.amap.api.col.p0003sl;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.base.ae.gmap.GLMapRender;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* loaded from: classes2.dex */
public final class o extends w implements IGLSurfaceView {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1350a;
    private IAMapDelegate b;
    private GLMapRender c;

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final SurfaceHolder getHolder() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setZOrderOnTop(boolean z) {
    }

    public o(Context context, boolean z) {
        super(context);
        this.b = null;
        this.c = null;
        this.f1350a = false;
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

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLConfigChooser(df dfVar) {
        super.a(dfVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLContextFactory(dg dgVar) {
        super.a(dgVar);
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void onDetachedGLThread() {
        dx.a(dw.c, "AMapGLTextureView onDetachedGLThread MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            b();
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

    @Override // com.amap.api.col.p0003sl.w, com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setRenderer(GLSurfaceView.Renderer renderer) {
        this.c = (GLMapRender) renderer;
        super.setRenderer(renderer);
    }

    @Override // com.amap.api.col.p0003sl.w
    public final void b() {
        dx.a(dw.c, "AMapGLTextureView onPause mMapRender.mSurfacedestoryed " + this.c.mSurfacedestoryed);
        if (!this.c.mSurfacedestoryed) {
            queueEvent(new Runnable() { // from class: com.amap.api.col.3sl.o.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        if (o.this.c != null) {
                            o.this.c.onSurfaceDestory();
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                        dv.a(th);
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
        super.b();
    }

    @Override // com.amap.api.col.p0003sl.w
    public final void c() {
        super.c();
        dx.a(dw.c, "AMapGLTextureView onResume");
    }

    @Override // com.amap.api.col.p0003sl.w, android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        dx.a(dw.c, "AMapGLTextureView onSurfaceTextureDestroyed");
        requestRender();
        try {
            if (MapsInitializer.getTextureDestroyRender()) {
                Thread.sleep(100L);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
        return super.onSurfaceTextureDestroyed(surfaceTexture);
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(int i) {
        GLMapRender gLMapRender;
        super.onWindowVisibilityChanged(i);
        dx.a(dw.c, "AMapGLTextureView onWindowVisibilityChanged visibility ".concat(String.valueOf(i)));
        try {
            if (i != 8 && i != 4) {
                if (i != 0 || (gLMapRender = this.c) == null) {
                    return;
                }
                gLMapRender.renderResume();
                return;
            }
            GLMapRender gLMapRender2 = this.c;
            if (gLMapRender2 != null) {
                gLMapRender2.renderPause();
                this.f1350a = false;
            }
            requestRender();
        } catch (Throwable th) {
            th.printStackTrace();
            dv.a(th);
        }
    }

    @Override // com.amap.api.col.p0003sl.w, android.view.TextureView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        dx.a(dw.c, "AMapGLTextureView onAttachedToWindow");
        try {
            GLMapRender gLMapRender = this.c;
            if (gLMapRender != null) {
                gLMapRender.onAttachedToWindow();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        c();
    }

    @Override // com.amap.api.col.p0003sl.w, android.view.View
    protected final void onDetachedFromWindow() {
        dx.a(dw.c, "AMapGLTextureView onDetachedFromWindow MapsInitializer.isSupportRecycleView() " + MapsInitializer.isSupportRecycleView());
        if (MapsInitializer.isSupportRecycleView()) {
            return;
        }
        b();
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
