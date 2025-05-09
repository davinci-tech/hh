package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

/* loaded from: classes8.dex */
public abstract class zzbo extends zzb implements zzbn {
    public zzbo() {
        super("com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    public static zzbn zze(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataTypeCallback");
        if (queryLocalInterface instanceof zzbn) {
            return (zzbn) queryLocalInterface;
        }
        return new zzbp(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((DataTypeResult) zzc.zza(parcel, DataTypeResult.CREATOR));
        return true;
    }
}
