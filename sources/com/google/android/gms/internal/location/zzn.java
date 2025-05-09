package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

/* loaded from: classes8.dex */
public final class zzn implements Parcelable.Creator<zzm> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzm[] newArray(int i) {
        return new zzm[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzm createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        com.google.android.gms.location.zzj zzjVar = zzm.zzce;
        List<ClientIdentity> list = zzm.zzcd;
        String str = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                zzjVar = (com.google.android.gms.location.zzj) SafeParcelReader.createParcelable(parcel, readHeader, com.google.android.gms.location.zzj.CREATOR);
            } else if (fieldId == 2) {
                list = SafeParcelReader.createTypedList(parcel, readHeader, ClientIdentity.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                str = SafeParcelReader.createString(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzm(zzjVar, list, str);
    }
}
