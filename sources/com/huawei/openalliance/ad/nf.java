package com.huawei.openalliance.ad;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.huawei.openalliance.ad.inter.data.ImageInfo;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.utils.az;
import com.huawei.openalliance.ad.views.interfaces.INativeVideoView;
import com.huawei.openalliance.ad.views.interfaces.IPPSNativeView;

/* loaded from: classes9.dex */
public class nf extends na<INativeVideoView> implements nx<INativeVideoView> {
    private boolean h;

    @Override // com.huawei.openalliance.ad.nx
    public boolean a(MediaPlayerAgent mediaPlayerAgent, com.huawei.openalliance.ad.inter.data.e eVar) {
        return false;
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(boolean z) {
        if (this.b == null) {
            return;
        }
        this.b.b(z);
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(IPPSNativeView iPPSNativeView, com.huawei.openalliance.ad.inter.data.e eVar) {
        if (iPPSNativeView == null || eVar == null || eVar.V() || eVar.q() == null) {
            return;
        }
        int intValue = eVar.q().intValue();
        if (intValue == 1 || intValue == 2) {
            ho.b("NativeVideoViewPresenter", "reportAdShowEvent Media Play over reportAdShowEvent by customExposureType: %s", Integer.valueOf(intValue));
            iPPSNativeView.a(null);
        }
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(com.huawei.openalliance.ad.inter.data.e eVar) {
        this.f7126a = pd.a(eVar);
        this.b = new ou(this.d, new sf(this.d, 3), this.f7126a);
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(final VideoInfo videoInfo) {
        if (videoInfo == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.nf.1
            @Override // java.lang.Runnable
            public void run() {
                final boolean b = videoInfo.b(nf.this.d);
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nf.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((INativeVideoView) nf.this.d()).onCheckVideoHashResult(videoInfo, b);
                    }
                });
            }
        });
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(final ImageInfo imageInfo) {
        if (imageInfo == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.nf.3
            @Override // java.lang.Runnable
            public void run() {
                if (imageInfo.b(nf.this.d)) {
                    nf.this.b(imageInfo);
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(int i, int i2, long j, IPPSNativeView iPPSNativeView, com.huawei.openalliance.ad.inter.data.e eVar) {
        if (iPPSNativeView == null || eVar == null || eVar.V() || eVar.q() == null || eVar.q().intValue() == 0) {
            return;
        }
        Integer q = eVar.q();
        if (q.intValue() == 2 && eVar.r() != null && eVar.r().intValue() >= 0) {
            int intValue = eVar.r().intValue();
            if (ho.a()) {
                ho.a("NativeVideoViewPresenter", "reportAdShowEvent play percentage: %s  minEffectiveVideoPlayProgress：%s", Integer.valueOf(i), eVar.r());
            }
            if (i < intValue) {
                return;
            }
        } else {
            if (q.intValue() != 1) {
                return;
            }
            long j2 = i2 - j;
            if (ho.a()) {
                ho.a("NativeVideoViewPresenter", "reportAdShowEvent has playing time: %s  playTime: %s  videoStartPlayTime: %s", Long.valueOf(j2), Integer.valueOf(i2), Long.valueOf(j));
            }
            if (j2 < eVar.getMinEffectiveShowTime()) {
                return;
            }
        }
        iPPSNativeView.a(null);
    }

    @Override // com.huawei.openalliance.ad.nx
    public void a(int i, int i2) {
        a();
        if (this.h) {
            return;
        }
        this.h = true;
        new com.huawei.openalliance.ad.analysis.c(this.d).a(this.f7126a, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final ImageInfo imageInfo) {
        if (imageInfo == null) {
            return;
        }
        com.huawei.openalliance.ad.utils.az.a(this.d, imageInfo.getUrl(), new az.a() { // from class: com.huawei.openalliance.ad.nf.2
            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a(final Drawable drawable) {
                com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nf.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((INativeVideoView) nf.this.d()).onPreviewImageLoaded(imageInfo, drawable);
                    }
                });
            }

            @Override // com.huawei.openalliance.ad.utils.az.a
            public void a() {
                ho.c("NativeVideoViewPresenter", "video cover image load failed");
            }
        });
    }

    private void a() {
        if (this.f7126a == null || this.f7126a.R() == null) {
            return;
        }
        ho.b("NativeVideoViewPresenter", "online stream error, direct cache video.");
        com.huawei.openalliance.ad.beans.metadata.VideoInfo R = this.f7126a.R();
        dt.h().a(new dr(R.a(), R.c(), R.i() == 0, R.g(), null, true, 1, this.f7126a.m(), this.f7126a.l(), 3, false, 0));
    }

    public nf(Context context, INativeVideoView iNativeVideoView) {
        super(context);
        this.h = false;
        a((nf) iNativeVideoView);
        this.b = new ou(context, new sf(context, 3));
    }
}
