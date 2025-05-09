package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.DialogClickType;
import com.huawei.openalliance.ad.views.AppDownloadButton;

/* loaded from: classes5.dex */
public class uk implements com.huawei.openalliance.ad.views.interfaces.aa {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.openalliance.ad.views.interfaces.j f7558a;
    private boolean b;

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void c() {
        com.huawei.openalliance.ad.views.t popUpView;
        ho.b("RewardTPopListener", "onNonBtnAreaClick, is clickable: %s", Boolean.valueOf(this.b));
        if (this.b && (popUpView = this.f7558a.getPopUpView()) != null) {
            this.f7558a.a((Integer) 1);
            this.f7558a.a(21, popUpView.getClickInfo());
            this.f7558a.a(false, DialogClickType.NO_BUTTON_AREA);
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void b() {
        ho.b("RewardTPopListener", "onCancelClick");
        this.f7558a.a("129");
        this.f7558a.a(true, "cancel");
        AppDownloadButton appDownloadButton = this.f7558a.getAppDownloadButton();
        if (appDownloadButton != null) {
            appDownloadButton.f();
        }
    }

    @Override // com.huawei.openalliance.ad.views.interfaces.aa
    public void a() {
        ho.b("RewardTPopListener", "onPopUpClick");
        this.f7558a.d();
        this.f7558a.a(true, DialogClickType.POP_UP);
        this.f7558a.a("128");
    }

    public uk(com.huawei.openalliance.ad.views.interfaces.j jVar, boolean z) {
        this.f7558a = jVar;
        this.b = z;
    }
}
