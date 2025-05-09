package com.google.android.gms.fitness.request;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* loaded from: classes8.dex */
public abstract class zzaf extends com.google.android.gms.internal.fitness.zzb implements zzae {
    public zzaf() {
        super("com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onDeviceFound((BleDevice) com.google.android.gms.internal.fitness.zzc.zza(parcel, BleDevice.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            onScanStopped();
        }
        return true;
    }
}
