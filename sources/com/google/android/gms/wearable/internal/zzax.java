package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzax implements Parcelable.Creator<zzaw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaw[] newArray(int i) {
        return new zzaw[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaw createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzay zzayVar = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                zzayVar = (zzay) SafeParcelReader.createParcelable(parcel, readHeader, zzay.CREATOR);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 5) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzaw(zzayVar, i, i2, i3);
    }
}
