package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.DataDeleteRequest;

/* loaded from: classes2.dex */
final class zzdl extends zzal {
    private final /* synthetic */ DataDeleteRequest zzfh;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdl(zzdj zzdjVar, GoogleApiClient googleApiClient, DataDeleteRequest dataDeleteRequest) {
        super(googleApiClient);
        this.zzfh = dataDeleteRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new DataDeleteRequest(this.zzfh, new zzen(this)));
    }
}
