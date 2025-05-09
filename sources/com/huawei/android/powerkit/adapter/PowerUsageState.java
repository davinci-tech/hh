package com.huawei.android.powerkit.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class PowerUsageState implements Parcelable {
    public static final Parcelable.Creator<PowerUsageState> CREATOR = new Parcelable.Creator<PowerUsageState>() { // from class: com.huawei.android.powerkit.adapter.PowerUsageState.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: fG_, reason: merged with bridge method [inline-methods] */
        public PowerUsageState createFromParcel(Parcel parcel) {
            return new PowerUsageState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PowerUsageState[] newArray(int i) {
            return new PowerUsageState[i];
        }
    };
    private long mBgTime;
    private long mBgTotalPower;
    private long mFgTime;
    private long mFgTotalPower;
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PowerUsageState(String str, long j, long j2, long j3, long j4) {
        this.mName = str;
        this.mFgTime = j;
        this.mBgTime = j2;
        this.mFgTotalPower = j3;
        this.mBgTotalPower = j4;
    }

    public PowerUsageState(Parcel parcel) {
        this.mName = parcel.readString();
        this.mFgTime = parcel.readLong();
        this.mBgTime = parcel.readLong();
        this.mFgTotalPower = parcel.readLong();
        this.mBgTotalPower = parcel.readLong();
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setFgTime(long j) {
        this.mFgTime = j;
    }

    public void setBgTime(long j) {
        this.mBgTime = j;
    }

    public void setFgPower(long j) {
        this.mFgTotalPower = j;
    }

    public void setBgPower(long j) {
        this.mBgTotalPower = j;
    }

    public String getName() {
        return this.mName;
    }

    public long getFgTime() {
        return this.mFgTime;
    }

    public long getBgTime() {
        return this.mBgTime;
    }

    public long getFgPower() {
        return this.mFgTotalPower;
    }

    public long getBgPower() {
        return this.mBgTotalPower;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeLong(this.mFgTime);
        parcel.writeLong(this.mBgTime);
        parcel.writeLong(this.mFgTotalPower);
        parcel.writeLong(this.mBgTotalPower);
    }

    public String toString() {
        return "name: " + this.mName + "\" mFgTime: " + this.mFgTime + "\" mFgTotalPower:" + this.mFgTotalPower + "\" mBgTime: " + this.mBgTime + "\" mBgTotalPower:" + this.mBgTotalPower;
    }
}
