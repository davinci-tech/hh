package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
final class zzdx extends zzar {
    private final /* synthetic */ DataType zzfm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdx(zzdt zzdtVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzfm = dataType;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzam zzamVar) throws RemoteException {
        ((zzcb) zzamVar.getService()).zza(new com.google.android.gms.fitness.request.zzbn(this.zzfm, (DataSource) null, (zzcq) new zzen(this)));
    }
}
