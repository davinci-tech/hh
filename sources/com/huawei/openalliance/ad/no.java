package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.MediaFile;
import com.huawei.openalliance.ad.inter.data.PlacementMediaFile;
import com.huawei.openalliance.ad.views.interfaces.IPlacementVideoView;

/* loaded from: classes5.dex */
public class no extends na<IPlacementVideoView> implements od<IPlacementVideoView> {
    @Override // com.huawei.openalliance.ad.od
    public void b(String str) {
        this.g = str;
    }

    @Override // com.huawei.openalliance.ad.od
    public void a(com.huawei.openalliance.ad.inter.data.g gVar) {
        this.f7126a = pi.a(gVar);
        this.b = new ou(this.d, new sg(this.d), this.f7126a);
        if (gVar != null) {
            this.f = Integer.valueOf(gVar.getSequence());
        }
    }

    @Override // com.huawei.openalliance.ad.od
    public void a(final PlacementMediaFile placementMediaFile) {
        if (placementMediaFile == null) {
            return;
        }
        ho.b("PlacementVideoViewPresenter", "checkVideoHash");
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.no.1
            @Override // java.lang.Runnable
            public void run() {
                if (2 == placementMediaFile.getPlayMode() || placementMediaFile.a(no.this.d)) {
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.no.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ((IPlacementVideoView) no.this.d()).onCheckVideoHashResult(placementMediaFile, true);
                        }
                    });
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.od
    public void a(long j, int i) {
        new com.huawei.openalliance.ad.analysis.c(this.d).a(this.f7126a, j, i);
    }

    @Override // com.huawei.openalliance.ad.od
    public void a() {
        if (this.f7126a == null || this.f7126a.S() == null) {
            return;
        }
        try {
            ho.b("PlacementVideoViewPresenter", "online stream error, direct cache video.");
            MediaFile S = this.f7126a.S();
            dt.h().a(new dr(S.e(), (int) S.d(), S.g() == 0, S.f(), null, true, 1, this.f7126a.m(), this.f7126a.l(), 60, false, 0));
        } catch (Throwable th) {
            ho.c("PlacementVideoViewPresenter", "direct cache ex: %s", th.getClass().getSimpleName());
        }
    }

    public no(Context context, IPlacementVideoView iPlacementVideoView) {
        super(context == null ? context : context.getApplicationContext());
        a((no) iPlacementVideoView);
        context = context != null ? context.getApplicationContext() : context;
        this.b = new ou(context, new sg(context));
    }
}
