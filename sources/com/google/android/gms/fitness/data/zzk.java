package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzk implements Parcelable.Creator<DataSource> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource[] newArray(int i) {
        return new DataSource[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        String str = null;
        Device device = null;
        zzb zzbVar = null;
        String str2 = null;
        int[] iArr = null;
        int i = 0;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    dataType = (DataType) SafeParcelReader.createParcelable(parcel, readHeader, DataType.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 4:
                    device = (Device) SafeParcelReader.createParcelable(parcel, readHeader, Device.CREATOR);
                    break;
                case 5:
                    zzbVar = (zzb) SafeParcelReader.createParcelable(parcel, readHeader, zzb.CREATOR);
                    break;
                case 6:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 8:
                    iArr = SafeParcelReader.createIntArray(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataSource(dataType, str, i, device, zzbVar, str2, iArr);
    }
}
