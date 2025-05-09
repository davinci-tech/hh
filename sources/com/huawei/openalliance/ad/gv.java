package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.utils.az;

/* loaded from: classes9.dex */
public class gv extends na<hf> implements gw<hf> {
    private boolean h;

    @Override // com.huawei.openalliance.ad.gw
    public void c() {
        if (this.b == null) {
            return;
        }
        this.b.e();
    }

    @Override // com.huawei.openalliance.ad.gw
    public void b() {
        if (this.b == null) {
            return;
        }
        this.b.d();
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(boolean z) {
        if (this.b == null) {
            return;
        }
        this.b.b(z);
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(VideoInfo videoInfo) {
        if (videoInfo == null) {
            return;
        }
        ((hf) d()).a(videoInfo, videoInfo.b(this.d));
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(ImageInfo imageInfo) {
        ho.c("NativeVideoP", "checkPreviewImageHashAndLoad " + imageInfo);
        if (imageInfo == null) {
            return;
        }
        boolean b = imageInfo.b(this.d);
        ho.b("NativeVideoP", "imageInfo checkHash result %s", Boolean.valueOf(b));
        if (b) {
            b(imageInfo);
        }
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(gs gsVar) {
        if (gsVar == null) {
            this.f7126a = null;
            return;
        }
        this.f7126a = gsVar.a();
        if (this.f7126a != null) {
            this.b.a(this.f7126a);
        }
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(long j, long j2, long j3, long j4) {
        this.b.a(j, j2, (int) j3, (int) j4);
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a(int i, int i2) {
        if (this.h) {
            return;
        }
        this.h = true;
        new com.huawei.openalliance.ad.analysis.c(this.d).a(this.f7126a, i, i2);
    }

    @Override // com.huawei.openalliance.ad.gw
    public void a() {
        if (this.f7126a == null || this.b == null) {
            return;
        }
        this.b.a(this.d, this.f7126a);
    }

    private void b(final ImageInfo imageInfo) {
        if (imageInfo == null) {
            ho.b("NativeVideoP", "image info null");
        } else {
            com.huawei.openalliance.ad.utils.az.a(this.d, imageInfo.getUrl(), new az.a() { // from class: com.huawei.openalliance.ad.gv.1
                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a(final Drawable drawable) {
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.gv.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ((hf) gv.this.d()).a(imageInfo, drawable);
                        }
                    });
                }

                @Override // com.huawei.openalliance.ad.utils.az.a
                public void a() {
                    ho.c("NativeVideoP", "preview image load failed");
                }
            });
        }
    }

    public gv(Context context, hf hfVar) {
        super(context);
        this.h = false;
        a((gv) hfVar);
        this.b = new ou(context, gu.a(context, hfVar.getLinkedNativeAd()));
    }
}
