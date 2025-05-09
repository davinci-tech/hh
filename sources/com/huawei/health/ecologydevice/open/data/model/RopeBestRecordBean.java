package com.huawei.health.ecologydevice.open.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class RopeBestRecordBean implements Parcelable {
    public static final Parcelable.Creator<RopeBestRecordBean> CREATOR = new Parcelable.Creator<RopeBestRecordBean>() { // from class: com.huawei.health.ecologydevice.open.data.model.RopeBestRecordBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: TM_, reason: merged with bridge method [inline-methods] */
        public RopeBestRecordBean createFromParcel(Parcel parcel) {
            return new RopeBestRecordBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RopeBestRecordBean[] newArray(int i) {
            return new RopeBestRecordBean[i];
        }
    };
    private long deviceCode;
    private long endTime;
    private int source;
    private long startTime;
    private int value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected RopeBestRecordBean(Parcel parcel) {
        if (parcel != null) {
            this.startTime = parcel.readLong();
            this.deviceCode = parcel.readLong();
            this.endTime = parcel.readLong();
            this.source = parcel.readInt();
            this.value = parcel.readInt();
        }
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(long j) {
        this.deviceCode = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeLong(this.startTime);
            parcel.writeLong(this.deviceCode);
            parcel.writeLong(this.endTime);
            parcel.writeInt(this.source);
            parcel.writeInt(this.value);
        }
    }

    public String toString() {
        return "RopeBestRecordBean{startTime=" + this.startTime + ", deviceCode=" + this.deviceCode + ", endTime=" + this.endTime + ", source=" + this.source + ", value=" + this.value + '}';
    }
}
