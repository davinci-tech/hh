package com.huawei.health.plan.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class PlanStatistics implements Parcelable {
    public static final Parcelable.Creator<PlanStatistics> CREATOR = new Parcelable.Creator<PlanStatistics>() { // from class: com.huawei.health.plan.model.PlanStatistics.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: arW_, reason: merged with bridge method [inline-methods] */
        public PlanStatistics createFromParcel(Parcel parcel) {
            return new PlanStatistics(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlanStatistics[] newArray(int i) {
            return new PlanStatistics[i];
        }
    };
    private long mTotalCalorie;
    private double mTotalDistance;
    private long mTotalDuration;
    private int mTotalPlanNum;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlanStatistics() {
        this.mTotalCalorie = 0L;
        this.mTotalDuration = 0L;
        this.mTotalPlanNum = 0;
        this.mTotalDistance = 0.0d;
        this.mType = 0;
    }

    protected PlanStatistics(Parcel parcel) {
        this.mTotalCalorie = 0L;
        this.mTotalDuration = 0L;
        this.mTotalPlanNum = 0;
        this.mTotalDistance = 0.0d;
        this.mType = 0;
        this.mTotalCalorie = parcel.readLong();
        this.mTotalDuration = parcel.readLong();
        this.mTotalPlanNum = parcel.readInt();
        this.mTotalDistance = parcel.readDouble();
        this.mType = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTotalCalorie);
        parcel.writeLong(this.mTotalDuration);
        parcel.writeInt(this.mTotalPlanNum);
        parcel.writeInt(this.mType);
        parcel.writeDouble(this.mTotalDistance);
    }

    public long acquireCalorie() {
        return this.mTotalCalorie;
    }

    public void saveCalorie(long j) {
        this.mTotalCalorie = j;
    }

    public long acquireDuration() {
        return this.mTotalDuration;
    }

    public void saveDuration(long j) {
        this.mTotalDuration = j;
    }

    public int acquireTotalPlan() {
        return this.mTotalPlanNum;
    }

    public void saveTotalPlan(int i) {
        this.mTotalPlanNum = i;
    }

    public long acquireType() {
        return this.mType;
    }

    public void saveType(int i) {
        this.mType = i;
    }

    public void setTotalDistance(double d) {
        this.mTotalDistance = d;
    }

    public double getTotalDistance() {
        return this.mTotalDistance;
    }
}
