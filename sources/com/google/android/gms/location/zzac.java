package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzac implements Parcelable.Creator<LocationResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationResult[] newArray(int i) {
        return new LocationResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        List<Location> list = LocationResult.zzbb;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                list = SafeParcelReader.createTypedList(parcel, readHeader, Location.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationResult(list);
    }
}
