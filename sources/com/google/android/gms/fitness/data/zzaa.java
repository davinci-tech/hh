package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzaa implements Parcelable.Creator<RawDataSet> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawDataSet[] newArray(int i) {
        return new RawDataSet[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawDataSet createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ArrayList arrayList = null;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, RawDataPoint.CREATOR);
            } else if (fieldId == 4) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new RawDataSet(i, arrayList, z);
    }
}
