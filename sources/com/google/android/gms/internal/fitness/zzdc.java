package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.fitness.result.DataTypeResult;

/* loaded from: classes8.dex */
final class zzdc extends zzy<DataTypeResult> {
    private final /* synthetic */ DataTypeCreateRequest zzfb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdc(zzdb zzdbVar, GoogleApiClient googleApiClient, DataTypeCreateRequest dataTypeCreateRequest) {
        super(googleApiClient);
        this.zzfb = dataTypeCreateRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzv zzvVar) throws RemoteException {
        ((zzbv) zzvVar.getService()).zza(new DataTypeCreateRequest(this.zzfb, new zzdf(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataTypeResult.zzc(status);
    }
}
