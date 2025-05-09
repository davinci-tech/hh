package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes8.dex */
public final class zzk extends com.google.android.gms.internal.maps.zza implements IMapViewDelegate {
    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onExitAmbient() throws RemoteException {
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final IObjectWrapper getView() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final void getMapAsync(zzap zzapVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzapVar);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapViewDelegate
    public final IGoogleMapDelegate getMap() throws RemoteException {
        IGoogleMapDelegate zzgVar;
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzgVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            zzgVar = queryLocalInterface instanceof IGoogleMapDelegate ? (IGoogleMapDelegate) queryLocalInterface : new zzg(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzgVar;
    }

    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapViewDelegate");
    }
}
