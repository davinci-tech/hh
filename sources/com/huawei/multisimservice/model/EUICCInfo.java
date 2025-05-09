package com.huawei.multisimservice.model;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class EUICCInfo implements Parcelable {
    public static final Parcelable.Creator<EUICCInfo> CREATOR = new Parcelable.Creator<EUICCInfo>() { // from class: com.huawei.multisimservice.model.EUICCInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bZd_, reason: merged with bridge method [inline-methods] */
        public EUICCInfo createFromParcel(Parcel parcel) {
            return new EUICCInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public EUICCInfo[] newArray(int i) {
            return new EUICCInfo[i];
        }
    };
    private boolean mActive;
    private String mICCID;
    private String mIMSI;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EUICCInfo() {
        this.mIMSI = null;
        this.mICCID = null;
        this.mActive = false;
    }

    public EUICCInfo(Parcel parcel) {
        this.mIMSI = null;
        this.mICCID = null;
        this.mActive = false;
        this.mIMSI = parcel.readString();
        this.mICCID = parcel.readString();
        this.mActive = parcel.readByte() != 0;
    }

    public EUICCInfo(String str, String str2, boolean z) {
        this.mIMSI = str;
        this.mICCID = str2;
        this.mActive = z;
    }

    public String getIMSI() {
        return this.mIMSI;
    }

    public void setIMSI(String str) {
        this.mIMSI = str;
    }

    public String getICCID() {
        return this.mICCID;
    }

    public void setICCID(String str) {
        this.mICCID = str;
    }

    public boolean isActive() {
        return this.mActive;
    }

    public void setActive(boolean z) {
        this.mActive = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIMSI);
        parcel.writeString(this.mICCID);
        parcel.writeByte(this.mActive ? (byte) 1 : (byte) 0);
    }

    public String toString() {
        return "SimInfo [im=" + CommonUtil.l(this.mIMSI) + ", mICC=" + CommonUtil.l(this.mICCID) + ", mActive=" + this.mActive + "]";
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public EUICCInfo m676clone() throws CloneNotSupportedException {
        return (EUICCInfo) super.clone();
    }
}
