package com.google.android.gms.maps.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

/* loaded from: classes8.dex */
public abstract class zzbk extends com.google.android.gms.internal.maps.zzb implements zzbj {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onStreetViewPanoramaChange((StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(parcel, StreetViewPanoramaLocation.CREATOR));
        parcel2.writeNoException();
        return true;
    }

    public zzbk() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaChangeListener");
    }
}
