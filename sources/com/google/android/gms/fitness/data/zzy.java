package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzy implements Parcelable.Creator<RawBucket> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawBucket[] newArray(int i) {
        return new RawBucket[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawBucket createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        Session session = null;
        ArrayList arrayList = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 3:
                    session = (Session) SafeParcelReader.createParcelable(parcel, readHeader, Session.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, RawDataSet.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new RawBucket(j, j2, session, i, arrayList, i2, z);
    }
}
