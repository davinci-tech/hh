package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public final class zzcc extends zza implements zzcb {
    zzcc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzbj zzbjVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzbjVar);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzbn zzbnVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzbnVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzaj zzajVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzajVar);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
}
