package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public final class zzj implements Parcelable.Creator<DataDeleteRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataDeleteRequest[] newArray(int i) {
        return new DataDeleteRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataDeleteRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        IBinder iBinder = null;
        boolean z = false;
        boolean z2 = false;
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
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 4:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
                    break;
                case 5:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, Session.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 8:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataDeleteRequest(j, j2, arrayList, arrayList2, arrayList3, z, z2, iBinder);
    }
}
