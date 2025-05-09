package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;

/* loaded from: classes2.dex */
final class zzdk extends zzal {
    private final /* synthetic */ DataSet zzff;
    private final /* synthetic */ boolean zzfg = false;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdk(zzdj zzdjVar, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        super(googleApiClient);
        this.zzff = dataSet;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new com.google.android.gms.fitness.request.zzk(this.zzff, (zzcq) new zzen(this), this.zzfg));
    }
}
