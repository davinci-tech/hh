package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzg implements Parcelable.Creator<ActivityTransitionResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ActivityTransitionResult[] newArray(int i) {
        return new ActivityTransitionResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ActivityTransitionResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, ActivityTransitionEvent.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new ActivityTransitionResult(arrayList);
    }
}
