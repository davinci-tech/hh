package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public abstract class zzam extends com.google.android.gms.internal.maps.zzb implements zzal {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onMapLoaded();
        parcel2.writeNoException();
        return true;
    }

    public zzam() {
        super("com.google.android.gms.maps.internal.IOnMapLoadedCallback");
    }
}
