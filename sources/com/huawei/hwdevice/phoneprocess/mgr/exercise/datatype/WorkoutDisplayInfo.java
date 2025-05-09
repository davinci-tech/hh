package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class WorkoutDisplayInfo {
    private boolean isFreeMotion = false;
    private int sportDataSource = 0;
    private int chiefSportDataType = 0;
    private boolean isTrackPoint = true;
    private int workoutType = 258;

    public void setFreeMotion(boolean z) {
        this.isFreeMotion = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean getFreeMotion() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.isFreeMotion))).booleanValue();
    }

    public void setSportDataSource(int i) {
        this.sportDataSource = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getSportDataSource() {
        return ((Integer) jdy.d(Integer.valueOf(this.sportDataSource))).intValue();
    }

    public void setChiefSportDataType(int i) {
        this.chiefSportDataType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getChiefSportDataType() {
        return ((Integer) jdy.d(Integer.valueOf(this.chiefSportDataType))).intValue();
    }

    public void setHasTrackPoint(boolean z) {
        this.isTrackPoint = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean isHasTrackPoint() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.isTrackPoint))).booleanValue();
    }

    public void setWorkoutType(int i) {
        this.workoutType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getWorkoutType() {
        return ((Integer) jdy.d(Integer.valueOf(this.workoutType))).intValue();
    }
}
