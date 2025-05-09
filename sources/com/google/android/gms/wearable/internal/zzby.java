package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;

/* loaded from: classes8.dex */
final class zzby extends zzn<DataApi.DataItemResult> {
    private final /* synthetic */ Uri zzco;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzby(zzbw zzbwVar, GoogleApiClient googleApiClient, Uri uri) {
        super(googleApiClient);
        this.zzco = uri;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgv(this), this.zzco);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzcg(status, null);
    }
}
