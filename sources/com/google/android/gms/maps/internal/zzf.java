package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;

/* loaded from: classes2.dex */
public final class zzf extends com.google.android.gms.internal.maps.zza implements zze {
    @Override // com.google.android.gms.maps.internal.zze
    public final com.google.android.gms.internal.maps.zze zze() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        com.google.android.gms.internal.maps.zze zzb = com.google.android.gms.internal.maps.zzf.zzb(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzb;
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final IStreetViewPanoramaFragmentDelegate zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        IStreetViewPanoramaFragmentDelegate zzbvVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbvVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate");
            zzbvVar = queryLocalInterface instanceof IStreetViewPanoramaFragmentDelegate ? (IStreetViewPanoramaFragmentDelegate) queryLocalInterface : new zzbv(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbvVar;
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final ICameraUpdateFactoryDelegate zzd() throws RemoteException {
        ICameraUpdateFactoryDelegate zzbVar;
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
            zzbVar = queryLocalInterface instanceof ICameraUpdateFactoryDelegate ? (ICameraUpdateFactoryDelegate) queryLocalInterface : new zzb(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbVar;
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final IMapFragmentDelegate zzc(IObjectWrapper iObjectWrapper) throws RemoteException {
        IMapFragmentDelegate zzjVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzjVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
            zzjVar = queryLocalInterface instanceof IMapFragmentDelegate ? (IMapFragmentDelegate) queryLocalInterface : new zzj(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzjVar;
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final IStreetViewPanoramaViewDelegate zza(IObjectWrapper iObjectWrapper, StreetViewPanoramaOptions streetViewPanoramaOptions) throws RemoteException {
        IStreetViewPanoramaViewDelegate zzbwVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewPanoramaOptions);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzbwVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
            zzbwVar = queryLocalInterface instanceof IStreetViewPanoramaViewDelegate ? (IStreetViewPanoramaViewDelegate) queryLocalInterface : new zzbw(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzbwVar;
    }

    @Override // com.google.android.gms.maps.internal.zze
    public final IMapViewDelegate zza(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions) throws RemoteException {
        IMapViewDelegate zzkVar;
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, googleMapOptions);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = transactAndReadException.readStrongBinder();
        if (readStrongBinder == null) {
            zzkVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapViewDelegate");
            zzkVar = queryLocalInterface instanceof IMapViewDelegate ? (IMapViewDelegate) queryLocalInterface : new zzk(readStrongBinder);
        }
        transactAndReadException.recycle();
        return zzkVar;
    }

    zzf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICreator");
    }
}
