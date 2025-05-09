package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.views.NetworkChangeListener;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes9.dex */
public class us implements NetworkChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.openalliance.ad.views.interfaces.k f7566a;

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkDisconnected() {
    }

    @Override // com.huawei.openalliance.ad.views.NetworkChangeListener
    public void onNetworkConnectedOrChanged(boolean z) {
        this.f7566a.a(z);
    }

    public us(PPSRewardView pPSRewardView) {
        this.f7566a = pPSRewardView;
    }
}
