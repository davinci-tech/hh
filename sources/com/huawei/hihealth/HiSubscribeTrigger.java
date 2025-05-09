package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.type.HiHealthDataType;

/* loaded from: classes.dex */
public class HiSubscribeTrigger implements Parcelable {
    public static final Parcelable.Creator<HiSubscribeTrigger> CREATOR = new Parcelable.Creator<HiSubscribeTrigger>() { // from class: com.huawei.hihealth.HiSubscribeTrigger.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buV_, reason: merged with bridge method [inline-methods] */
        public HiSubscribeTrigger createFromParcel(Parcel parcel) {
            return new HiSubscribeTrigger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public HiSubscribeTrigger[] newArray(int i) {
            return new HiSubscribeTrigger[i];
        }
    };
    private HiHealthDataType.ChangeType change;
    private HiHealthClient client;
    private int compareType;
    private HiHealthData data;
    private double increment;
    private int intervalType;
    private long millionSecond;
    private Object threshold;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiSubscribeTrigger() {
    }

    protected HiSubscribeTrigger(Parcel parcel) {
        this.millionSecond = parcel.readLong();
        this.intervalType = parcel.readInt();
        this.compareType = parcel.readInt();
        this.increment = parcel.readDouble();
        this.client = (HiHealthClient) parcel.readParcelable(HiHealthClient.class.getClassLoader());
        this.data = (HiHealthData) parcel.readParcelable(HiHealthData.class.getClassLoader());
    }

    public void setInterval(long j) {
        this.millionSecond = j;
    }

    public void setExInterval(int i) {
        this.intervalType = i;
    }

    public void setThreshold(Object obj, int i) {
        this.threshold = obj;
        this.compareType = i;
    }

    public void setIncrement(double d) {
        this.increment = d;
    }

    public boolean check(int i, HiHealthClient hiHealthClient, HiHealthDataType.ChangeType changeType, HiHealthData hiHealthData) {
        this.compareType = i;
        this.client = hiHealthClient;
        this.change = changeType;
        this.data = hiHealthData;
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.millionSecond);
        parcel.writeInt(this.intervalType);
        parcel.writeInt(this.compareType);
        parcel.writeDouble(this.increment);
        parcel.writeParcelable(this.client, i);
        parcel.writeParcelable(this.data, i);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiSubscribeTrigger{millionSecond=");
        stringBuffer.append(this.millionSecond);
        stringBuffer.append(", intervalType=").append(this.intervalType);
        stringBuffer.append(", threshold=").append(this.threshold);
        stringBuffer.append(", compareType=").append(this.compareType);
        stringBuffer.append(", increment=").append(this.increment);
        stringBuffer.append(", client=").append(this.client);
        stringBuffer.append(", change=").append(this.change);
        stringBuffer.append(", data=").append(this.data);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
