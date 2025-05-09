package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.views.BaseVideoView;

/* loaded from: classes5.dex */
public class VideoView extends BaseVideoView {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8026a = "VideoView";

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        String str = f8026a;
        ho.b(str, "onSurfaceTextureDestroyed");
        this.m = false;
        if (this.t) {
            pause();
        }
        g();
        if (this.p != null) {
            ho.b(str, "release old surface when onSurfaceTextureDestroyed");
            this.p.release();
            this.p = null;
        }
        if (this.q == null) {
            return true;
        }
        ho.b(str, "release old surfaceTexture when onSurfaceTextureDestroyed");
        this.q.release();
        this.q = null;
        return true;
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        String str = f8026a;
        ho.b(str, "onSurfaceTextureAvailable width: %d height: %d", Integer.valueOf(i), Integer.valueOf(i2));
        this.m = true;
        if (this.p == null || this.q != surfaceTexture) {
            if (this.p != null) {
                ho.b(str, "release old surface when onSurfaceTextureAvailable");
                this.p.release();
            }
            if (this.q != null) {
                ho.b(str, "release old SurfaceTexture when onSurfaceTextureAvailable");
                this.q.release();
            }
            this.p = new Surface(surfaceTexture);
            this.n.setSurface(this.p);
            this.q = surfaceTexture;
        }
        if (this.u == null) {
            this.u = new BaseVideoView.h(this.x);
            this.n.setVideoSizeChangeListener(this.u);
        }
        if (this.l) {
            play(this.r);
        }
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f();
    }

    public Bitmap getSurfaceBitmap() {
        return this.k.getBitmap();
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView
    public void d() {
        super.d();
        if (this.p != null) {
            this.p.release();
        }
        this.p = null;
        this.q = null;
    }

    @Override // com.huawei.openalliance.ad.views.BaseVideoView
    protected void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_view_video, this);
        this.k = (TextureView) findViewById(R.id.hiad_id_video_texture_view);
        this.k.setSurfaceTextureListener(this);
    }

    public VideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoView(Context context) {
        super(context);
    }
}
