package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;

/* loaded from: classes8.dex */
public abstract class zzez extends zzb implements zzey {
    public zzez() {
        super("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza((zzeu) zzc.zza(parcel, zzeu.CREATOR), zzbl.zzd(parcel.readStrongBinder()));
        } else if (i == 2) {
            zza((FitnessSensorServiceRequest) zzc.zza(parcel, FitnessSensorServiceRequest.CREATOR), zzcr.zzj(parcel.readStrongBinder()));
        } else {
            if (i != 3) {
                return false;
            }
            zza((zzew) zzc.zza(parcel, zzew.CREATOR), zzcr.zzj(parcel.readStrongBinder()));
        }
        return true;
    }
}
