package com.huawei.basefitnessadvice.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class PlanWorkout implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    private String mCourseId;
    private int mCourseType;

    @SerializedName("dayInfo")
    private DayInfo mDayInfo;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("extendParams")
    private String mExtendParams;
    private boolean mIsDayClockIn;

    @SerializedName("name")
    private String mName;

    @SerializedName("version")
    private String mVersion;

    @SerializedName("weekInfo")
    private WeekInfo mWeekInfo;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String mWorkoutId;

    public boolean isDayClockIn() {
        return this.mIsDayClockIn;
    }

    public void setIsDayClockIn(boolean z) {
        this.mIsDayClockIn = z;
    }

    public String getCourseId() {
        return this.mCourseId;
    }

    public void setCourseId(String str) {
        this.mCourseId = str;
    }

    public int getCourseType() {
        return this.mCourseType;
    }

    public void setCourseType(int i) {
        this.mCourseType = i;
    }

    public String popName() {
        return this.mName;
    }

    public void putName(String str) {
        this.mName = str;
    }

    public String popDescription() {
        return this.mDescription;
    }

    public void putDescription(String str) {
        this.mDescription = str;
    }

    public String popWorkoutId() {
        return this.mWorkoutId;
    }

    public void putWorkoutId(String str) {
        this.mWorkoutId = str;
    }

    public WeekInfo popWeekInfo() {
        return this.mWeekInfo;
    }

    public final void putWeekInfo(WeekInfo weekInfo) {
        this.mWeekInfo = weekInfo;
    }

    public DayInfo popDayInfo() {
        return this.mDayInfo;
    }

    public final void putDayInfo(DayInfo dayInfo) {
        this.mDayInfo = dayInfo;
    }

    public String popExtendParams() {
        return this.mExtendParams;
    }

    public void putExtendParams(String str) {
        this.mExtendParams = str;
    }

    public String popVersion() {
        return this.mVersion;
    }

    public void putVersion(String str) {
        this.mVersion = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public PlanWorkout copy() {
        PlanWorkout planWorkout = new PlanWorkout();
        planWorkout.putDescription(this.mDescription);
        planWorkout.putName(this.mName);
        planWorkout.putVersion(this.mVersion);
        planWorkout.putWorkoutId(this.mWorkoutId);
        planWorkout.putDayInfo(this.mDayInfo.copy());
        planWorkout.putWeekInfo(this.mWeekInfo);
        return planWorkout;
    }

    public Object clone() {
        try {
            if (!(super.clone() instanceof PlanWorkout)) {
                return new PlanWorkout();
            }
            PlanWorkout planWorkout = (PlanWorkout) super.clone();
            DayInfo dayInfo = this.mDayInfo;
            if (dayInfo != null && (dayInfo.clone() instanceof DayInfo)) {
                planWorkout.putDayInfo((DayInfo) this.mDayInfo.clone());
            }
            WeekInfo weekInfo = this.mWeekInfo;
            if (weekInfo != null && (weekInfo.clone() instanceof WeekInfo)) {
                planWorkout.putWeekInfo((WeekInfo) this.mWeekInfo.clone());
            }
            return planWorkout;
        } catch (CloneNotSupportedException e) {
            LogUtil.e("PlanWorkout clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }
}
