package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* loaded from: classes8.dex */
public final class zzag extends com.google.android.gms.internal.fitness.zza implements zzae {
    zzag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.request.IBleScanCallback");
    }

    @Override // com.google.android.gms.fitness.request.zzae
    public final void onDeviceFound(BleDevice bleDevice) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.fitness.zzc.zza(obtainAndWriteInterfaceToken, bleDevice);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.fitness.request.zzae
    public final void onScanStopped() throws RemoteException {
        transactOneway(2, obtainAndWriteInterfaceToken());
    }
}
