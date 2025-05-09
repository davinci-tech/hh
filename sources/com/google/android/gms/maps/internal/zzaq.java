package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public abstract class zzaq extends com.google.android.gms.internal.maps.zzb implements zzap {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IGoogleMapDelegate zzgVar;
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            zzgVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            zzgVar = queryLocalInterface instanceof IGoogleMapDelegate ? (IGoogleMapDelegate) queryLocalInterface : new zzg(readStrongBinder);
        }
        zza(zzgVar);
        parcel2.writeNoException();
        return true;
    }

    public zzaq() {
        super("com.google.android.gms.maps.internal.IOnMapReadyCallback");
    }
}
