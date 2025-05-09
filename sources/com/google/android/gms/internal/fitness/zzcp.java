package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionStopResult;

/* loaded from: classes8.dex */
public final class zzcp extends zza implements zzcn {
    zzcp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionStopCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzcn
    public final void zza(SessionStopResult sessionStopResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, sessionStopResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
