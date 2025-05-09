package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.fitness.request.SessionReadRequest;

/* loaded from: classes2.dex */
public interface zzcf extends IInterface {
    void zza(SessionInsertRequest sessionInsertRequest) throws RemoteException;

    void zza(SessionReadRequest sessionReadRequest) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzax zzaxVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzaz zzazVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzbb zzbbVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzbd zzbdVar) throws RemoteException;
}
