package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

/* loaded from: classes2.dex */
public final class zzbw extends zza implements zzbv {
    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataTypeCreateRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(com.google.android.gms.fitness.request.zzs zzsVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzsVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(com.google.android.gms.fitness.request.zzaa zzaaVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzaaVar);
        transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }
}
