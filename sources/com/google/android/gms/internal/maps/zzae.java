package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes8.dex */
public final class zzae extends zza implements zzac {
    @Override // com.google.android.gms.internal.maps.zzac
    public final int zzi() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(9, obtainAndWriteInterfaceToken());
        int readInt = transactAndReadException.readInt();
        transactAndReadException.recycle();
        return readInt;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean zza(zzac zzacVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, zzacVar);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setZIndex(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setVisible(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setTransparency(float f) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeFloat(f);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void setFadeIn(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void remove() throws RemoteException {
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken());
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean isVisible() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final float getZIndex() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final float getTransparency() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(13, obtainAndWriteInterfaceToken());
        float readFloat = transactAndReadException.readFloat();
        transactAndReadException.recycle();
        return readFloat;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final String getId() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        String readString = transactAndReadException.readString();
        transactAndReadException.recycle();
        return readString;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final boolean getFadeIn() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken());
        boolean zza = zzc.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    @Override // com.google.android.gms.internal.maps.zzac
    public final void clearTileCache() throws RemoteException {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }

    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }
}
