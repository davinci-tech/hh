package com.huawei.hms.base.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class BaseOptionsParcel implements Parcelable {
    public static final Parcelable.Creator<BaseOptionsParcel> CREATOR = new Parcelable.Creator<BaseOptionsParcel>() { // from class: com.huawei.hms.base.common.BaseOptionsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseOptionsParcel createFromParcel(Parcel parcel) {
            return new BaseOptionsParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseOptionsParcel[] newArray(int i) {
            return new BaseOptionsParcel[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseOptionsParcel() {
    }

    protected BaseOptionsParcel(Parcel parcel) {
        new ParcelReader(parcel).finish();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWriter parcelWriter = new ParcelWriter(parcel);
        parcelWriter.finishObjectHeader(parcelWriter.beginObjectHeader());
    }
}
