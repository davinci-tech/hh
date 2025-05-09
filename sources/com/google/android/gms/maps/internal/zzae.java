package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzae extends com.google.android.gms.internal.maps.zzb implements zzad {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzg(com.google.android.gms.internal.maps.zzu.zzg(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }

    public zzae() {
        super("com.google.android.gms.maps.internal.IOnInfoWindowCloseListener");
    }
}
