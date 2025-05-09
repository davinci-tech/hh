package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.media.listener.MuteListener;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes9.dex */
public class ur implements MuteListener {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7565a;

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onUnmute() {
        this.f7565a.e(false);
    }

    @Override // com.huawei.openalliance.ad.media.listener.MuteListener
    public void onMute() {
        this.f7565a.e(true);
    }

    public ur(PPSRewardView pPSRewardView) {
        this.f7565a = pPSRewardView;
    }
}
