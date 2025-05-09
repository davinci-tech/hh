package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.wearable.ConnectionConfiguration;

/* loaded from: classes2.dex */
public final class zzdz implements Parcelable.Creator<zzdy> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdy[] newArray(int i) {
        return new zzdy[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdy createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ConnectionConfiguration[] connectionConfigurationArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                connectionConfigurationArr = (ConnectionConfiguration[]) SafeParcelReader.createTypedArray(parcel, readHeader, ConnectionConfiguration.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzdy(i, connectionConfigurationArr);
    }
}
