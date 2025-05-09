package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
public final class zzak implements Parcelable.Creator<zzaj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaj[] newArray(int i) {
        return new zzaj[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzaj createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
            } else if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzaj(dataType, iBinder);
    }
}
