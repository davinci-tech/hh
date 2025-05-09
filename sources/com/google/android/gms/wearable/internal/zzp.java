package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;

/* loaded from: classes8.dex */
final class zzp extends zzn<CapabilityApi.GetCapabilityResult> {
    private final /* synthetic */ String zzbp;
    private final /* synthetic */ int zzbq;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzp(zzo zzoVar, GoogleApiClient googleApiClient, String str, int i) {
        super(googleApiClient);
        this.zzbp = str;
        this.zzbq = i;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzgr(this), this.zzbp, this.zzbq);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzy(status, null);
    }
}
