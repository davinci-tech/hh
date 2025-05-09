package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzff implements Parcelable.Creator<zzfe> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfe[] newArray(int i) {
        return new zzfe[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfe createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        String str = null;
        byte[] bArr = null;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 4) {
                bArr = SafeParcelReader.createByteArray(parcel, readHeader);
            } else if (fieldId == 5) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfe(i, str, bArr, str2);
    }
}
