package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes2.dex */
final class zzcc extends zzn<DataApi.GetFdForAssetResult> {
    private final /* synthetic */ Asset zzdd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcc(zzbw zzbwVar, GoogleApiClient googleApiClient, Asset asset) {
        super(googleApiClient);
        this.zzdd = asset;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        zzhgVar.zza(this, this.zzdd);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzci(status, null);
    }
}
