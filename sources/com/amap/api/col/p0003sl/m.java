package com.amap.api.col.p0003sl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.SurfaceHolder;
import android.view.ViewParent;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.api.mapcore.IGLSurfaceView;

/* loaded from: classes2.dex */
public final class m implements IGLSurfaceView {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f1330a;
    private IAMapDelegate b;

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final int getHeight() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final SurfaceHolder getHolder() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final ViewParent getParent() {
        return null;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final int getRenderMode() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final int getWidth() {
        return 0;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void onDetachedGLThread() {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final boolean post(Runnable runnable) {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final boolean postDelayed(Runnable runnable, long j) {
        return false;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void requestRender() {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setBackgroundColor(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLConfigChooser(df dfVar) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setEGLContextFactory(dg dgVar) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setRenderMode(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setRenderer(GLSurfaceView.Renderer renderer) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setVisibility(int i) {
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void setZOrderOnTop(boolean z) {
    }

    public m(Context context) {
        this(context, (byte) 0);
    }

    private m(Context context, byte b) {
        this.b = null;
        this.f1330a = false;
        this.b = new l(this, context);
    }

    public final IAMapDelegate a() {
        return this.b;
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final void queueEvent(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.autonavi.base.amap.api.mapcore.IGLSurfaceView
    public final boolean isEnabled() {
        return this.b != null;
    }
}
