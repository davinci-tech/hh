package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes8.dex */
final class zzaz extends zzn<Status> {
    private final /* synthetic */ zzay zzcm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaz(zzay zzayVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzcm = zzayVar;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        String str;
        str = this.zzcm.zzce;
        ((zzep) zzhgVar.getService()).zzc(new zzgn(this), str);
    }
}
