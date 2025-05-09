package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public final class zzv extends zza implements zzt {
    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean zzj(zzt zztVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zztVar);
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(30, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(29, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void showInfoWindow() throws RemoteException {
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setZIndex(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(27, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setVisible(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setTitle(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setSnippet(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setRotation(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, latLng);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setInfoWindowAnchor(float f, float f2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        obtainAndWriteInterfaceToken.writeFloat(f2);
        transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setFlat(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(20, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setDraggable(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setAnchor(float f, float f2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        obtainAndWriteInterfaceToken.writeFloat(f2);
        transactAndReadExceptionReturnVoid(19, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void setAlpha(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(25, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void remove() throws RemoteException {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isVisible() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isInfoWindowShown() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isFlat() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(21, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final boolean isDraggable() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final void hideInfoWindow() throws RemoteException {
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getZIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(28, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getTitle() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getSnippet() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getRotation() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(23, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final LatLng getPosition() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        LatLng latLng = (LatLng) zzc.zza(transactAndReadException, LatLng.CREATOR);
        transactAndReadException.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final String getId() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzt
    public final float getAlpha() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(26, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }
}
