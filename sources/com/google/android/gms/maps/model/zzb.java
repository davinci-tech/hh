package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzb implements Parcelable.Creator<Cap> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Cap[] newArray(int i) {
        return new Cap[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Cap createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        IBinder iBinder = null;
        Float f = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f = SafeParcelReader.readFloatObject(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Cap(i, iBinder, f);
    }
}
