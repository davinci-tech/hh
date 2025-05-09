package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* loaded from: classes8.dex */
public final class zzbs extends zza implements zzbq {
    zzbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(GoalsResult goalsResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, goalsResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
