package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class RunningLevelSomeDayData implements Parcelable {
    public static final Parcelable.Creator<RunningLevelSomeDayData> CREATOR = new Parcelable.Creator<RunningLevelSomeDayData>() { // from class: com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelSomeDayData.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: dEN_, reason: merged with bridge method [inline-methods] */
        public RunningLevelSomeDayData createFromParcel(Parcel parcel) {
            return new RunningLevelSomeDayData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RunningLevelSomeDayData[] newArray(int i) {
            return new RunningLevelSomeDayData[i];
        }
    };
    private static final long serialVersionUID = -4070758271476044314L;
    private float mCondition;
    private float mCurrentRunLevel;
    private float mFatigue;
    private float mFitness;
    private float mRealRunLevel;
    private float mTotalPoint;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RunningLevelSomeDayData() {
    }

    protected RunningLevelSomeDayData(Parcel parcel) {
        this.mCurrentRunLevel = parcel.readFloat();
        this.mRealRunLevel = parcel.readFloat();
        this.mTotalPoint = parcel.readFloat();
        this.mFitness = parcel.readFloat();
        this.mFatigue = parcel.readFloat();
        this.mCondition = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mCurrentRunLevel);
        parcel.writeFloat(this.mRealRunLevel);
        parcel.writeFloat(this.mTotalPoint);
        parcel.writeFloat(this.mFitness);
        parcel.writeFloat(this.mFatigue);
        parcel.writeFloat(this.mCondition);
    }

    public float getCurrentRunLevel() {
        return this.mCurrentRunLevel;
    }

    public void setCurrentRunLevel(float f) {
        this.mCurrentRunLevel = f;
    }

    public float getRealRunLevel() {
        return this.mRealRunLevel;
    }

    public void setRealRunLevel(float f) {
        this.mRealRunLevel = f;
    }

    public float getTotalPoint() {
        return this.mTotalPoint;
    }

    public void setTotalPoint(float f) {
        this.mTotalPoint = f;
    }

    public float getFitness() {
        return this.mFitness;
    }

    public void setFitness(float f) {
        this.mFitness = f;
    }

    public float getFatigue() {
        return this.mFatigue;
    }

    public void setFatigue(float f) {
        this.mFatigue = f;
    }

    public float getCondition() {
        return this.mCondition;
    }

    public void setCondition(float f) {
        this.mCondition = f;
    }

    public String toString() {
        return "RunningLevelSomeDayData{currentRunLevel=" + this.mCurrentRunLevel + ", realRunLevel=" + this.mRealRunLevel + ", totalPoint=" + this.mTotalPoint + ", fitness=" + this.mFitness + ", fatigue=" + this.mFatigue + ", condition=" + this.mCondition + '}';
    }
}
