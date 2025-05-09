package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;

/* loaded from: classes2.dex */
public final class zzcg extends zza implements zzcf {
    zzcg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(com.google.android.gms.fitness.request.zzaz zzazVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzazVar);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(com.google.android.gms.fitness.request.zzbb zzbbVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzbbVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, sessionInsertRequest);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(SessionReadRequest sessionReadRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, sessionReadRequest);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(com.google.android.gms.fitness.request.zzax zzaxVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzaxVar);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcf
    public final void zza(com.google.android.gms.fitness.request.zzbd zzbdVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzbdVar);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
}
