package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes2.dex */
public final class zzbx extends com.google.android.gms.internal.maps.zza implements IUiSettingsDelegate {
    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomGesturesEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomControlsEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setTiltGesturesEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setScrollGesturesEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setRotateGesturesEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMapToolbarEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setIndoorLevelPickerEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setCompassEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setAllGesturesEnabled(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.maps.zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isZoomControlsEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isTiltGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isScrollGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isRotateGesturesEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMyLocationButtonEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isMapToolbarEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isIndoorLevelPickerEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final boolean isCompassEnabled() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        boolean zza = com.google.android.gms.internal.maps.zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }
}
