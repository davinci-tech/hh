package com.google.android.gms.internal.location;

import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

/* loaded from: classes8.dex */
final class zzw extends zzab {
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationListener zzcl;
    private final /* synthetic */ Looper zzcp;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzck, ListenerHolders.createListenerHolder(this.zzcl, zzbm.zza(this.zzcp), "LocationListener"), new zzac(this));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzw(zzq zzqVar, GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        super(googleApiClient);
        this.zzck = locationRequest;
        this.zzcl = locationListener;
        this.zzcp = looper;
    }
}
