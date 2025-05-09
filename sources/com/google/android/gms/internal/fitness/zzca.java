package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* loaded from: classes2.dex */
public final class zzca extends zza implements zzbz {
    zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(DataReadRequest dataReadRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataReadRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(com.google.android.gms.fitness.request.zzk zzkVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzkVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(DataDeleteRequest dataDeleteRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataDeleteRequest);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(com.google.android.gms.fitness.request.zzg zzgVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzgVar);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(DataUpdateRequest dataUpdateRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataUpdateRequest);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataUpdateListenerRegistrationRequest);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbz
    public final void zza(com.google.android.gms.fitness.request.zzw zzwVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzwVar);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }
}
