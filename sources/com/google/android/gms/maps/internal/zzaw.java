package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzaw extends com.google.android.gms.internal.maps.zzb implements zzav {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        boolean onMyLocationButtonClick = onMyLocationButtonClick();
        parcel2.writeNoException();
        com.google.android.gms.internal.maps.zzc.zza(parcel2, onMyLocationButtonClick);
        return true;
    }

    public zzaw() {
        super("com.google.android.gms.maps.internal.IOnMyLocationButtonClickListener");
    }
}
