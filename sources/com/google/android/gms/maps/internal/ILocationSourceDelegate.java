package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface ILocationSourceDelegate extends IInterface {
    void activate(zzah zzahVar) throws RemoteException;

    void deactivate() throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.maps.zzb implements ILocationSourceDelegate {
        @Override // com.google.android.gms.internal.maps.zzb
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            zzah zzaiVar;
            if (i == 1) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    zzaiVar = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
                    zzaiVar = queryLocalInterface instanceof zzah ? (zzah) queryLocalInterface : new zzai(readStrongBinder);
                }
                activate(zzaiVar);
            } else {
                if (i != 2) {
                    return false;
                }
                deactivate();
            }
            parcel2.writeNoException();
            return true;
        }

        public zza() {
            super("com.google.android.gms.maps.internal.ILocationSourceDelegate");
        }
    }
}
