package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;

/* loaded from: classes8.dex */
public final class zzex implements Parcelable.Creator<zzew> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew[] newArray(int i) {
        return new zzew[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSource dataSource = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) == 1) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzew(dataSource);
    }
}
