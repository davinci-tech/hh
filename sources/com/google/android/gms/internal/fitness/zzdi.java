package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* loaded from: classes8.dex */
final class zzdi extends zzbr {
    private final /* synthetic */ zzdh zzfe;

    zzdi(zzdh zzdhVar) {
        this.zzfe = zzdhVar;
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(GoalsResult goalsResult) throws RemoteException {
        this.zzfe.setResult((zzdh) goalsResult);
    }
}
