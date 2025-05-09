package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.Session;

/* loaded from: classes8.dex */
final class zzef extends zzbd {
    private final /* synthetic */ Session zzfx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzef(zzee zzeeVar, GoogleApiClient googleApiClient, Session session) {
        super(googleApiClient);
        this.zzfx = session;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        ((zzcf) zzayVar.getService()).zza(new com.google.android.gms.fitness.request.zzaz(this.zzfx, (zzcq) new zzen(this)));
    }
}
