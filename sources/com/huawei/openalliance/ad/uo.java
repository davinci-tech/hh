package com.huawei.openalliance.ad;

import android.content.DialogInterface;

/* loaded from: classes5.dex */
public class uo implements DialogInterface.OnCancelListener {

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.openalliance.ad.views.interfaces.k f7562a;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.f7562a.m();
    }

    public uo(com.huawei.openalliance.ad.views.interfaces.k kVar) {
        this.f7562a = kVar;
    }
}
