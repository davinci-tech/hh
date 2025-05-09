package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;

/* loaded from: classes2.dex */
public final class zzbu extends zza implements zzbt {
    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitBleApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(StartBleScanRequest startBleScanRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, startBleScanRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzbh zzbhVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzbhVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zze zzeVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzeVar);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzbl zzblVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzblVar);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzah zzahVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzahVar);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
}
