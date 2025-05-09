package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
public final class zzbo implements Parcelable.Creator<zzbn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbn[] newArray(int i) {
        return new zzbn[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbn createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 2) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
            } else if (fieldId == 3) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzbn(dataType, dataSource, iBinder);
    }
}
