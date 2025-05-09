package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class up implements com.huawei.openalliance.ad.views.interfaces.d {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7563a;

    @Override // com.huawei.openalliance.ad.views.interfaces.d
    public void a(boolean z, boolean z2, String str, boolean z3) {
        ho.a("RewardViewECCL", "onCardClick, isAppRelated: %s, isHanlded: %s, destination: %s, isButtonClicked: %s", Boolean.valueOf(z), Boolean.valueOf(z2), str, Boolean.valueOf(z3));
        com.huawei.openalliance.ad.views.interfaces.b bVar = new com.huawei.openalliance.ad.views.interfaces.b(z, true, str, 20);
        if (!z) {
            this.f7563a.b(new com.huawei.openalliance.ad.views.interfaces.b(false, z2, str, 20));
        } else if ("app".equals(str)) {
            this.f7563a.b("4");
            this.f7563a.b(bVar);
            if (!z3) {
                this.f7563a.getEndCardView().d();
            }
        } else {
            PPSRewardView pPSRewardView = this.f7563a;
            if (z3) {
                pPSRewardView.b(bVar);
                this.f7563a.b("3");
            } else {
                pPSRewardView.b(new com.huawei.openalliance.ad.views.interfaces.b(true, false, str, 20));
            }
        }
        this.f7563a.setClickInfo(null);
    }

    public up(PPSRewardView pPSRewardView) {
        this.f7563a = pPSRewardView;
    }
}
