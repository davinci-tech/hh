package com.huawei.basefitnessadvice.model;

/* loaded from: classes8.dex */
public class Summary {
    private int mBestPace;
    private float mCalorie;
    private float mDistance;
    private int mDuration;
    private long mEndTime;
    private long mExerciseTime;
    private int mFinishRate;
    private int mMaxMet;
    private String mPlanId;
    private String mSportId;
    private long mStartTime;
    private int mTargetDistance;
    private int mTrainingLoadPeak;
    private String mWorkoutDate;
    private String mWorkoutId;
    private String mWorkoutName;

    public String acquirePlanId() {
        return this.mPlanId;
    }

    public void setPlanId(String str) {
        this.mPlanId = str;
    }

    public String acquireWorkoutId() {
        return this.mWorkoutId;
    }

    public void setWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public String acquireWorkoutName() {
        return this.mWorkoutName;
    }

    public void setWorkoutName(String str) {
        this.mWorkoutName = str;
    }

    public String acquireWorkoutDate() {
        return this.mWorkoutDate;
    }

    public void setWorkoutDate(String str) {
        this.mWorkoutDate = str;
    }

    public long acquireExerciseTime() {
        return this.mExerciseTime;
    }

    public void setExerciseTime(long j) {
        this.mExerciseTime = j;
    }

    public long acquireEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public long acquiretStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public float acquireCalorie() {
        return this.mCalorie;
    }

    public void setCalorie(float f) {
        this.mCalorie = f;
    }

    public int acquireDuring() {
        return this.mDuration;
    }

    public void setDuring(int i) {
        this.mDuration = i;
    }

    public int acquireTargetDistance() {
        return this.mTargetDistance;
    }

    public void setTargetDistance(int i) {
        this.mTargetDistance = i;
    }

    public float acquireDistance() {
        return this.mDistance;
    }

    public void setDistance(float f) {
        this.mDistance = f;
    }

    public int acquireBestPace() {
        return this.mBestPace;
    }

    public void setBestPace(int i) {
        this.mBestPace = i;
    }

    public int acuqireTrainingLoadPeak() {
        return this.mTrainingLoadPeak;
    }

    public void setTrainingLoadPeak(int i) {
        this.mTrainingLoadPeak = i;
    }

    public int acquireMaxMet() {
        return this.mMaxMet;
    }

    public void setMaxMet(int i) {
        this.mMaxMet = i;
    }

    public String acquireSportId() {
        return this.mSportId;
    }

    public void setSportId(String str) {
        this.mSportId = str;
    }

    public int acuqireFinishRate() {
        return this.mFinishRate;
    }

    public void setFinishRate(int i) {
        this.mFinishRate = i;
    }

    public String toString() {
        return "Summary{mPlanId='" + this.mPlanId + "', mWorkoutId='" + this.mWorkoutId + "', mWorkoutName='" + this.mWorkoutName + "', mWorkoutDate='" + this.mWorkoutDate + "', mExerciseTime=" + this.mExerciseTime + ", mCalorie=" + this.mCalorie + ", mDuration=" + this.mDuration + ", mTargetDistance=" + this.mTargetDistance + ", mDistance=" + this.mDistance + ", mBestPace=" + this.mBestPace + ", mFinishRate=" + this.mFinishRate + ", mTrainingLoadPeak=" + this.mTrainingLoadPeak + ", mMaxMet=" + this.mMaxMet + ", mSportId='" + this.mSportId + "', mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + '}';
    }
}
