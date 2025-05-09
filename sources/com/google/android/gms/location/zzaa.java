package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes8.dex */
public final class zzaa implements Parcelable.Creator<LocationAvailability> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationAvailability[] newArray(int i) {
        return new LocationAvailability[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationAvailability createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 1000;
        int i2 = 1;
        int i3 = 1;
        long j = 0;
        zzaj[] zzajVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i2 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                i3 = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                j = SafeParcelReader.readLong(parcel, readHeader);
            } else if (fieldId == 4) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzajVarArr = (zzaj[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzaj.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationAvailability(i, i2, i3, j, zzajVarArr);
    }
}
