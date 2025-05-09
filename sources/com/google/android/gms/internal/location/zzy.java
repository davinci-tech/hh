package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/* loaded from: classes8.dex */
final class zzy extends zzab {
    private final /* synthetic */ PendingIntent zzbx;
    private final /* synthetic */ LocationRequest zzck;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzck, this.zzbx, new zzac(this));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzy(zzq zzqVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzck = locationRequest;
        this.zzbx = pendingIntent;
    }
}
