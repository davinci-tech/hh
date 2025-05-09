package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Subscription;

/* loaded from: classes8.dex */
public final class zzbk implements Parcelable.Creator<zzbj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbj[] newArray(int i) {
        return new zzbj[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbj createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Subscription subscription = null;
        boolean z = false;
        IBinder iBinder = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(readHeader);
            if (fieldId == 1) {
                subscription = (Subscription) SafeParcelReader.createParcelable(parcel, readHeader, Subscription.CREATOR);
            } else if (fieldId == 2) {
                z = SafeParcelReader.readBoolean(parcel, readHeader);
            } else if (fieldId == 3) {
                iBinder = SafeParcelReader.readIBinder(parcel, readHeader);
            } else {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzbj(subscription, z, iBinder);
    }
}
