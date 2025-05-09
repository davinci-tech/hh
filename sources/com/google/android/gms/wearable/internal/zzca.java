package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

/* loaded from: classes8.dex */
final class zzca extends zzn<DataItemBuffer> {
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ int zzdc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzca(zzbw zzbwVar, GoogleApiClient googleApiClient, Uri uri, int i) {
        super(googleApiClient);
        this.zzco = uri;
        this.zzdc = i;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgw(this), this.zzco, this.zzdc);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new DataItemBuffer(DataHolder.empty(status.getStatusCode()));
    }
}
