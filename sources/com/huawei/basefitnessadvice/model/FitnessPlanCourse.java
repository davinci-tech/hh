package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import defpackage.mnt;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class FitnessPlanCourse implements Cloneable {
    private static final double ALPHA = 1.0E-6d;

    @SerializedName("actionInfo")
    private mnt actionInfo;
    private double burnedCalorie;

    @SerializedName("calorie")
    private int calorie;

    @SerializedName("calorieMan")
    private int calorieMan;

    @SerializedName("finish")
    private boolean finish;

    @SerializedName("courseAttr")
    private int gender = 2;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    @SerializedName("name")
    private String name;

    @SerializedName("restTime")
    private int restTime;

    @SerializedName(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID)
    private String workoutId;

    @SerializedName("workoutRealCal")
    private double workoutRealCal;

    @SerializedName("workoutRealCalMan")
    private double workoutRealCalMan;

    public int getCalorieMan() {
        return this.calorieMan;
    }

    public void saveWorkoutRealCalMan(double d) {
        this.workoutRealCalMan = d;
    }

    public double acquireWorkoutRealCalMan() {
        double d = this.workoutRealCalMan;
        return d < 1.0E-6d ? this.calorie : d;
    }

    public void saveGender(int i) {
        this.gender = i;
    }

    public int acquireGender() {
        return this.gender;
    }

    public int acquireCalorie() {
        return this.calorie;
    }

    public void saveCalorie(int i) {
        this.calorie = i;
    }

    public double acquireBurnedCalorie() {
        double d = this.burnedCalorie;
        return d < 1.0E-6d ? this.calorie : d;
    }

    public void saveBurnedCalorie(double d) {
        this.burnedCalorie = d;
    }

    public double acquireWorkoutRealCal() {
        double d = this.workoutRealCal;
        return d < 1.0E-6d ? this.calorie : d;
    }

    public void saveWorkoutRealCal(double d) {
        this.workoutRealCal = d;
    }

    public String acquireName() {
        return this.name;
    }

    public void saveName(String str) {
        this.name = str;
    }

    public boolean acquireFinishStatus() {
        return this.finish;
    }

    public void saveFinishStatus(boolean z) {
        this.finish = z;
    }

    public int acquireWorkoutTime() {
        return this.restTime;
    }

    public void saveWorkoutTime(int i) {
        this.restTime = i;
    }

    public String acquireCourseId() {
        return this.workoutId;
    }

    public void saveCourseId(String str) {
        this.workoutId = str;
    }

    public mnt getActionInfo() {
        return this.actionInfo;
    }

    public void setActionInfo(mnt mntVar) {
        this.actionInfo = mntVar;
    }

    public int acquirePunchFlag() {
        return this.mPunchFlag;
    }

    public void savePunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public String toString() {
        return "FitnessPlanCourse{finish=" + this.finish + ", restTime=" + this.restTime + ", workoutId='" + this.workoutId + "', calorie=" + this.calorie + ", calorieMan=" + this.calorieMan + ", name='" + this.name + "', workoutRealCal=" + this.workoutRealCal + ", workoutRealCalMan=" + this.workoutRealCalMan + ", gender=" + this.gender + ", burnedCalorie=" + this.burnedCalorie + '}';
    }

    protected Object clone() {
        try {
            FitnessPlanCourse fitnessPlanCourse = (FitnessPlanCourse) super.clone();
            fitnessPlanCourse.saveFinishStatus(this.finish);
            fitnessPlanCourse.saveCourseId(this.workoutId);
            fitnessPlanCourse.saveWorkoutTime(this.restTime);
            fitnessPlanCourse.saveName(this.name);
            fitnessPlanCourse.saveCalorie(this.calorie);
            fitnessPlanCourse.saveBurnedCalorie(this.burnedCalorie);
            fitnessPlanCourse.saveWorkoutRealCal(this.workoutRealCal);
            fitnessPlanCourse.saveGender(this.gender);
            fitnessPlanCourse.saveWorkoutRealCalMan(this.workoutRealCalMan);
            return fitnessPlanCourse;
        } catch (CloneNotSupportedException e) {
            LogUtil.e("FitnessPlanCourse clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }
}
