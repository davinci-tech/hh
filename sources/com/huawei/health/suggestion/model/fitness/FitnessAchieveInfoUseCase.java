package com.huawei.health.suggestion.model.fitness;

import java.io.Serializable;
import java.util.Objects;

/* loaded from: classes8.dex */
public class FitnessAchieveInfoUseCase implements Serializable {
    private static final long serialVersionUID = 986711243365945981L;
    private int mSumDays;
    private int mSumExerciseCount;
    private long mSumExerciseTime;
    private long mTimeStamp;
    private int mTodayTime;

    public int getExerciseCount() {
        return this.mSumExerciseCount;
    }

    public void setSumExerciseCount(int i) {
        this.mSumExerciseCount = i;
    }

    public long getSumExerciseTime() {
        return this.mSumExerciseTime;
    }

    public void setSumExerciseTime(long j) {
        this.mSumExerciseTime = j;
    }

    public int getTodayTime() {
        return this.mTodayTime;
    }

    public void setTodayTime(int i) {
        this.mTodayTime = i;
    }

    public int getSumDays() {
        return this.mSumDays;
    }

    public void setSumDays(int i) {
        this.mSumDays = i;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(long j) {
        this.mTimeStamp = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FitnessAchieveInfoUseCase fitnessAchieveInfoUseCase = (FitnessAchieveInfoUseCase) obj;
        return this.mSumExerciseCount == fitnessAchieveInfoUseCase.mSumExerciseCount && this.mSumExerciseTime == fitnessAchieveInfoUseCase.mSumExerciseTime && this.mTodayTime == fitnessAchieveInfoUseCase.mTodayTime && this.mSumDays == fitnessAchieveInfoUseCase.mSumDays;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSumExerciseCount), Long.valueOf(this.mSumExerciseTime), Integer.valueOf(this.mTodayTime), Integer.valueOf(this.mSumDays), Long.valueOf(this.mTimeStamp));
    }

    public String toString() {
        return "FitnessAchieveInfoUseCase{mSumExerciseCount=" + this.mSumExerciseCount + ", mSumExerciseTime=" + this.mSumExerciseTime + ", mTodayTime=" + this.mTodayTime + ", mSumDays=" + this.mSumDays + ", mTimeStamp=" + this.mTimeStamp + '}';
    }
}
