package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class BloodPressurePlanResultBean implements Parcelable {
    public static final Parcelable.Creator<BloodPressurePlanResultBean> CREATOR = new Parcelable.Creator<BloodPressurePlanResultBean>() { // from class: com.huawei.health.healthmodel.bean.BloodPressurePlanResultBean.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZA_, reason: merged with bridge method [inline-methods] */
        public BloodPressurePlanResultBean createFromParcel(Parcel parcel) {
            BloodPressurePlanResultBean bloodPressurePlanResultBean = new BloodPressurePlanResultBean();
            if (parcel == null) {
                return bloodPressurePlanResultBean;
            }
            bloodPressurePlanResultBean.mPlanId = parcel.readInt();
            bloodPressurePlanResultBean.mHour = parcel.readInt();
            bloodPressurePlanResultBean.mMinute = parcel.readInt();
            bloodPressurePlanResultBean.mMinTime = parcel.readLong();
            bloodPressurePlanResultBean.mMaxTime = parcel.readLong();
            bloodPressurePlanResultBean.mPressureTime = parcel.readLong();
            bloodPressurePlanResultBean.mBloodPressureDiastolic = parcel.readDouble();
            bloodPressurePlanResultBean.mBloodPressureSystolic = parcel.readDouble();
            return bloodPressurePlanResultBean;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public BloodPressurePlanResultBean[] newArray(int i) {
            return new BloodPressurePlanResultBean[i];
        }
    };

    @SerializedName("bloodPressureDiastolic")
    private double mBloodPressureDiastolic;

    @SerializedName("bloodPressureSystolic")
    private double mBloodPressureSystolic;

    @SerializedName("hour")
    private int mHour;

    @SerializedName("maxTime")
    private transient long mMaxTime;

    @SerializedName("minTime")
    private transient long mMinTime;

    @SerializedName("minute")
    private int mMinute;

    @SerializedName("planId")
    private int mPlanId;

    @SerializedName("pressureTime")
    private long mPressureTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getPlanId() {
        return this.mPlanId;
    }

    public void setPlanId(int i) {
        this.mPlanId = i;
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

    public long getPressureTime() {
        return this.mPressureTime;
    }

    public void setPressureTime(long j) {
        this.mPressureTime = j;
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
        return "BloodPressurePlanResultBean{mPressureTime=" + this.mPressureTime + ", mHour=" + this.mHour + ", mMinute=" + this.mMinute + ", mBloodPressureDiastolic=" + this.mBloodPressureDiastolic + ", mBloodPressureSystolic=" + this.mBloodPressureSystolic + ", mPlanId=" + this.mPlanId + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mPlanId);
        parcel.writeInt(this.mHour);
        parcel.writeInt(this.mMinute);
        parcel.writeLong(this.mMinTime);
        parcel.writeLong(this.mMaxTime);
        parcel.writeLong(this.mPressureTime);
        parcel.writeDouble(this.mBloodPressureDiastolic);
        parcel.writeDouble(this.mBloodPressureSystolic);
    }
}
