package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;

/* loaded from: classes8.dex */
public abstract class zzar extends zzb implements zzaq {
    @Override // com.google.android.gms.internal.location.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((LocationSettingsResult) zzc.zza(parcel, LocationSettingsResult.CREATOR));
        return true;
    }

    public zzar() {
        super("com.google.android.gms.location.internal.ISettingsCallbacks");
    }
}
