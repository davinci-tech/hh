package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final class zzr extends zzn<CapabilityApi.AddLocalCapabilityResult> {
    private final /* synthetic */ String zzbp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzr(zzo zzoVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzbp = str;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgl(this), this.zzbp);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzu(status);
    }
}
