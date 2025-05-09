package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import health.compact.a.HiDateUtil;

/* loaded from: classes.dex */
public class HiTimeInterval implements Parcelable {
    public static final Parcelable.Creator<HiTimeInterval> CREATOR = new Parcelable.Creator<HiTimeInterval>() { // from class: com.huawei.hihealth.HiTimeInterval.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: buY_, reason: merged with bridge method [inline-methods] */
        public HiTimeInterval createFromParcel(Parcel parcel) {
            return new HiTimeInterval(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HiTimeInterval[] newArray(int i) {
            return new HiTimeInterval[i];
        }
    };
    private long endTime;
    private long startTime;
    private int timeZone;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiTimeInterval() {
    }

    public HiTimeInterval(long j, long j2) {
        this.startTime = j;
        this.endTime = j2;
    }

    public HiTimeInterval(int i, long j, long j2) {
        this.timeZone = i;
        this.endTime = j;
        this.startTime = j2;
    }

    protected HiTimeInterval(Parcel parcel) {
        this.startTime = parcel.readLong();
        this.endTime = parcel.readLong();
        this.timeZone = parcel.readInt();
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(int i) {
        this.timeZone = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiTimeInterval{sTm=");
        stringBuffer.append(HiDateUtil.m(this.startTime));
        stringBuffer.append(",eTm=").append(HiDateUtil.m(this.endTime));
        stringBuffer.append(",tmZone=").append(this.timeZone);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeInt(this.timeZone);
    }
}
