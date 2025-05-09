package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public final class zzal extends zza implements zzaj {
    @Override // com.google.android.gms.internal.location.zzaj
    public final void zza(zzad zzadVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzadVar);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    zzal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.location.internal.IFusedLocationProviderCallback");
    }
}
