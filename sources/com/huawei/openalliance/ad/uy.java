package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.DialogClickType;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class uy implements com.huawei.openalliance.ad.views.interfaces.aa {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f7760a;
    private final PPSRewardView b;

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void c() {
        ho.b("RewardViewPUCL", "popUpView, non button area clicked, is clickable: %s", Boolean.valueOf(this.f7760a));
        if (this.f7760a) {
            PPSRewardView pPSRewardView = this.b;
            pPSRewardView.a(pPSRewardView.getPopUpView().getClickInfo());
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void b() {
        this.b.a("129");
        this.b.a(true, "cancel");
        AppDownloadButton appDownloadButton = this.b.getAppDetailView().getAppDownloadButton();
        if (appDownloadButton != null) {
            appDownloadButton.f();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void a() {
        AppDownloadButton appDownloadButton = this.b.getAppDetailView().getAppDownloadButton();
        if (appDownloadButton != null) {
            appDownloadButton.a(this.b.getPopUpView().getClickInfo());
            appDownloadButton.setNeedShowConfirmDialog(false);
            appDownloadButton.setSource(16);
            appDownloadButton.performClick();
            this.b.a("128");
        }
        this.b.a(true, DialogClickType.POP_UP);
    }

    public uy(PPSRewardView pPSRewardView, boolean z) {
        this.f7760a = z;
        this.b = pPSRewardView;
    }
}
