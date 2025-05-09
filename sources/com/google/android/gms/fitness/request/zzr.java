package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzr implements Parcelable.Creator<DataTypeCreateRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataTypeCreateRequest[] newArray(int i) {
        return new DataTypeCreateRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataTypeCreateRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        ArrayList arrayList = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, Field.CREATOR);
            } else if (fieldId == 3) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataTypeCreateRequest(str, arrayList, iBinder);
    }
}
