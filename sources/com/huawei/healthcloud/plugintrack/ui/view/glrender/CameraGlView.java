package com.huawei.healthcloud.plugintrack.ui.view.glrender;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import com.huawei.healthcloud.plugintrack.ui.view.glrender.util.Camera2Helper;
import defpackage.hly;

/* loaded from: classes4.dex */
public class CameraGlView extends GLSurfaceView {
    private hly e;

    public CameraGlView(Context context) {
        this(context, null);
    }

    public CameraGlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEGLContextClientVersion(2);
        hly hlyVar = new hly(this);
        this.e = hlyVar;
        setRenderer(hlyVar);
        setRenderMode(0);
    }

    public void setOnPreviewListener(Camera2Helper.OnPreviewListener onPreviewListener) {
        this.e.e(onPreviewListener);
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
        this.e.a();
    }

    public void d(boolean z) {
        this.e.d(z);
    }

    public boolean c() {
        return this.e.c();
    }

    public void c(boolean z) {
        this.e.e(z);
    }

    public void setIsOptimalSize(boolean z) {
        this.e.b(z);
    }

    public void setRadio(float f) {
        this.e.c(f);
    }
}
