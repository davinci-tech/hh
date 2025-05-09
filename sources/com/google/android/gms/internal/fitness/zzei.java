package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

/* loaded from: classes8.dex */
final class zzei extends zzbb<SessionReadResult> {
    private final /* synthetic */ SessionReadRequest zzga;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzei(zzee zzeeVar, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        super(googleApiClient);
        this.zzga = sessionReadRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        ((zzcf) zzayVar.getService()).zza(new SessionReadRequest(this.zzga, new zzel(this, null)));
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public final /* synthetic */ Result createFailedResult(Status status) {
        return SessionReadResult.zze(status);
    }
}
