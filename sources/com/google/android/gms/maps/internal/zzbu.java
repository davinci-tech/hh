package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

/* loaded from: classes8.dex */
public final class zzbu extends com.google.android.gms.internal.maps.zza implements IStreetViewPanoramaDelegate {
    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLng);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewSource);
        transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadiusAndSource(LatLng latLng, int i, StreetViewSource streetViewSource) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLng);
        obtainAndWriteInterfaceToken.writeInt(i);
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewSource);
        transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithRadius(LatLng latLng, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLng);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPositionWithID(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLng);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaLongClickListener(zzbn zzbnVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbnVar);
        transactAndReadExceptionReturnVoid(20, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaClickListener(zzbl zzblVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzblVar);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaChangeListener(zzbj zzbjVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbjVar);
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbhVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, zzbhVar);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, StreetViewPanoramaOrientation.CREATOR);
        transactAndReadException.recycle();
        return streetViewPanoramaOrientation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewPanoramaOrientation);
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isUserNavigationEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isStreetNamesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final boolean isPanningGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, StreetViewPanoramaLocation.CREATOR);
        transactAndReadException.recycle();
        return streetViewPanoramaLocation;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final StreetViewPanoramaCamera getPanoramaCamera() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, StreetViewPanoramaCamera.CREATOR);
        transactAndReadException.recycle();
        return streetViewPanoramaCamera;
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableZoom(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableUserNavigation(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enableStreetNames(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void enablePanning(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, streetViewPanoramaCamera);
        obtainAndWriteInterfaceToken.writeLong(j);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }
}
