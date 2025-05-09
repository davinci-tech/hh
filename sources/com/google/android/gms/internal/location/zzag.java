package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;

/* loaded from: classes8.dex */
final class zzag extends zzai {
    private final /* synthetic */ PendingIntent zzbz;
    private final /* synthetic */ GeofencingRequest zzcs;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzcs, this.zzbz, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzag(zzaf zzafVar, GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzcs = geofencingRequest;
        this.zzbz = pendingIntent;
    }
}
