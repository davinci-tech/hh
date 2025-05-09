package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.GoalsReadRequest;

/* loaded from: classes2.dex */
public final class zzby extends zza implements zzbx {
    zzby(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbx
    public final void zza(GoalsReadRequest goalsReadRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, goalsReadRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
