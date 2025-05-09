package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityTransitionRequest;

/* loaded from: classes8.dex */
final class zzh extends zzj {
    private final /* synthetic */ ActivityTransitionRequest zzby;
    private final /* synthetic */ PendingIntent zzbz;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzby, this.zzbz, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzh(zze zzeVar, GoogleApiClient googleApiClient, ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzby = activityTransitionRequest;
        this.zzbz = pendingIntent;
    }
}
