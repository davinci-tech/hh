package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

/* loaded from: classes8.dex */
final class zzbz extends zzn<DataItemBuffer> {
    zzbz(zzbw zzbwVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgw(this));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new DataItemBuffer(DataHolder.empty(status.getStatusCode()));
    }
}
