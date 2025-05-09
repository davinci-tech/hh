package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzj extends zza implements zzh {
    @Override // com.google.android.gms.internal.maps.zzh
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(24, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(23, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean zzb(zzh zzhVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzhVar);
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setZIndex(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setVisible(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokeWidth(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokePattern(List<PatternItem> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setStrokeColor(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setRadius(double d) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeDouble(d);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setFillColor(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setClickable(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(19, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void setCenter(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, latLng);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final void remove() throws RemoteException {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean isVisible() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final boolean isClickable() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(20, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final float getZIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final float getStrokeWidth() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final List<PatternItem> getStrokePattern() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(22, obtainAndWriteInterfaceToken());
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(PatternItem.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int getStrokeColor() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final double getRadius() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        double readDouble = transactAndReadException.readDouble();
        transactAndReadException.recycle();
        return readDouble;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final String getId() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final int getFillColor() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzh
    public final LatLng getCenter() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        LatLng latLng = (LatLng) zzc.zza(transactAndReadException, LatLng.CREATOR);
        transactAndReadException.recycle();
        return latLng;
    }

    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }
}
