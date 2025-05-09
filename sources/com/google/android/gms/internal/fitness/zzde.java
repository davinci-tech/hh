package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzde extends zzaa {
    zzde(zzdb zzdbVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzv zzvVar) throws RemoteException {
        ((zzbv) zzvVar.getService()).zza(new com.google.android.gms.fitness.request.zzaa((zzcq) new zzen(this)));
    }
}
