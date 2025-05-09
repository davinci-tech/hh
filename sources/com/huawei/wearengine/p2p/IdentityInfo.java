package com.huawei.wearengine.p2p;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes7.dex */
public class IdentityInfo implements Parcelable {
    public static final Parcelable.Creator<IdentityInfo> CREATOR = new Parcelable.Creator<IdentityInfo>() { // from class: com.huawei.wearengine.p2p.IdentityInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: fef_, reason: merged with bridge method [inline-methods] */
        public IdentityInfo createFromParcel(Parcel parcel) {
            IdentityInfo identityInfo = new IdentityInfo();
            if (parcel != null) {
                identityInfo.setPackageName(parcel.readString());
                identityInfo.setFingerPrint(parcel.readString());
            }
            return identityInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public IdentityInfo[] newArray(int i) {
            return (i > 65535 || i < 0) ? new IdentityInfo[0] : new IdentityInfo[i];
        }
    };
    private String mFingerPrint;
    private String mPackageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IdentityInfo() {
        this(null, null);
    }

    public IdentityInfo(String str, String str2) {
        this.mPackageName = str;
        this.mFingerPrint = str2;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }

    public String getFingerPrint() {
        return this.mFingerPrint;
    }

    public void setFingerPrint(String str) {
        this.mFingerPrint = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mFingerPrint);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mPackageName = parcel.readString();
        this.mFingerPrint = parcel.readString();
    }
}
