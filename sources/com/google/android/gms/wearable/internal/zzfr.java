package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzfr implements Parcelable.Creator<zzfq> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfq[] newArray(int i) {
        return new zzfq[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzfq createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzay zzayVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                zzayVar = (zzay) SafeParcelReader.createParcelable(parcel, readHeader, zzay.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzfq(i, zzayVar);
    }
}
