package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public abstract class zzo extends com.google.android.gms.internal.maps.zzb implements zzn {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onCameraIdle();
        parcel2.writeNoException();
        return true;
    }

    public zzo() {
        super("com.google.android.gms.maps.internal.IOnCameraIdleListener");
    }
}
