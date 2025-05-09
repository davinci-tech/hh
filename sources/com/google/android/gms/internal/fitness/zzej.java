package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzej extends zzbd {
    private final /* synthetic */ PendingIntent zzfv;
    private final /* synthetic */ int zzgb = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzej(zzee zzeeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent, int i) {
        super(googleApiClient);
        this.zzfv = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzay zzayVar) throws RemoteException {
        ((zzcf) zzayVar.getService()).zza(new com.google.android.gms.fitness.request.zzax(this.zzfv, (zzcq) new zzen(this), this.zzgb));
    }
}
