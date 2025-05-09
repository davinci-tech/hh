package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

/* loaded from: classes8.dex */
public final class zzbv extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaFragmentDelegate {
    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onStop() throws RemoteException {
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onStart() throws RemoteException {
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken);
        if (transactAndReadException.readInt() != 0) {
            bundle.readFromParcel(transactAndReadException);
        }
        transactAndReadException.recycle();
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onResume() throws RemoteException {
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onPause() throws RemoteException {
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onLowMemory() throws RemoteException {
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onInflate(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewPanoramaOptions);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onDestroyView() throws RemoteException {
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onDestroy() throws RemoteException {
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
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

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void onCreate(Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, bundle);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final boolean isReady() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
    public final void getStreetViewPanoramaAsync(zzbp zzbpVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbpVar);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate
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

    zzbv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
    }
}
