package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* loaded from: classes8.dex */
final class zzdq extends zzaj<DailyTotalResult> {
    private final /* synthetic */ DataType zzfm;
    private final /* synthetic */ boolean zzfn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdq(zzdj zzdjVar, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.zzfm = dataType;
        this.zzfn = z;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new com.google.android.gms.fitness.request.zzg((zzbe) new zzdr(this), this.zzfm, this.zzfn));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return DailyTotalResult.zza(status, this.zzfm);
    }
}
