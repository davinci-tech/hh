package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes8.dex */
public abstract class zzay extends com.google.android.gms.internal.maps.zzb implements zzax {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
        parcel2.writeNoException();
        return true;
    }

    public zzay() {
        super("com.google.android.gms.maps.internal.IOnMyLocationChangeListener");
    }
}
