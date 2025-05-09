package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataRequest;

/* loaded from: classes2.dex */
final class zzbx extends zzn<DataApi.DataItemResult> {
    private final /* synthetic */ PutDataRequest zzdb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbx(zzbw zzbwVar, GoogleApiClient googleApiClient, PutDataRequest putDataRequest) {
        super(googleApiClient);
        this.zzdb = putDataRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        zzhgVar.zza(this, this.zzdb);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzcg(status, null);
    }
}
