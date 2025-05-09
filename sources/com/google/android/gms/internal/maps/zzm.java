package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes2.dex */
public final class zzm extends zza implements zzk {
    @Override // com.google.android.gms.internal.maps.zzk
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(25, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(20, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean zzb(zzk zzkVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzkVar);
        Parcel transactAndReadException = transactAndReadException(19, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void zza(float f, float f2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        obtainAndWriteInterfaceToken.writeFloat(f2);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setZIndex(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setVisible(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setTransparency(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, latLngBounds);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setPosition(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, latLng);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setDimensions(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setClickable(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void setBearing(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final void remove() throws RemoteException {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isVisible() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final boolean isClickable() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(23, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getZIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getWidth() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getTransparency() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLng getPosition() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        LatLng latLng = (LatLng) zzc.zza(transactAndReadException, LatLng.CREATOR);
        transactAndReadException.recycle();
        return latLng;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final String getId() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getHeight() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final LatLngBounds getBounds() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(transactAndReadException, LatLngBounds.CREATOR);
        transactAndReadException.recycle();
        return latLngBounds;
    }

    @Override // com.google.android.gms.internal.maps.zzk
    public final float getBearing() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }
}
