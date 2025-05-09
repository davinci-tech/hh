package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

/* loaded from: classes2.dex */
public final class zzbr extends com.google.android.gms.internal.maps.zza implements IProjectionDelegate {
    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final IObjectWrapper toScreenLocation(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, latLng);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final VisibleRegion getVisibleRegion() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        VisibleRegion visibleRegion = (VisibleRegion) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, VisibleRegion.CREATOR);
        transactAndReadException.recycle();
        return visibleRegion;
    }

    @Override // com.google.android.gms.maps.internal.IProjectionDelegate
    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        LatLng latLng = (LatLng) com.google.android.gms.internal.maps.zzc.zza(transactAndReadException, LatLng.CREATOR);
        transactAndReadException.recycle();
        return latLng;
    }

    zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }
}
