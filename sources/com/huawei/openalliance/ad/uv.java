package com.huawei.openalliance.ad;

import android.view.View;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes9.dex */
public class uv implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7756a;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.reward_close == view.getId()) {
            this.f7756a.d(true);
        } else if (R.id.reward_mute_icon == view.getId()) {
            this.f7756a.i();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public uv(PPSRewardView pPSRewardView) {
        this.f7756a = pPSRewardView;
    }
}
