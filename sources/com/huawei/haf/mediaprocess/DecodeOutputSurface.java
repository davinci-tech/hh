package com.huawei.haf.mediaprocess;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import health.compact.a.LogUtil;
import java.io.IOException;

/* loaded from: classes.dex */
public class DecodeOutputSurface implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a, reason: collision with root package name */
    private TextureRenderer f2129a;
    private boolean c;
    private Surface d;
    private SurfaceTexture e;
    private final Object b = new Object();
    private int h = 0;

    public DecodeOutputSurface() throws IOException {
        d();
    }

    private void d() throws IOException {
        TextureRenderer textureRenderer = new TextureRenderer(this.h);
        this.f2129a = textureRenderer;
        textureRenderer.c();
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.f2129a.e());
        this.e = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this);
        this.d = new Surface(this.e);
    }

    public void c() {
        this.d.release();
        this.f2129a = null;
        this.d = null;
        this.e = null;
    }

    public Surface zc_() {
        return this.d;
    }

    public void e() throws IOException {
        synchronized (this.b) {
            do {
                if (!this.c) {
                    try {
                        this.b.wait(5000L);
                    } catch (InterruptedException e) {
                        LogUtil.e("DecodeOutputSurface", " awaitNewImage interrupt");
                        throw new IOException(e);
                    }
                } else {
                    this.c = false;
                }
            } while (this.c);
            LogUtil.e("DecodeOutputSurface", "Surface frame wait timed out");
            throw new IOException("Surface frame wait timed out");
        }
        this.f2129a.c("before updateTextImage");
        this.e.updateTexImage();
    }

    public void b() throws IOException {
        this.f2129a.ze_(this.e, false);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this.b) {
            if (this.c) {
                LogUtil.e("DecodeOutputSurface", "mIsFrameAvailable already set, frame could be dropped");
            }
            this.c = true;
            this.b.notifyAll();
        }
    }
}
