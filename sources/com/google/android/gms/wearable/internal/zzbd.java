package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: classes8.dex */
final class zzbd extends zzn<Status> {
    private final /* synthetic */ zzay zzcm;
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ boolean zzcp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbd(zzay zzayVar, GoogleApiClient googleApiClient, Uri uri, boolean z) {
        super(googleApiClient);
        this.zzcm = zzayVar;
        this.zzco = uri;
        this.zzcp = z;
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzhg zzhgVar) throws RemoteException {
        String str;
        str = this.zzcm.zzce;
        zzhgVar.zza(this, str, this.zzco, this.zzcp);
    }
}
