package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzej extends com.google.android.gms.internal.wearable.zzb implements zzei {
    public zzej() {
        super("com.google.android.gms.wearable.internal.IChannelStreamCallbacks");
    }

    @Override // com.google.android.gms.internal.wearable.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zza(parcel.readInt(), parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
