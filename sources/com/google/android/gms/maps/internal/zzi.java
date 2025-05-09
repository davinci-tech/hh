package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes8.dex */
public abstract class zzi extends com.google.android.gms.internal.maps.zzb implements zzh {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IObjectWrapper zzh;
        if (i == 1) {
            zzh = zzh(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        } else {
            if (i != 2) {
                return false;
            }
            zzh = zzi(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        com.google.android.gms.internal.maps.zzc.zza(parcel2, zzh);
        return true;
    }

    public zzi() {
        super("com.google.android.gms.maps.internal.IInfoWindowAdapter");
    }
}
