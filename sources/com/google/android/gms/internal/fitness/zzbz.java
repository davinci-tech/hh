package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateListenerRegistrationRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;

/* loaded from: classes2.dex */
public interface zzbz extends IInterface {
    void zza(DataDeleteRequest dataDeleteRequest) throws RemoteException;

    void zza(DataReadRequest dataReadRequest) throws RemoteException;

    void zza(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest) throws RemoteException;

    void zza(DataUpdateRequest dataUpdateRequest) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzg zzgVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzk zzkVar) throws RemoteException;

    void zza(com.google.android.gms.fitness.request.zzw zzwVar) throws RemoteException;
}
