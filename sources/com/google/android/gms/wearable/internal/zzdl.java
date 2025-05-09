package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zzdl implements Parcelable.Creator<zzdk> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdk[] newArray(int i) {
        return new zzdk[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdk createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        zzah zzahVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                i = SafeParcelReader.readInt(parcel, readHeader);
            } else if (fieldId == 3) {
                zzahVar = (zzah) SafeParcelReader.createParcelable(parcel, readHeader, zzah.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzdk(i, zzahVar);
    }
}
