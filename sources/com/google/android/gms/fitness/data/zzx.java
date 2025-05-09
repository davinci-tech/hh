package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;

/* loaded from: classes8.dex */
public final class zzx implements Parcelable.Creator<Goal.MetricObjective> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.MetricObjective[] newArray(int i) {
        return new Goal.MetricObjective[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.MetricObjective createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        double d = 0.0d;
        double d2 = 0.0d;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                str = SafeParcelReader.createString(parcel, readHeader);
            } else if (fieldId == 2) {
                d = SafeParcelReader.readDouble(parcel, readHeader);
            } else if (fieldId == 3) {
                d2 = SafeParcelReader.readDouble(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new Goal.MetricObjective(str, d, d2);
    }
}
