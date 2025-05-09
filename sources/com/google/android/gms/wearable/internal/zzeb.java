package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzeb implements Parcelable.Creator<zzea> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzea[] newArray(int i) {
        return new zzea[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzea createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zzfo.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzea(i, arrayList);
    }
}
