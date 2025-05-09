package com.google.android.gms.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public final class zzw extends com.google.android.gms.internal.location.zza implements zzu {
    @Override // com.google.android.gms.location.zzu
    public final void onLocationResult(LocationResult locationResult) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.location.zzc.zza(obtainAndWriteInterfaceToken, locationResult);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.location.zzu
    public final void onLocationAvailability(LocationAvailability locationAvailability) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.location.zzc.zza(obtainAndWriteInterfaceToken, locationAvailability);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.ILocationCallback");
    }
}
