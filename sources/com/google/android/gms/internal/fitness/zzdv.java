package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* loaded from: classes8.dex */
final class zzdv extends zzap<ListSubscriptionsResult> {
    private final /* synthetic */ DataType zzfm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdv(zzdt zzdtVar, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzfm = dataType;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzam zzamVar) throws RemoteException {
        ((zzcb) zzamVar.getService()).zza(new com.google.android.gms.fitness.request.zzaj(this.zzfm, (zzch) new zzdz(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }
}
