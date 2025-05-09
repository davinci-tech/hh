package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public final class zzai extends com.google.android.gms.internal.maps.zza implements zzah {
    @Override // com.google.android.gms.maps.internal.zzah
    public final void zza(Location location) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, location);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IOnLocationChangeListener");
    }
}
