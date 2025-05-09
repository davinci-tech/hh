package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;

/* loaded from: classes2.dex */
public final class zzce extends zza implements zzcd {
    zzce(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, dataSourcesRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(com.google.android.gms.fitness.request.zzao zzaoVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzaoVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(com.google.android.gms.fitness.request.zzar zzarVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzarVar);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
}
