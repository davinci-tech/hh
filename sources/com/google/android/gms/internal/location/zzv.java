package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzv extends zzab {
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(new zzac(this));
    }

    zzv(zzq zzqVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
}
