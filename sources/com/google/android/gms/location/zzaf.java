package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes8.dex */
public final class zzaf implements Parcelable.Creator<zzae> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzae[] newArray(int i) {
        return new zzae[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzae createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = "";
        String str2 = "";
        String str3 = str2;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                str3 = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzae(str, str2, str3);
    }
}
