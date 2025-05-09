package com.huawei.basichealthmodel.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class HealthBloodPressureBean implements Parcelable {
    public static final Parcelable.Creator<HealthBloodPressureBean> CREATOR = new Parcelable.Creator<HealthBloodPressureBean>() { // from class: com.huawei.basichealthmodel.ui.bean.HealthBloodPressureBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: la_, reason: merged with bridge method [inline-methods] */
        public HealthBloodPressureBean createFromParcel(Parcel parcel) {
            HealthBloodPressureBean healthBloodPressureBean = new HealthBloodPressureBean();
            if (parcel == null) {
                return healthBloodPressureBean;
            }
            healthBloodPressureBean.mMinTime = parcel.readLong();
            healthBloodPressureBean.mMaxTime = parcel.readLong();
            healthBloodPressureBean.mTime = parcel.readString();
            healthBloodPressureBean.mHour = parcel.readInt();
            healthBloodPressureBean.mMinute = parcel.readInt();
            healthBloodPressureBean.mPressureTime = parcel.readLong();
            healthBloodPressureBean.mDaysOfWeek = parcel.readInt();
            healthBloodPressureBean.mBloodPressureDiastolic = parcel.readDouble();
            healthBloodPressureBean.mBloodPressureSystolic = parcel.readDouble();
            return healthBloodPressureBean;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public HealthBloodPressureBean[] newArray(int i) {
            return new HealthBloodPressureBean[i];
        }
    };

    @SerializedName("bloodPressureDiastolic")
    private double mBloodPressureDiastolic;

    @SerializedName("bloodPressureSystolic")
    private double mBloodPressureSystolic;

    @SerializedName("daysOfWeek")
    private int mDaysOfWeek;

    @SerializedName("hour")
    private int mHour;

    @SerializedName("maxTime")
    private transient long mMaxTime;

    @SerializedName("minTime")
    private transient long mMinTime;

    @SerializedName("minute")
    private int mMinute;

    @SerializedName("pressureTime")
    private long mPressureTime;

    @SerializedName("time")
    private String mTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getMinTime() {
        return this.mMinTime;
    }

    public void setMinTime(long j) {
        this.mMinTime = j;
    }

    public long getMaxTime() {
        return this.mMaxTime;
    }

    public void setMaxTime(long j) {
        this.mMaxTime = j;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public int getHour() {
        return this.mHour;
    }

    public void setHour(int i) {
        this.mHour = i;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public void setMinute(int i) {
        this.mMinute = i;
    }

    public long getPressureTime() {
        return this.mPressureTime;
    }

    public void setPressureTime(long j) {
        this.mPressureTime = j;
    }

    public int getDaysOfWeek() {
        return this.mDaysOfWeek;
    }

    public void setDaysOfWeek(int i) {
        this.mDaysOfWeek = i;
    }

    public double getBloodPressureDiastolic() {
        return this.mBloodPressureDiastolic;
    }

    public void setBloodPressureDiastolic(double d) {
        this.mBloodPressureDiastolic = d;
    }

    public double getBloodPressureSystolic() {
        return this.mBloodPressureSystolic;
    }

    public void setBloodPressureSystolic(double d) {
        this.mBloodPressureSystolic = d;
    }

    public String toString() {
        return "HealthBloodPressureBean{mMinTime=" + this.mMinTime + ", mMaxTime=" + this.mMaxTime + ", mTime='" + this.mTime + "', mPressureTime=" + this.mPressureTime + ", mDaysOfWeek=" + this.mDaysOfWeek + ", mBloodPressureDiastolic=" + this.mBloodPressureDiastolic + ", mBloodPressureSystolic=" + this.mBloodPressureSystolic + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeLong(this.mMinTime);
        parcel.writeLong(this.mMaxTime);
        parcel.writeString(this.mTime);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
        parcel.writeLong(this.mPressureTime);
        parcel.writeInt(this.mDaysOfWeek);
        parcel.writeDouble(this.mBloodPressureDiastolic);
        parcel.writeDouble(this.mBloodPressureSystolic);
    }
}
