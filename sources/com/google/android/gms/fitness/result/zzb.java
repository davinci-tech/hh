package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSet;

/* loaded from: classes8.dex */
public final class zzb implements Parcelable.Creator<DailyTotalResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DailyTotalResult[] newArray(int i) {
        return new DailyTotalResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DailyTotalResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        DataSet dataSet = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            } else if (fieldId == 2) {
                dataSet = (DataSet) SafeParcelReader.createParcelable(parcel, readHeader, DataSet.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DailyTotalResult(status, dataSet);
    }
}
