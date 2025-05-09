package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

/* loaded from: classes8.dex */
final class zzeh extends zzbd {
    private final /* synthetic */ SessionInsertRequest zzfz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzeh(zzee zzeeVar, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.zzfz = sessionInsertRequest;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        ((zzcf) zzayVar.getService()).zza(new SessionInsertRequest(this.zzfz, new zzen(this)));
    }
}
