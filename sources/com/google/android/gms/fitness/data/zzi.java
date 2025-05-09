package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzi implements Parcelable.Creator<DataSet> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSet[] newArray(int i) {
        return new DataSet[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSet createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        DataSource dataSource = null;
        ArrayList arrayList2 = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
            } else if (fieldId == 1000) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, readHeader, arrayList, getClass().getClassLoader());
            } else if (fieldId == 4) {
                arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
            } else if (fieldId == 5) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataSet(i, dataSource, arrayList, arrayList2, z);
    }
}
