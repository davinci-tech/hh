package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzag implements Parcelable.Creator<LocationSettingsRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationSettingsRequest[] newArray(int i) {
        return new LocationSettingsRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ LocationSettingsRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        boolean z = false;
        boolean z2 = false;
        zzae zzaeVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, LocationRequest.CREATOR);
            } else if (fieldId == 2) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId == 3) {
                z2 = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzaeVar = (zzae) SafeParcelReader.createParcelable(parcel, readHeader, zzae.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LocationSettingsRequest(arrayList, z, z2, zzaeVar);
    }
}
