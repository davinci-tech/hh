package com.huawei.openalliance.ad;

import android.content.DialogInterface;
import com.huawei.openalliance.ad.views.PPSRewardView;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public class ud implements DialogInterface.OnCancelListener {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<PPSRewardView> f7551a;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        PPSRewardView pPSRewardView = this.f7551a.get();
        if (pPSRewardView != null) {
            pPSRewardView.resumeView();
            pPSRewardView.a("130");
            pPSRewardView.setAdDialog(null);
        }
    }

    public ud(PPSRewardView pPSRewardView) {
        this.f7551a = new WeakReference<>(pPSRewardView);
    }
}
