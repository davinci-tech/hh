package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;

/* loaded from: classes8.dex */
final class zzcx extends zzu {
    private final /* synthetic */ BleDevice zzfa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcx(zzct zzctVar, GoogleApiClient googleApiClient, BleDevice bleDevice) {
        super(googleApiClient);
        this.zzfa = bleDevice;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzp zzpVar) throws RemoteException {
        ((zzbt) zzpVar.getService()).zza(new com.google.android.gms.fitness.request.zze(this.zzfa.getAddress(), this.zzfa, (zzcq) new zzen(this)));
    }
}
