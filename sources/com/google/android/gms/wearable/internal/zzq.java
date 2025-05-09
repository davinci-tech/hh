package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final class zzq extends zzn<CapabilityApi.GetAllCapabilitiesResult> {
    private final /* synthetic */ int zzbq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzq(zzo zzoVar, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
        this.zzbq = i;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgq(this), this.zzbq);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzx(status, null);
    }
}
