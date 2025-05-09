package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzaa extends com.google.android.gms.internal.maps.zzb implements zzz {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onIndoorBuildingFocused();
        } else {
            if (i != 2) {
                return false;
            }
            zza(com.google.android.gms.internal.maps.zzo.zze(parcel.readStrongBinder()));
        }
        parcel2.writeNoException();
        return true;
    }

    public zzaa() {
        super("com.google.android.gms.maps.internal.IOnIndoorStateChangeListener");
    }
}
