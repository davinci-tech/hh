package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Session;

/* loaded from: classes8.dex */
public final class zzba implements Parcelable.Creator<zzaz> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaz[] newArray(int i) {
        return new zzaz[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaz createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
            } else if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzaz(session, iBinder);
    }
}
