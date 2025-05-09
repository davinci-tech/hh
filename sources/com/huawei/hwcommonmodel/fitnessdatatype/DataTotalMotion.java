package com.huawei.hwcommonmodel.fitnessdatatype;

import defpackage.jdy;

/* loaded from: classes5.dex */
public class DataTotalMotion {
    private int calorie;
    private int distance;
    private int height;
    private int motion_type;
    private int sleep_time;
    private int step;

    public int getMotion_type() {
        return ((Integer) jdy.d(Integer.valueOf(this.motion_type))).intValue();
    }

    public void setMotion_type(int i) {
        this.motion_type = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getStep() {
        return ((Integer) jdy.d(Integer.valueOf(this.step))).intValue();
    }

    public void setStep(int i) {
        this.step = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getCalorie() {
        return ((Integer) jdy.d(Integer.valueOf(this.calorie))).intValue();
    }

    public void setCalorie(int i) {
        this.calorie = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDistance() {
        return ((Integer) jdy.d(Integer.valueOf(this.distance))).intValue();
    }

    public void setDistance(int i) {
        this.distance = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getHeight() {
        return ((Integer) jdy.d(Integer.valueOf(this.height))).intValue();
    }

    public void setHeight(int i) {
        this.height = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public void setSleep_time(int i) {
        this.sleep_time = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
