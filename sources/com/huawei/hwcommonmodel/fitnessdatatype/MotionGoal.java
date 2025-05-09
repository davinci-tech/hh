package com.huawei.hwcommonmodel.fitnessdatatype;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class MotionGoal {
    public static final int HW_FITNESS_CALORIE_GOAL = 2;
    public static final int HW_FITNESS_DISTANCE_GOAL = 4;
    public static final int HW_FITNESS_DURATION_GOAL = 8;
    public static final int HW_FITNESS_GOAL_DAY = 1;
    public static final int HW_FITNESS_GOAL_WEEK = 2;
    public static final int HW_FITNESS_STEPS_GOAL = 1;
    private int goalType = 1;
    private int motionType = 0;
    private int dataType = 1;
    private int stepGoal = 10000;
    private int calorieGoal = 0;
    private int distanceGoal = 0;
    private int dutationGoal = 0;

    public int getGoalType() {
        return ((Integer) jdy.d(Integer.valueOf(this.goalType))).intValue();
    }

    public void setGoalType(int i) {
        this.goalType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getMotionType() {
        return ((Integer) jdy.d(Integer.valueOf(this.motionType))).intValue();
    }

    public void setDataType(int i) {
        this.dataType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDataType() {
        return ((Integer) jdy.d(Integer.valueOf(this.dataType))).intValue();
    }

    public int getStepGoal() {
        return ((Integer) jdy.d(Integer.valueOf(this.stepGoal))).intValue();
    }

    public void setStepGoal(int i) {
        this.stepGoal = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getCalorieGoal() {
        return ((Integer) jdy.d(Integer.valueOf(this.calorieGoal))).intValue();
    }

    public void setCalorieGoal(int i) {
        this.calorieGoal = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDistanceGoal() {
        return ((Integer) jdy.d(Integer.valueOf(this.distanceGoal))).intValue();
    }

    public void setDistanceGoal(int i) {
        this.distanceGoal = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDutationGoal() {
        return ((Integer) jdy.d(Integer.valueOf(this.dutationGoal))).intValue();
    }

    public void setDutationGoal(int i) {
        this.dutationGoal = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getCurrValue() {
        int i = this.dataType;
        if (i == 1) {
            return this.stepGoal;
        }
        if (i == 2) {
            return this.calorieGoal;
        }
        if (i == 4) {
            return this.distanceGoal;
        }
        if (i == 8) {
            return this.dutationGoal;
        }
        return this.stepGoal;
    }

    public String toString() {
        return "MotionGoal{goalType=" + this.goalType + ", motionType=" + this.motionType + ", dataType=" + this.dataType + ", stepGoal=" + this.stepGoal + ", calorieGoal=" + this.calorieGoal + ", distanceGoal=" + this.distanceGoal + ", dutationGoal=" + this.dutationGoal + '}';
    }
}
