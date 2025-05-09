package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public abstract class zzu extends com.google.android.gms.internal.fitness.zzb implements zzt {
    public zzu() {
        super("com.google.android.gms.fitness.data.IDataSourceListener");
    }

    public static zzt zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.data.IDataSourceListener");
        if (queryLocalInterface instanceof zzt) {
            return (zzt) queryLocalInterface;
        }
        return new zzv(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((DataPoint) com.google.android.gms.internal.fitness.zzc.zza(parcel, DataPoint.CREATOR));
        return true;
    }
}
