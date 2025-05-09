package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes9.dex */
public class aj extends b {
    private boolean A;
    private Surface B;
    private final int y;
    private SurfaceTexture z;

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        b(i, i2);
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.A = false;
        k();
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.A = true;
        k();
        this.z = surfaceTexture;
        Surface surface = new Surface(surfaceTexture);
        this.B = surface;
        a(surface);
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ho.b(getLogTag(), "onDetachedFromWindow");
        k();
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ho.b(getLogTag(), "onAttachedToWindow");
    }

    public boolean j() {
        return this.A;
    }

    @Override // com.huawei.openalliance.ad.views.b
    protected String getLogTag() {
        return "TextureGlVideoView" + this.y;
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView
    protected void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_view_video, this);
        this.k = (TextureView) findViewById(R.id.hiad_id_video_texture_view);
        this.k.setSurfaceTextureListener(this);
    }

    private void k() {
        Surface surface = this.B;
        if (surface != null) {
            surface.release();
            this.B = null;
        }
        SurfaceTexture surfaceTexture = this.z;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.z = null;
        }
    }

    public aj(Context context) {
        super(context);
        this.y = hashCode();
        this.A = false;
    }
}
