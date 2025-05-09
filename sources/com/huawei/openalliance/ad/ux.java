package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.constant.StatusChangeMethods;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes9.dex */
public class ux implements com.huawei.openalliance.ad.download.l {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7759a;

    @Override // com.huawei.openalliance.ad.download.k
    public void a(String str, int i) {
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void b(String str) {
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void c(String str) {
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void b(AppDownloadTask appDownloadTask) {
        ho.b("RewardViewOSCL", StatusChangeMethods.STATUS_CHANGE);
        if (appDownloadTask != null) {
            com.huawei.openalliance.ad.download.e i = appDownloadTask.i();
            ho.b("RewardViewOSCL", "status:" + i);
            if (com.huawei.openalliance.ad.download.e.DOWNLOADING == i) {
                this.f7759a.b("3");
            }
        }
        this.f7759a.d();
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void a(String str) {
        this.f7759a.d();
    }

    @Override // com.huawei.openalliance.ad.download.l
    public void a(AppDownloadTask appDownloadTask) {
        this.f7759a.d();
    }

    public ux(PPSRewardView pPSRewardView) {
        this.f7759a = pPSRewardView;
    }
}
