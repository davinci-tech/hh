package com.huawei.hms.base.common;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class BaseModelOptionsParcel implements Parcelable {
    public static final Parcelable.Creator<BaseModelOptionsParcel> CREATOR = new Parcelable.Creator<BaseModelOptionsParcel>() { // from class: com.huawei.hms.base.common.BaseModelOptionsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseModelOptionsParcel createFromParcel(Parcel parcel) {
            return new BaseModelOptionsParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseModelOptionsParcel[] newArray(int i) {
            return new BaseModelOptionsParcel[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseModelOptionsParcel() {
    }

    protected BaseModelOptionsParcel(Parcel parcel) {
        new ParcelReader(parcel).finish();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWriter parcelWriter = new ParcelWriter(parcel);
        parcelWriter.finishObjectHeader(parcelWriter.beginObjectHeader());
    }
}
