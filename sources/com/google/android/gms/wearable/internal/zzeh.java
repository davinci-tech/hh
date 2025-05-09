package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzeh implements Parcelable.Creator<zzeg> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeg[] newArray(int i) {
        return new zzeg[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeg createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzfo zzfoVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                zzfoVar = (zzfo) SafeParcelReader.createParcelable(parcel, readHeader, zzfo.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzeg(i, zzfoVar);
    }
}
