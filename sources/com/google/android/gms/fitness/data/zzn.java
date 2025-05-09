package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes8.dex */
public final class zzn implements Parcelable.Creator<DataUpdateNotification> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataUpdateNotification[] newArray(int i) {
        return new DataUpdateNotification[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataUpdateNotification createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        int i = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                j = SafeParcelReader.readLong(parcel, readHeader);
            } else if (fieldId == 2) {
                j2 = SafeParcelReader.readLong(parcel, readHeader);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 4) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
            } else if (fieldId == 5) {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataUpdateNotification(j, j2, i, dataSource, dataType);
    }
}
