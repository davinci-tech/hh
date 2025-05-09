package com.huawei.unitedevice.p2p;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* loaded from: classes7.dex */
public class IdentityInfo implements Parcelable {
    public static final Parcelable.Creator<IdentityInfo> CREATOR = new Parcelable.Creator<IdentityInfo>() { // from class: com.huawei.unitedevice.p2p.IdentityInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ekg_, reason: merged with bridge method [inline-methods] */
        public IdentityInfo createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            IdentityInfo identityInfo = new IdentityInfo(null, null);
            identityInfo.setPackageName(parcel.readString());
            identityInfo.setFingerPrint(parcel.readString());
            return identityInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public IdentityInfo[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new IdentityInfo[i];
        }
    };
    private String mFingerPrint;
    private String mPackageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            IdentityInfo identityInfo = obj instanceof IdentityInfo ? (IdentityInfo) obj : null;
            String str = this.mPackageName;
            if (str != null && this.mFingerPrint != null) {
                return str.equals(identityInfo.mPackageName) && this.mFingerPrint.equals(identityInfo.mFingerPrint);
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mPackageName, this.mFingerPrint);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackageName);
        parcel.writeString(this.mFingerPrint);
    }

    public void readFromParcel(Parcel parcel) {
        this.mPackageName = parcel.readString();
        this.mFingerPrint = parcel.readString();
    }
}
