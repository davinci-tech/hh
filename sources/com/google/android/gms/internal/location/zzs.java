package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationCallback;

/* loaded from: classes8.dex */
final class zzs extends zzab {
    private final /* synthetic */ LocationCallback zzcm;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zzb(ListenerHolders.createListenerKey(this.zzcm, "LocationCallback"), new zzac(this));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzs(zzq zzqVar, GoogleApiClient googleApiClient, LocationCallback locationCallback) {
        super(googleApiClient);
        this.zzcm = locationCallback;
    }
}
