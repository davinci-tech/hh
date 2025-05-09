package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes8.dex */
public final class zzbw extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaViewDelegate {
    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final IObjectWrapper getView() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final void getStreetViewPanoramaAsync(zzbp zzbpVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbpVar);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate
    public final IStreetViewPanoramaDelegate getStreetViewPanorama() throws RemoteException {
        IStreetViewPanoramaDelegate zzbuVar;
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbuVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
            zzbuVar = queryLocalInterface instanceof IStreetViewPanoramaDelegate ? (IStreetViewPanoramaDelegate) queryLocalInterface : new zzbu(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbuVar;
    }

    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }
}
