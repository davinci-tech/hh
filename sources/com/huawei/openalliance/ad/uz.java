package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class uz implements com.huawei.openalliance.ad.views.interfaces.aa {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7761a;

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void c() {
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void b() {
        this.f7761a.c("129");
        AppDownloadButton appDownloadButton = this.f7761a.getAppDetailView().getAppDownloadButton();
        if (appDownloadButton != null) {
            appDownloadButton.f();
        }
        this.f7761a.o();
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void a() {
        AppDownloadButton appDownloadButton = this.f7761a.getAppDetailView().getAppDownloadButton();
        this.f7761a.c("128");
        if (appDownloadButton != null) {
            appDownloadButton.a(this.f7761a.getWebPopUpView().getClickInfo());
            appDownloadButton.setSource(5);
            appDownloadButton.performClick();
        }
        this.f7761a.o();
    }

    public uz(PPSRewardView pPSRewardView) {
        this.f7761a = pPSRewardView;
    }
}
