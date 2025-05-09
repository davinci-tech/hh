package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzev implements Parcelable.Creator<zzeu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeu[] newArray(int i) {
        return new zzeu[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzeu createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzeu(arrayList);
    }
}
