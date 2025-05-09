package com.huawei.hwdevice.phoneprocess.mgr.hwrriservicemgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class EcgData implements Parcelable {
    public static final Parcelable.Creator<EcgData> CREATOR = new Parcelable.Creator<EcgData>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.hwrriservicemgr.datatype.EcgData.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: bNf_, reason: merged with bridge method [inline-methods] */
        public EcgData createFromParcel(Parcel parcel) {
            return new EcgData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public EcgData[] newArray(int i) {
            return new EcgData[i];
        }
    };
    private String mEcgData;
    private long mEcgEndTime;
    private long mEcgStartTime;
    private int mHeartRateValue;
    private boolean mIsHeartRateType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public EcgData() {
    }

    private EcgData(Parcel parcel) {
        this.mEcgStartTime = parcel.readLong();
        this.mEcgEndTime = parcel.readLong();
        this.mHeartRateValue = parcel.readInt();
        this.mIsHeartRateType = parcel.readByte() != 0;
        this.mEcgData = parcel.readString();
    }

    public long getEcgStartTime() {
        return this.mEcgStartTime;
    }

    public void setEcgStartTime(long j) {
        this.mEcgStartTime = j;
    }

    public long getEcgEndTime() {
        return this.mEcgEndTime;
    }

    public void setEcgEndTime(long j) {
        this.mEcgEndTime = j;
    }

    public int getHeartRateValue() {
        return this.mHeartRateValue;
    }

    public void setHeartRateValue(int i) {
        this.mHeartRateValue = i;
    }

    public boolean isHeartRateType() {
        return this.mIsHeartRateType;
    }

    public void setHeartRateType(boolean z) {
        this.mIsHeartRateType = z;
    }

    public String getEcgData() {
        return this.mEcgData;
    }

    public void setEcgData(String str) {
        this.mEcgData = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("EcgData{mEcgStartTime=");
        stringBuffer.append(this.mEcgStartTime);
        stringBuffer.append(", mEcgEndTime=").append(this.mEcgEndTime);
        stringBuffer.append(", mHeartRateValue=").append(this.mHeartRateValue);
        stringBuffer.append(", mIsHeartRateType=").append(this.mIsHeartRateType);
        stringBuffer.append(", mEcgData=").append(this.mEcgData);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mHeartRateValue);
        parcel.writeByte(this.mIsHeartRateType ? (byte) 1 : (byte) 0);
    }
}
