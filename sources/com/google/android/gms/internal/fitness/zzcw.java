package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;

/* loaded from: classes8.dex */
final class zzcw extends zzu {
    private final /* synthetic */ String a_;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcw(zzct zzctVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.a_ = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzp zzpVar) throws RemoteException {
        ((zzbt) zzpVar.getService()).zza(new com.google.android.gms.fitness.request.zze(this.a_, (BleDevice) null, (zzcq) new zzen(this)));
    }
}
