package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public final class zzi implements Parcelable.Creator<SessionStopResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionStopResult[] newArray(int i) {
        return new SessionStopResult[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SessionStopResult createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Status status = null;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 2) {
                status = (Status) SafeParcelReader.createParcelable(parcel, readHeader, Status.CREATOR);
            } else if (fieldId == 3) {
                arrayList = SafeParcelReader.createTypedList(parcel, readHeader, Session.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SessionStopResult(status, arrayList);
    }
}
