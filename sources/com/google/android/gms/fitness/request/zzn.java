package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzn implements Parcelable.Creator<DataReadRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadRequest[] newArray(int i) {
        return new DataReadRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadRequest createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        ArrayList arrayList4 = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        ArrayList arrayList5 = null;
        ArrayList<Integer> arrayList6 = null;
        ArrayList<Long> arrayList7 = null;
        ArrayList<Long> arrayList8 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
                    break;
                case 2:
                    arrayList2 = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 5:
                    arrayList3 = SafeParcelReader.createTypedList(parcel, readHeader, DataType.CREATOR);
                    break;
                case 6:
                    arrayList4 = SafeParcelReader.createTypedList(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 9:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, readHeader, DataSource.CREATOR);
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 11:
                case 15:
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
                case 12:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 14:
                    iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
                    break;
                case 16:
                    arrayList5 = SafeParcelReader.createTypedList(parcel, readHeader, Device.CREATOR);
                    break;
                case 17:
                    arrayList6 = SafeParcelReader.createIntegerList(parcel, readHeader);
                    break;
                case 18:
                    arrayList7 = SafeParcelReader.createLongList(parcel, readHeader);
                    break;
                case 19:
                    arrayList8 = SafeParcelReader.createLongList(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new DataReadRequest(arrayList, arrayList2, j, j2, arrayList3, arrayList4, i, j3, dataSource, i2, z, z2, iBinder, arrayList5, arrayList6, arrayList7, arrayList8);
    }
}
