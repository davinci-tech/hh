package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;

/* loaded from: classes8.dex */
final class zzcu extends zzu {
    private final /* synthetic */ StartBleScanRequest zzex;
    private final /* synthetic */ com.google.android.gms.fitness.request.zzae zzey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcu(zzct zzctVar, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest, com.google.android.gms.fitness.request.zzae zzaeVar) {
        super(googleApiClient);
        this.zzex = startBleScanRequest;
        this.zzey = zzaeVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzp zzpVar) throws RemoteException {
        ((zzbt) zzpVar.getService()).zza(new StartBleScanRequest(this.zzex.getDataTypes(), this.zzey, this.zzex.getTimeoutSecs(), new zzen(this)));
    }
}
