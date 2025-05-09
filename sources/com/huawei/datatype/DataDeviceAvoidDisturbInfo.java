package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class DataDeviceAvoidDisturbInfo {
    private int deviceAvoidDisturbIndex = 1;
    private int deviceAvoidDisturbSwitch = 0;
    private int deviceAvoidDisturbTimeSwitch = 0;
    private int deviceAvoidDisturbType = 0;
    private int deviceAvoidDisturbStartTime = 2300;
    private int deviceAvoidDisturbEndTime = 700;
    private int deviceAvoidDisturbCycle = 127;

    public int getDeviceAvoidDisturbIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbIndex))).intValue();
    }

    public void setDeviceAvoidDisturbIndex(int i) {
        this.deviceAvoidDisturbIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbSwitch() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbSwitch))).intValue();
    }

    public void setDeviceAvoidDisturbSwitch(int i) {
        this.deviceAvoidDisturbSwitch = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbType() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbType))).intValue();
    }

    public void setDeviceAvoidDisturbType(int i) {
        this.deviceAvoidDisturbType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbStartTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbStartTime))).intValue();
    }

    public void setDeviceAvoidDisturbStartTime(int i) {
        this.deviceAvoidDisturbStartTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbEndTime() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbEndTime))).intValue();
    }

    public void setDeviceAvoidDisturbEndTime(int i) {
        this.deviceAvoidDisturbEndTime = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbCycle() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbCycle))).intValue();
    }

    public void setDeviceAvoidDisturbCycle(int i) {
        this.deviceAvoidDisturbCycle = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getDeviceAvoidDisturbTimeSwitch() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceAvoidDisturbTimeSwitch))).intValue();
    }

    public void setDeviceAvoidDisturbTimeSwitch(int i) {
        this.deviceAvoidDisturbTimeSwitch = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "[勿扰信息列表：index = " + this.deviceAvoidDisturbIndex + ", switch = " + this.deviceAvoidDisturbSwitch + ", time_switch = " + this.deviceAvoidDisturbTimeSwitch + ", type = " + this.deviceAvoidDisturbType + ", start_time = " + this.deviceAvoidDisturbStartTime + ", end_time = " + this.deviceAvoidDisturbEndTime + ", cycle = " + this.deviceAvoidDisturbCycle + "]";
    }
}
