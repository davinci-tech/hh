package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzaw implements Parcelable.Creator<SessionReadRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionReadRequest[] newArray(int i) {
        return new SessionReadRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionReadRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        String str2 = null;
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList<String> arrayList3 = null;
        IBinder iBinder = null;
        long j = 0;
        long j2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 2:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
                    break;
                case 6:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 8:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    arrayList3 = SafeParcelReader.createStringList(parcel, readHeader);
                    break;
                case 10:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionReadRequest(str, str2, j, j2, arrayList, arrayList2, z, z2, arrayList3, iBinder);
    }
}
