package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiEcgMetaData implements Parcelable {
    public static final Parcelable.Creator<HiEcgMetaData> CREATOR = new Parcelable.Creator<HiEcgMetaData>() { // from class: com.huawei.hihealth.data.model.HiEcgMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiEcgMetaData createFromParcel(Parcel parcel) {
            return new HiEcgMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiEcgMetaData[] newArray(int i) {
            return new HiEcgMetaData[i];
        }
    };
    private String detectResult;
    private int heartRate;
    private boolean isNormal;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiEcgMetaData() {
        this.heartRate = 0;
        this.isNormal = true;
    }

    private HiEcgMetaData(Parcel parcel) {
        this.heartRate = 0;
        this.isNormal = true;
        this.heartRate = parcel.readInt();
        this.isNormal = parcel.readByte() != 0;
        this.detectResult = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.heartRate);
        parcel.writeByte(this.isNormal ? (byte) 1 : (byte) 0);
        parcel.writeString(this.detectResult);
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public boolean isNormal() {
        return this.isNormal;
    }

    public void setNormal(boolean z) {
        this.isNormal = z;
    }

    public String getDetectResult() {
        return this.detectResult;
    }

    public void setDetectResult(String str) {
        this.detectResult = str;
    }
}
