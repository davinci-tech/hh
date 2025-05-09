package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* loaded from: classes8.dex */
public final class zzbj extends zza implements zzbh {
    zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbh
    public final void zza(DataReadResult dataReadResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataReadResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
