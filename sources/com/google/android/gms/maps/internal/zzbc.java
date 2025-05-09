package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.PointOfInterest;

/* loaded from: classes8.dex */
public abstract class zzbc extends com.google.android.gms.internal.maps.zzb implements zzbb {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((PointOfInterest) com.google.android.gms.internal.maps.zzc.zza(parcel, PointOfInterest.CREATOR));
        parcel2.writeNoException();
        return true;
    }

    public zzbc() {
        super("com.google.android.gms.maps.internal.IOnPoiClickListener");
    }
}
