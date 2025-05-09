package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.db.bean.ContentRecord;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.views.PPSRewardView;
import com.huawei.openalliance.ad.views.RewardVideoView;

/* loaded from: classes5.dex */
public class ui {

    /* renamed from: a, reason: collision with root package name */
    private lz f7556a = new lo();

    public void d() {
        this.f7556a.a(mo.CLICK);
    }

    public void c() {
        lz lzVar = this.f7556a;
        if (lzVar != null) {
            lzVar.e();
        }
    }

    public void b() {
        this.f7556a.b();
    }

    public void a(PPSRewardView pPSRewardView, IRewardAd iRewardAd, RewardVideoView rewardVideoView) {
        ContentRecord a2 = pn.a((com.huawei.openalliance.ad.inter.data.h) iRewardAd);
        if (a2 == null || a2.b(pPSRewardView.getContext()) == null) {
            ho.c("RewardOmProxy", "there is no reward ad or om is null");
            return;
        }
        ho.b("RewardOmProxy", "init om");
        this.f7556a.a(pPSRewardView.getContext(), a2, pPSRewardView, true);
        this.f7556a.c();
        a(rewardVideoView, this.f7556a);
    }

    public void a() {
        this.f7556a.j();
    }

    private void a(RewardVideoView rewardVideoView, lz lzVar) {
        if (rewardVideoView != null) {
            rewardVideoView.a(lzVar);
        }
    }
}
