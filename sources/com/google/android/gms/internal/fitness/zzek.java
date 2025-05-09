package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzek extends zzbd {
    private final /* synthetic */ PendingIntent zzfv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzek(zzee zzeeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzfv = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        ((zzcf) zzayVar.getService()).zza(new com.google.android.gms.fitness.request.zzbd(this.zzfv, (zzcq) new zzen(this)));
    }
}
