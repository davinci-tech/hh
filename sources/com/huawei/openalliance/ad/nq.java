package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.inter.data.VideoInfo;
import com.huawei.openalliance.ad.views.interfaces.IRewardVideoView;

/* loaded from: classes9.dex */
public class nq extends na<IRewardVideoView> implements of<IRewardVideoView> {
    @Override // com.huawei.openalliance.ad.of
    public void a(com.huawei.openalliance.ad.inter.data.h hVar) {
        this.f7126a = pn.a(hVar);
        this.b = new ou(this.d, new sh(this.d), this.f7126a);
    }

    @Override // com.huawei.openalliance.ad.of
    public void a(final VideoInfo videoInfo) {
        if (videoInfo == null) {
            return;
        }
        ho.b("RewardVideoViewPresenter", "checkVideoHash");
        com.huawei.openalliance.ad.utils.m.f(new Runnable() { // from class: com.huawei.openalliance.ad.nq.1
            @Override // java.lang.Runnable
            public void run() {
                if (com.huawei.openalliance.ad.utils.cz.j(videoInfo.getVideoDownloadUrl()) || videoInfo.b(nq.this.d)) {
                    com.huawei.openalliance.ad.utils.dj.a(new Runnable() { // from class: com.huawei.openalliance.ad.nq.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ((IRewardVideoView) nq.this.d()).onCheckVideoHashResult(videoInfo, true);
                        }
                    });
                }
            }
        });
    }

    @Override // com.huawei.openalliance.ad.of
    public void a(Context context, long j, long j2, int i) {
        new com.huawei.openalliance.ad.analysis.c(context).a(this.f7126a, j, j2, i);
    }

    @Override // com.huawei.openalliance.ad.of
    public void a(int i) {
        new com.huawei.openalliance.ad.analysis.c(this.d).c(this.f7126a, i);
    }

    public nq(Context context, IRewardVideoView iRewardVideoView) {
        super(context);
        a((nq) iRewardVideoView);
        this.b = new ou(context, new sh(context));
    }
}
