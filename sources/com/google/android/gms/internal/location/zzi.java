package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzi extends zzj {
    private final /* synthetic */ PendingIntent zzbz;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzbz, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzi(zze zzeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzbz = pendingIntent;
    }
}
