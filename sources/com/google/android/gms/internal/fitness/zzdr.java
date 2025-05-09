package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* loaded from: classes8.dex */
final class zzdr extends zzbf {
    private final /* synthetic */ zzdq zzfo;

    zzdr(zzdq zzdqVar) {
        this.zzfo = zzdqVar;
    }

    @Override // com.google.android.gms.internal.fitness.zzbe
    public final void zza(DailyTotalResult dailyTotalResult) throws RemoteException {
        this.zzfo.setResult((zzdq) dailyTotalResult);
    }
}
