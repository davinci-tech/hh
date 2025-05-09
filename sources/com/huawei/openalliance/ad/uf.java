package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.ComplianceActivity;
import com.huawei.openalliance.ad.inter.data.IRewardAd;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class uf implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final IRewardAd f7553a;
    private final PPSRewardView b;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IRewardAd iRewardAd = this.f7553a;
        if (iRewardAd == null) {
            ho.c("RewardChoiceViewOCL", "AdInfo is null or contentData is null");
        } else if (com.huawei.openalliance.ad.utils.bg.a(iRewardAd.getCompliance())) {
            com.huawei.openalliance.ad.utils.d.a(this.b.getContext(), this.f7553a);
        } else {
            ComplianceActivity.a(this.b.getContext(), view, pn.a((com.huawei.openalliance.ad.inter.data.h) this.f7553a), true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public uf(PPSRewardView pPSRewardView, IRewardAd iRewardAd) {
        this.f7553a = iRewardAd;
        this.b = pPSRewardView;
    }
}
