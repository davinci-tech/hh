package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;

/* loaded from: classes8.dex */
final class zzdn extends zzal {
    private final /* synthetic */ DataUpdateListenerRegistrationRequest zzfj;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdn(zzdj zzdjVar, GoogleApiClient googleApiClient, DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) {
        super(googleApiClient);
        this.zzfj = dataUpdateListenerRegistrationRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new DataUpdateListenerRegistrationRequest(this.zzfj, new zzen(this)));
    }
}
