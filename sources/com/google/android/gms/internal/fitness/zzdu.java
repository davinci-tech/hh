package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* loaded from: classes8.dex */
final class zzdu extends zzap<ListSubscriptionsResult> {
    zzdu(zzdt zzdtVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzam zzamVar) throws RemoteException {
        ((zzcb) zzamVar.getService()).zza(new com.google.android.gms.fitness.request.zzaj((DataType) null, (zzch) new zzdz(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return ListSubscriptionsResult.zzd(status);
    }
}
