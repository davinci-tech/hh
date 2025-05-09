package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* loaded from: classes8.dex */
public abstract class zzbi extends zzb implements zzbh {
    public zzbi() {
        super("com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    public static zzbh zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataReadCallback");
        if (queryLocalInterface instanceof zzbh) {
            return (zzbh) queryLocalInterface;
        }
        return new zzbj(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((DataReadResult) zzc.zza(parcel, DataReadResult.CREATOR));
        return true;
    }
}
