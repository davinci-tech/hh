package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public final class zzs extends zza implements zzq {
    @Override // com.google.android.gms.internal.maps.zzq
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzq
    public final boolean zzb(zzq zzqVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzqVar);
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzq
    public final String getShortName() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzq
    public final String getName() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzq
    public final void activate() throws RemoteException {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }
}
