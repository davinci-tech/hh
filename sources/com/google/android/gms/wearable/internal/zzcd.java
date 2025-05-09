package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItemAsset;

/* loaded from: classes8.dex */
final class zzcd extends zzn<DataApi.GetFdForAssetResult> {
    private final /* synthetic */ DataItemAsset zzde;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcd(zzbw zzbwVar, GoogleApiClient googleApiClient, DataItemAsset dataItemAsset) {
        super(googleApiClient);
        this.zzde = dataItemAsset;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        zzhgVar.zza(this, Asset.createFromRef(this.zzde.getId()));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzci(status, null);
    }
}
