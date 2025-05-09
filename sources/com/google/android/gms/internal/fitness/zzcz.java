package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* loaded from: classes8.dex */
final class zzcz extends zzs<BleDevicesResult> {
    zzcz(zzct zzctVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzp zzpVar) throws RemoteException {
        ((zzbt) zzpVar.getService()).zza(new com.google.android.gms.fitness.request.zzah((zzer) new zzda(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return BleDevicesResult.zzb(status);
    }
}
