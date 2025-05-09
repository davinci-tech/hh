package com.huawei.wearengine.device;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class ScanFilterParcel implements Parcelable {
    public static final Parcelable.Creator<ScanFilterParcel> CREATOR = new Parcelable.Creator<ScanFilterParcel>() { // from class: com.huawei.wearengine.device.ScanFilterParcel.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdk_, reason: merged with bridge method [inline-methods] */
        public ScanFilterParcel createFromParcel(Parcel parcel) {
            ScanFilterParcel scanFilterParcel = new ScanFilterParcel();
            if (parcel == null) {
                return scanFilterParcel;
            }
            scanFilterParcel.setType(parcel.readInt());
            scanFilterParcel.setMatcher(parcel.readString());
            return scanFilterParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ScanFilterParcel[] newArray(int i) {
            return (i > 65535 || i < 0) ? new ScanFilterParcel[0] : new ScanFilterParcel[i];
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
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mMatcher);
        parcel.writeInt(this.mType);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mType = parcel.readInt();
        this.mMatcher = parcel.readString();
    }
}
