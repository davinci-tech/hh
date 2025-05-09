package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzdo extends zzal {
    private final /* synthetic */ PendingIntent zzfk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdo(zzdj zzdjVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzfk = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzag zzagVar) throws RemoteException {
        ((zzbz) zzagVar.getService()).zza(new com.google.android.gms.fitness.request.zzw(this.zzfk, new zzen(this)));
    }
}
