package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

/* loaded from: classes8.dex */
final class zzt extends zzab {
    private final /* synthetic */ boolean zzcn;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzcn);
        setResult((zzt) Status.RESULT_SUCCESS);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzt(zzq zzqVar, GoogleApiClient googleApiClient, boolean z) {
        super(googleApiClient);
        this.zzcn = z;
    }
}
