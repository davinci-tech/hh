package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes8.dex */
final class zzcv extends zzu {
    private final /* synthetic */ com.google.android.gms.fitness.request.zzae zzey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcv(zzct zzctVar, GoogleApiClient googleApiClient, com.google.android.gms.fitness.request.zzae zzaeVar) {
        super(googleApiClient);
        this.zzey = zzaeVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zzp zzpVar) throws RemoteException {
        ((zzbt) zzpVar.getService()).zza(new com.google.android.gms.fitness.request.zzbh(this.zzey, new zzen(this)));
    }
}
