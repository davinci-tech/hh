package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

/* loaded from: classes8.dex */
public abstract class zzcl extends zzb implements zzck {
    public zzcl() {
        super("com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    public static zzck zzh(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
        if (queryLocalInterface instanceof zzck) {
            return (zzck) queryLocalInterface;
        }
        return new zzcm(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((SessionReadResult) zzc.zza(parcel, SessionReadResult.CREATOR));
        return true;
    }
}
