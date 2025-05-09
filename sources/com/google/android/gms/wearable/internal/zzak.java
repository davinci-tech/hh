package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.ChannelApi;

/* loaded from: classes8.dex */
final class zzak extends zzn<ChannelApi.OpenChannelResult> {
    private final /* synthetic */ String zzcb;
    private final /* synthetic */ String zzcc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzak(zzaj zzajVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzcb = str;
        this.zzcc = str2;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        ((zzep) zzhgVar.getService()).zza(new zzha(this), this.zzcb, this.zzcc);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzam(status, null);
    }
}
