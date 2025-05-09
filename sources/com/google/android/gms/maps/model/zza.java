package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zza implements Parcelable.Creator<CameraPosition> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CameraPosition createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                latLng = (LatLng) SafeParcelReader.createParcelable(parcel, readHeader, LatLng.CREATOR);
            } else if (fieldId == 3) {
                f = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (fieldId == 4) {
                f2 = SafeParcelReader.readFloat(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                f3 = SafeParcelReader.readFloat(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new CameraPosition(latLng, f, f2, f3);
    }
}
