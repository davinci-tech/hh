package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzed implements Parcelable.Creator<zzec> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzec[] newArray(int i) {
        return new zzec[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzec createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzdd zzddVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                zzddVar = (zzdd) SafeParcelReader.createParcelable(parcel, readHeader, zzdd.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzec(i, zzddVar);
    }
}
