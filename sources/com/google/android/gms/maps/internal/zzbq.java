package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzbq extends com.google.android.gms.internal.maps.zzb implements zzbp {
    @Override // com.google.android.gms.internal.maps.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        IStreetViewPanoramaDelegate zzbuVar;
        if (i != 1) {
            return false;
        }
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            zzbuVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            zzbuVar = queryLocalInterface instanceof IStreetViewPanoramaDelegate ? (IStreetViewPanoramaDelegate) queryLocalInterface : new zzbu(readStrongBinder);
        }
        zza(zzbuVar);
        parcel2.writeNoException();
        return true;
    }

    public zzbq() {
        super("com.google.android.gms.maps.internal.IOnStreetViewPanoramaReadyCallback");
    }
}
