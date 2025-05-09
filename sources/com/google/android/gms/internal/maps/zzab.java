package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class zzab extends zza implements zzz {
    @Override // com.google.android.gms.internal.maps.zzz
    public final IObjectWrapper zzj() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(28, obtainAndWriteInterfaceToken());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(16, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        transactAndReadExceptionReturnVoid(27, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean zzb(zzz zzzVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzzVar);
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setZIndex(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setWidth(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setVisible(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setStartCap(Cap cap) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, cap);
        transactAndReadExceptionReturnVoid(19, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setPoints(List<LatLng> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setPattern(List<PatternItem> list) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeTypedList(list);
        transactAndReadExceptionReturnVoid(25, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setJointType(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(23, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setGeodesic(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setEndCap(Cap cap) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, cap);
        transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setColor(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void setClickable(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final void remove() throws RemoteException {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isVisible() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(12, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isGeodesic() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(14, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final boolean isClickable() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(18, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final float getZIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final float getWidth() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(6, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final Cap getStartCap() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(20, obtainAndWriteInterfaceToken());
        Cap cap = (Cap) zzc.zza(transactAndReadException, Cap.CREATOR);
        transactAndReadException.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final List<LatLng> getPoints() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(4, obtainAndWriteInterfaceToken());
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(LatLng.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final List<PatternItem> getPattern() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(26, obtainAndWriteInterfaceToken());
        ArrayList createTypedArrayList = transactAndReadException.createTypedArrayList(PatternItem.CREATOR);
        transactAndReadException.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int getJointType() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(24, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final String getId() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final Cap getEndCap() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(22, obtainAndWriteInterfaceToken());
        Cap cap = (Cap) zzc.zza(transactAndReadException, Cap.CREATOR);
        transactAndReadException.recycle();
        return cap;
    }

    @Override // com.google.android.gms.internal.maps.zzz
    public final int getColor() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }
}
