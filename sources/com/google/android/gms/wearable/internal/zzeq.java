package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;

/* loaded from: classes2.dex */
public final class zzeq extends com.google.android.gms.internal.wearable.zza implements zzep {
    zzeq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.wearable.internal.IWearableService");
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, PutDataRequest putDataRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, putDataRequest);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, Uri uri) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, uri);
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, Uri uri, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, uri);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(40, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzb(zzek zzekVar, Uri uri, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, uri);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(41, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str, String str2, byte[] bArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        transactAndReadExceptionReturnVoid(12, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, Asset asset) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, asset);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzb(zzek zzekVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzc(zzek zzekVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(42, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(43, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(46, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzb(zzek zzekVar, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(47, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, zzd zzdVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzdVar);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, zzfw zzfwVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzfwVar);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str, String str2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        transactAndReadExceptionReturnVoid(31, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzc(zzek zzekVar, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(32, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzb(zzek zzekVar, String str, int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeInt(i);
        transactAndReadExceptionReturnVoid(33, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, zzei zzeiVar, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzeiVar);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(34, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zzb(zzek zzekVar, zzei zzeiVar, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzeiVar);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(35, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, parcelFileDescriptor);
        transactAndReadExceptionReturnVoid(38, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.wearable.internal.zzep
    public final void zza(zzek zzekVar, String str, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, zzekVar);
        obtainAndWriteInterfaceToken.writeString(str);
        com.google.android.gms.internal.wearable.zzc.zza(obtainAndWriteInterfaceToken, parcelFileDescriptor);
        obtainAndWriteInterfaceToken.writeLong(j);
        obtainAndWriteInterfaceToken.writeLong(j2);
        transactAndReadExceptionReturnVoid(39, obtainAndWriteInterfaceToken);
    }
}
