package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzw implements Parcelable.Creator<MapValue> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MapValue[] newArray(int i) {
        return new MapValue[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ MapValue createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 2) {
                f = SafeParcelReader.readFloat(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new MapValue(i, f);
    }
}
