package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzah extends zzai {
    private final /* synthetic */ com.google.android.gms.location.zzal zzct;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzaz zzazVar) throws RemoteException {
        zzazVar.zza(this.zzct, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzah(zzaf zzafVar, GoogleApiClient googleApiClient, com.google.android.gms.location.zzal zzalVar) {
        super(googleApiClient);
        this.zzct = zzalVar;
    }
}
