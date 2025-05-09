package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

/* loaded from: classes8.dex */
final class zzew extends zzn<Status> {
    private final /* synthetic */ MessageApi.MessageListener zzef;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzew(zzeu zzeuVar, GoogleApiClient googleApiClient, MessageApi.MessageListener messageListener) {
        super(googleApiClient);
        this.zzef = messageListener;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        zzhgVar.zza(this, this.zzef);
    }
}
