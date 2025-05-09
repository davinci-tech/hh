package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

/* loaded from: classes8.dex */
public abstract class zzbo extends com.google.android.gms.internal.maps.zzb implements zzbn {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onStreetViewPanoramaLongClick((StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaOrientation.CREATOR));
        parcel2.writeNoException();
        return true;
    }

    public zzbo() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaLongClickListener");
    }
}
