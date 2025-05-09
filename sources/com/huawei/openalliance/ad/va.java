package com.huawei.openalliance.ad;

import android.content.DialogInterface;
import com.huawei.openalliance.ad.views.PPSRewardView;

/* loaded from: classes5.dex */
public class va implements DialogInterface.OnCancelListener {

    /* renamed from: a, reason: collision with root package name */
    private final PPSRewardView f7764a;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.f7764a.q();
    }

    public va(PPSRewardView pPSRewardView) {
        this.f7764a = pPSRewardView;
    }
}
