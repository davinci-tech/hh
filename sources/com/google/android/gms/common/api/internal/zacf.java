package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes2.dex */
final class zacf implements Runnable {
    private final /* synthetic */ zace zakk;

    zacf(zace zaceVar) {
        this.zakk = zaceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zach zachVar;
        zachVar = this.zakk.zakj;
        zachVar.zag(new ConnectionResult(4));
    }
}
