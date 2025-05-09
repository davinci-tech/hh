package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.views.PPSRewardView;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class ue implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<PPSRewardView> f7552a;

    @Override // java.lang.Runnable
    public void run() {
        PPSRewardView pPSRewardView = this.f7552a.get();
        if (pPSRewardView == null) {
            return;
        }
        pPSRewardView.c(false);
    }

    public ue(PPSRewardView pPSRewardView) {
        this.f7552a = new WeakReference<>(pPSRewardView);
    }
}
