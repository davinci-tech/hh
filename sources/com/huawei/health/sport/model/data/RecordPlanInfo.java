package com.huawei.health.sport.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class RecordPlanInfo implements Parcelable {
    public static final Parcelable.Creator<RecordPlanInfo> CREATOR = new Parcelable.Creator<RecordPlanInfo>() { // from class: com.huawei.health.sport.model.data.RecordPlanInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: awP_, reason: merged with bridge method [inline-methods] */
        public RecordPlanInfo createFromParcel(Parcel parcel) {
            return new RecordPlanInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RecordPlanInfo[] newArray(int i) {
            return new RecordPlanInfo[i];
        }
    };

    @SerializedName("planId")
    private String mPlanId;

    @SerializedName("planTrainData")
    private int mPlanTrainDate;

    @SerializedName("planType")
    private int mPlanType;

    @SerializedName("weekSequence")
    private int mWeekNumber;

    @SerializedName("workoutOrder")
    private int mWorkoutOrder;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecordPlanInfo() {
    }

    public RecordPlanInfo(String str, int i, int i2, int i3) {
        this.mPlanId = str;
        this.mPlanTrainDate = i;
        this.mWorkoutOrder = i2;
        this.mWeekNumber = i3;
    }

    protected RecordPlanInfo(Parcel parcel) {
        if (parcel != null) {
            this.mPlanId = parcel.readString();
            this.mPlanTrainDate = parcel.readInt();
            this.mWorkoutOrder = parcel.readInt();
            this.mWeekNumber = parcel.readInt();
            this.mPlanType = parcel.readInt();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mPlanId);
            parcel.writeInt(this.mPlanTrainDate);
            parcel.writeInt(this.mWorkoutOrder);
            parcel.writeInt(this.mWeekNumber);
            parcel.writeInt(this.mPlanType);
        }
    }

    public String getPlanId() {
        return this.mPlanId;
    }

    public void setPlanId(String str) {
        this.mPlanId = str;
    }

    public int getPlanTrainDate() {
        return this.mPlanTrainDate;
    }

    public void setPlanTrainDate(int i) {
        this.mPlanTrainDate = i;
    }

    public int getWorkoutOrder() {
        return this.mWorkoutOrder;
    }

    public void setWorkoutOrder(int i) {
        this.mWorkoutOrder = i;
    }

    public int getWeekNumber() {
        return this.mWeekNumber;
    }

    public void setWeekNumber(int i) {
        this.mWeekNumber = i;
    }

    public int getPlanType() {
        return this.mPlanType;
    }

    public void setPlanType(int i) {
        this.mPlanType = i;
    }

    public String toString() {
        return "RecordPlanInfo{mPlanId='" + this.mPlanId + "', mPlanTrainDate='" + this.mPlanTrainDate + "', mWorkoutOrder='" + this.mWorkoutOrder + "', mWeekNumber='" + this.mWeekNumber + "', mPlanType='" + this.mPlanType + "'}";
    }
}
