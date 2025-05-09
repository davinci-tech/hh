package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

/* loaded from: classes8.dex */
public abstract class zzbi extends com.google.android.gms.internal.maps.zzb implements zzbh {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onStreetViewPanoramaCameraChange((StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaCamera.CREATOR));
        parcel2.writeNoException();
        return true;
    }

    public zzbi() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaCameraChangeListener");
    }
}
