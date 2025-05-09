package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes8.dex */
final class zzu extends zzab {
    private final /* synthetic */ Location zzco;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzco);
        setResult((zzu) Status.RESULT_SUCCESS);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzu(zzq zzqVar, GoogleApiClient googleApiClient, Location location) {
        super(googleApiClient);
        this.zzco = location;
    }
}
