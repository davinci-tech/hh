package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResult;

/* loaded from: classes8.dex */
final class zzdp extends zzaj<DataReadResult> {
    private final /* synthetic */ DataReadRequest zzfl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdp(zzdj zzdjVar, GoogleApiClient googleApiClient, DataReadRequest dataReadRequest) {
        super(googleApiClient);
        this.zzfl = dataReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new DataReadRequest(this.zzfl, new zzds(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DataReadResult.zza(status, this.zzfl.getDataTypes(), this.zzfl.getDataSources());
    }
}
