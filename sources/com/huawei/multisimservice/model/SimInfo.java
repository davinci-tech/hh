package com.huawei.multisimservice.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class SimInfo implements Parcelable {
    public static final Parcelable.Creator<SimInfo> CREATOR = new Parcelable.Creator<SimInfo>() { // from class: com.huawei.multisimservice.model.SimInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public SimInfo[] newArray(int i) {
            return new SimInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bZj_, reason: merged with bridge method [inline-methods] */
        public SimInfo createFromParcel(Parcel parcel) {
            return new SimInfo(parcel);
        }
    };
    private boolean mActive;
    private String mICCID;
    private String mIMSI;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SimInfo() {
        this.mIMSI = "";
        this.mICCID = "";
        this.mActive = false;
    }

    public SimInfo(Parcel parcel) {
        this.mIMSI = "";
        this.mICCID = "";
        this.mActive = false;
        this.mIMSI = parcel.readString();
        this.mICCID = parcel.readString();
        this.mActive = parcel.readByte() != 0;
    }

    public SimInfo(String str, String str2, boolean z) {
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
}
