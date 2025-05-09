package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzaa extends zzab {
    private final /* synthetic */ PendingIntent zzbx;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzbx, new zzac(this));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaa(zzq zzqVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzbx = pendingIntent;
    }
}
