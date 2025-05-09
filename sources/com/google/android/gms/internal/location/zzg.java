package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes8.dex */
final class zzg extends zzj {
    private final /* synthetic */ PendingIntent zzbx;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zzb(this.zzbx);
        setResult((zzg) Status.RESULT_SUCCESS);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzg(zze zzeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzbx = pendingIntent;
    }
}
