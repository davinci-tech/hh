package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzba extends com.google.android.gms.internal.maps.zzb implements zzaz {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onMyLocationClick((Location) com.google.android.gms.internal.maps.zzc.zza(parcel, Location.CREATOR));
        parcel2.writeNoException();
        return true;
    }

    public zzba() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }
}
