package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzv extends com.google.android.gms.internal.location.zzb implements zzu {
    @Override // com.google.android.gms.internal.location.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onLocationResult((LocationResult) com.google.android.gms.internal.location.zzc.zza(parcel, LocationResult.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            onLocationAvailability((LocationAvailability) com.google.android.gms.internal.location.zzc.zza(parcel, LocationAvailability.CREATOR));
        }
        return true;
    }

    public static zzu zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
        return queryLocalInterface instanceof zzu ? (zzu) queryLocalInterface : new zzw(iBinder);
    }

    public zzv() {
        super("com.google.android.gms.location.ILocationCallback");
    }
}
