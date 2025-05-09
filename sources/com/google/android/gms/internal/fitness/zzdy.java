package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
final class zzdy extends zzar {
    private final /* synthetic */ DataSource zzfs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdy(zzdt zzdtVar, GoogleApiClient googleApiClient, DataSource dataSource) {
        super(googleApiClient);
        this.zzfs = dataSource;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzam zzamVar) throws RemoteException {
        ((zzcb) zzamVar.getService()).zza(new com.google.android.gms.fitness.request.zzbn((DataType) null, this.zzfs, (zzcq) new zzen(this)));
    }
}
