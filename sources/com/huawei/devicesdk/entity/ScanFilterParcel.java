package com.huawei.devicesdk.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class ScanFilterParcel implements Parcelable {
    public static final Parcelable.Creator<ScanFilterParcel> CREATOR = new Parcelable.Creator<ScanFilterParcel>() { // from class: com.huawei.devicesdk.entity.ScanFilterParcel.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: rW_, reason: merged with bridge method [inline-methods] */
        public ScanFilterParcel createFromParcel(Parcel parcel) {
            ScanFilterParcel scanFilterParcel = new ScanFilterParcel();
            scanFilterParcel.setType(parcel.readInt());
            scanFilterParcel.setMatcher(parcel.readString());
            return scanFilterParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public ScanFilterParcel[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new ScanFilterParcel[i];
        }
    };
    private String mMatcher;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String getMatcher() {
        return this.mMatcher;
    }

    public void setMatcher(String str) {
        this.mMatcher = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeString(this.mMatcher);
    }

    public void readFromParcel(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mMatcher = parcel.readString();
    }
}
