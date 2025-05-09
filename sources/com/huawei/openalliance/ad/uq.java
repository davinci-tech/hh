package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class uq implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7564a;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f7564a.n();
        ViewClickInstrumentation.clickOnView(view);
    }

    public uq(PPSRewardView pPSRewardView) {
        this.f7564a = pPSRewardView;
    }
}
