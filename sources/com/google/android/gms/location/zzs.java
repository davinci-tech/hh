package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public class zzs extends com.google.android.gms.internal.location.zzb implements zzr {
    @Override // com.google.android.gms.internal.location.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        throw new NoSuchMethodError();
    }

    public static zzr zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.IDeviceOrientationListener");
        return queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzt(iBinder);
    }
}
