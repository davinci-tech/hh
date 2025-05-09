package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: classes2.dex */
public final class zze implements Parcelable.Creator<zzd> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzd[] newArray(int i) {
        return new zzd[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzd createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        IBinder iBinder = null;
        IntentFilter[] intentFilterArr = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else if (fieldId == 3) {
                intentFilterArr = (IntentFilter[]) SafeParcelReader.createTypedArray(parcel, readHeader, IntentFilter.CREATOR);
            } else if (fieldId == 4) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 5) {
                str2 = SafeParcelReader.createString(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzd(iBinder, intentFilterArr, str, str2);
    }
}
