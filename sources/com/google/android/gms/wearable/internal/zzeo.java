package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzeo extends com.google.android.gms.internal.wearable.zza implements zzem {
    zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.wearable.internal.IWearableListener");
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(DataHolder dataHolder) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, dataHolder);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzfe zzfeVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzfeVar);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzfo zzfoVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzfoVar);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zzb(zzfo zzfoVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzfoVar);
        transactOneway(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void onConnectedNodes(List<zzfo> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactOneway(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzl zzlVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzlVar);
        transactOneway(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzaw zzawVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzawVar);
        transactOneway(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzah zzahVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzahVar);
        transactOneway(8, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzem
    public final void zza(zzi zziVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zziVar);
        transactOneway(9, obtainAndWriteInterfaceToken);
    }
}
