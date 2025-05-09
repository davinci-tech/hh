package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* loaded from: classes8.dex */
public final class zzbg extends zza implements zzbe {
    zzbg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbe
    public final void zza(DailyTotalResult dailyTotalResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dailyTotalResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
