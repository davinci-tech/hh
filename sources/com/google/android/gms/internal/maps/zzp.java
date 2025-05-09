package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzp extends zza implements zzn {
    @Override // com.google.android.gms.internal.maps.zzn
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean zzb(zzn zznVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zznVar);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final boolean isUnderground() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final List<IBinder> getLevels() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        ArrayList<IBinder> createBinderArrayList = transactAndReadException.createBinderArrayList();
        transactAndReadException.recycle();
        return createBinderArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getDefaultLevelIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzn
    public final int getActiveLevelIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }
}
