package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;

/* loaded from: classes8.dex */
final class zzz extends zzab {
    private final /* synthetic */ LocationListener zzcl;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(ListenerHolders.createListenerKey(this.zzcl, "LocationListener"), new zzac(this));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(zzq zzqVar, GoogleApiClient googleApiClient, LocationListener locationListener) {
        super(googleApiClient);
        this.zzcl = locationListener;
    }
}
