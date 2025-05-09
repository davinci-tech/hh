package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzh implements Parcelable.Creator<DataPoint> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataPoint[] newArray(int i) {
        return new DataPoint[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataPoint createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSource dataSource = null;
        Value[] valueArr = null;
        DataSource dataSource2 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 2:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    valueArr = (Value[]) SafeParcelReader.createTypedArray(parcel, readHeader, Value.CREATOR);
                    break;
                case 6:
                    dataSource2 = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 7:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 8:
                    j4 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataPoint(dataSource, j, j2, valueArr, dataSource2, j3, j4);
    }
}
