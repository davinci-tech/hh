package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes2.dex */
public final class zzj extends com.google.android.gms.internal.maps.zza implements IMapFragmentDelegate {
    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onInflate(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, googleMapOptions);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onExitAmbient() throws RemoteException {
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onEnterAmbient(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onDestroyView() throws RemoteException {
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper2);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final boolean isReady() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
    public final void getMapAsync(zzap zzapVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzapVar);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IMapFragmentDelegate
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

    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }
}
