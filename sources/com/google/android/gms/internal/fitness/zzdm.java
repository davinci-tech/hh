package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* loaded from: classes8.dex */
final class zzdm extends zzal {
    private final /* synthetic */ DataUpdateRequest zzfi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdm(zzdj zzdjVar, GoogleApiClient googleApiClient, DataUpdateRequest dataUpdateRequest) {
        super(googleApiClient);
        this.zzfi = dataUpdateRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new DataUpdateRequest(this.zzfi, new zzen(this)));
    }
}
