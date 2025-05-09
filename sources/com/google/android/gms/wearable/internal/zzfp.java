package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzfp implements Parcelable.Creator<zzfo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfo[] newArray(int i) {
        return new zzfo[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfo createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        int i = 0;
        boolean z = false;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 3) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 4) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 5) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfo(str, str2, i, z);
    }
}
