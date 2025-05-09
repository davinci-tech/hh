package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class WorkoutRealTimeInfo {
    private long calorieInfo;
    private long climeInfo;
    private long distanceInfo;
    private int heartRateInfo;
    private float speedInfo;
    private int sportType;
    private long timeInfo;

    public int getSportType() {
        return ((Integer) jdy.d(Integer.valueOf(this.sportType))).intValue();
    }

    public void setSportType(int i) {
        this.sportType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public float getSpeedInfo() {
        return ((Float) jdy.d(Float.valueOf(this.speedInfo))).floatValue();
    }

    public void setSpeedInfo(float f) {
        this.speedInfo = ((Float) jdy.d(Float.valueOf(f))).floatValue();
    }

    public int getHeartRateInfo() {
        return ((Integer) jdy.d(Integer.valueOf(this.heartRateInfo))).intValue();
    }

    public void setHeartRateInfo(int i) {
        this.heartRateInfo = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTimeInfo() {
        return ((Long) jdy.d(Long.valueOf(this.timeInfo))).longValue();
    }

    public void setTimeInfo(int i) {
        this.timeInfo = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getCalorieInfo() {
        return ((Long) jdy.d(Long.valueOf(this.calorieInfo))).longValue();
    }

    public void setCalorieInfo(long j) {
        this.calorieInfo = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long getDistanceInfo() {
        return ((Long) jdy.d(Long.valueOf(this.distanceInfo))).longValue();
    }

    public void setDistanceInfo(long j) {
        this.distanceInfo = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long getClimeInfo() {
        return ((Long) jdy.d(Long.valueOf(this.climeInfo))).longValue();
    }

    public void setClimeInfo(long j) {
        this.climeInfo = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }
}
