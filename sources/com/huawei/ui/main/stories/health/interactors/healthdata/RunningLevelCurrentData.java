package com.huawei.ui.main.stories.health.interactors.healthdata;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class RunningLevelCurrentData implements Parcelable {
    public static final Parcelable.Creator<RunningLevelCurrentData> CREATOR = new Parcelable.Creator<RunningLevelCurrentData>() { // from class: com.huawei.ui.main.stories.health.interactors.healthdata.RunningLevelCurrentData.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: dEM_, reason: merged with bridge method [inline-methods] */
        public RunningLevelCurrentData createFromParcel(Parcel parcel) {
            return new RunningLevelCurrentData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public RunningLevelCurrentData[] newArray(int i) {
            return new RunningLevelCurrentData[i];
        }
    };
    private static final long serialVersionUID = -8379825613457414708L;
    private float mAnaerobicUpResult;
    private float mCurrentRunLevel;
    private float mEasyRunUpResult;
    private int mHalfMarathon;
    private float mLacticAcidUpResult;
    private float mLastCondition;
    private float mLastCurrentRunLevel;
    private float mLastFatigue;
    private float mLastFitness;
    private float mLastRanking;
    private int mLastRecordDay;
    private float mLastTotalPoint;
    private int mMarathon;
    private float mMarathonUpResult;
    private float mRanking;
    private float mTakeOxygenLowResult;
    private float mTakeOxygenUpResult;
    private int mTimeForFiveKm;
    private int mTimeForOneKm;
    private int mTimeForTenKm;
    private int mTimeForThreeKm;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RunningLevelCurrentData() {
    }

    protected RunningLevelCurrentData(Parcel parcel) {
        this.mCurrentRunLevel = parcel.readFloat();
        this.mEasyRunUpResult = parcel.readFloat();
        this.mMarathonUpResult = parcel.readFloat();
        this.mLacticAcidUpResult = parcel.readFloat();
        this.mAnaerobicUpResult = parcel.readFloat();
        this.mTakeOxygenUpResult = parcel.readFloat();
        this.mTakeOxygenLowResult = parcel.readFloat();
        this.mTimeForOneKm = parcel.readInt();
        this.mTimeForThreeKm = parcel.readInt();
        this.mTimeForFiveKm = parcel.readInt();
        this.mTimeForTenKm = parcel.readInt();
        this.mHalfMarathon = parcel.readInt();
        this.mMarathon = parcel.readInt();
        this.mRanking = parcel.readFloat();
        this.mLastRecordDay = parcel.readInt();
        this.mLastTotalPoint = parcel.readFloat();
        this.mLastCurrentRunLevel = parcel.readFloat();
        this.mLastRanking = parcel.readFloat();
        this.mLastFitness = parcel.readFloat();
        this.mLastFatigue = parcel.readFloat();
        this.mLastCondition = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.mCurrentRunLevel);
        parcel.writeFloat(this.mEasyRunUpResult);
        parcel.writeFloat(this.mMarathonUpResult);
        parcel.writeFloat(this.mLacticAcidUpResult);
        parcel.writeFloat(this.mAnaerobicUpResult);
        parcel.writeFloat(this.mTakeOxygenUpResult);
        parcel.writeFloat(this.mTakeOxygenLowResult);
        parcel.writeInt(this.mTimeForOneKm);
        parcel.writeInt(this.mTimeForThreeKm);
        parcel.writeInt(this.mTimeForFiveKm);
        parcel.writeInt(this.mTimeForTenKm);
        parcel.writeInt(this.mHalfMarathon);
        parcel.writeInt(this.mMarathon);
        parcel.writeFloat(this.mRanking);
        parcel.writeInt(this.mLastRecordDay);
        parcel.writeFloat(this.mLastTotalPoint);
        parcel.writeFloat(this.mLastCurrentRunLevel);
        parcel.writeFloat(this.mLastRanking);
        parcel.writeFloat(this.mLastFitness);
        parcel.writeFloat(this.mLastFatigue);
        parcel.writeFloat(this.mLastCondition);
    }

    public float getCurrentRunLevel() {
        return this.mCurrentRunLevel;
    }

    public void setCurrentRunLevel(float f) {
        this.mCurrentRunLevel = f;
    }

    public float getEasyRunUpResult() {
        return this.mEasyRunUpResult;
    }

    public void setEasyRunUpResult(float f) {
        this.mEasyRunUpResult = f;
    }

    public float getMarathonUpResult() {
        return this.mMarathonUpResult;
    }

    public void setMarathonUpResult(float f) {
        this.mMarathonUpResult = f;
    }

    public float getLacticAcidUpResult() {
        return this.mLacticAcidUpResult;
    }

    public void setLacticAcidUpResult(float f) {
        this.mLacticAcidUpResult = f;
    }

    public float getAnaerobicUpResult() {
        return this.mAnaerobicUpResult;
    }

    public void setAnaerobicUpResult(float f) {
        this.mAnaerobicUpResult = f;
    }

    public float getTakeOxygenUpResult() {
        return this.mTakeOxygenUpResult;
    }

    public void setTakeOxygenUpResult(float f) {
        this.mTakeOxygenUpResult = f;
    }

    public float getTakeOxygenLowResult() {
        return this.mTakeOxygenLowResult;
    }

    public void setTakeOxygenLowResult(float f) {
        this.mTakeOxygenLowResult = f;
    }

    public int getTimeForOneKm() {
        return this.mTimeForOneKm;
    }

    public void setTimeForOneKm(int i) {
        this.mTimeForOneKm = i;
    }

    public int getTimeForThreeKm() {
        return this.mTimeForThreeKm;
    }

    public void setTimeForThreeKm(int i) {
        this.mTimeForThreeKm = i;
    }

    public int getTimeForFiveKm() {
        return this.mTimeForFiveKm;
    }

    public void setTimeForFiveKm(int i) {
        this.mTimeForFiveKm = i;
    }

    public int getTimeForTenKm() {
        return this.mTimeForTenKm;
    }

    public void setTimeForTenKm(int i) {
        this.mTimeForTenKm = i;
    }

    public int getHalfMarathon() {
        return this.mHalfMarathon;
    }

    public void setHalfMarathon(int i) {
        this.mHalfMarathon = i;
    }

    public int getMarathon() {
        return this.mMarathon;
    }

    public void setMarathon(int i) {
        this.mMarathon = i;
    }

    public float getRanking() {
        return this.mRanking;
    }

    public void setRanking(float f) {
        this.mRanking = f;
    }

    public int getLastRecordDay() {
        return this.mLastRecordDay;
    }

    public void setLastRecordDay(int i) {
        this.mLastRecordDay = i;
    }

    public float getLastTotalPoint() {
        return this.mLastTotalPoint;
    }

    public void setLastTotalPoint(float f) {
        this.mLastTotalPoint = f;
    }

    public float getLastCurrentRunLevel() {
        return this.mLastCurrentRunLevel;
    }

    public void setLastCurrentRunLevel(float f) {
        this.mLastCurrentRunLevel = f;
    }

    public float getLastRanking() {
        return this.mLastRanking;
    }

    public void setLastRanking(float f) {
        this.mLastRanking = f;
    }

    public float getLastFitness() {
        return this.mLastFitness;
    }

    public void setLastFitness(float f) {
        this.mLastFitness = f;
    }

    public float getLastFatigue() {
        return this.mLastFatigue;
    }

    public void setLastFatigue(float f) {
        this.mLastFatigue = f;
    }

    public float getLastCondition() {
        return this.mLastCondition;
    }

    public void setLastCondition(float f) {
        this.mLastCondition = f;
    }

    public String toString() {
        return "RunningLevelCurrentData{currentRunLevel=" + this.mCurrentRunLevel + ", easyRunUpResult=" + this.mEasyRunUpResult + ", marathonUpResult=" + this.mMarathonUpResult + ", lacticAcidUpResult=" + this.mLacticAcidUpResult + ", anaerobicUpResult=" + this.mAnaerobicUpResult + ", takeOxygenUpResult=" + this.mTakeOxygenUpResult + ", takeOxygenLowResult=" + this.mTakeOxygenLowResult + ", timeForOneKm=" + this.mTimeForOneKm + ", timeForThreeKm=" + this.mTimeForThreeKm + ", timeForFiveKm=" + this.mTimeForFiveKm + ", timeForTenKm=" + this.mTimeForTenKm + ", halfMarathon=" + this.mHalfMarathon + ", marathon=" + this.mMarathon + ", ranking=" + this.mRanking + ", lastRecordDay= " + this.mLastRecordDay + ", lastTotalPoint=" + this.mLastTotalPoint + ", LastCurrentRunLevel=" + this.mLastCurrentRunLevel + ", lastRanking=" + this.mLastRanking + ", lastFitness=" + this.mLastFitness + ", LastFatigue=" + this.mLastFatigue + ", lastCondition=" + this.mLastCondition + '}';
    }
}
