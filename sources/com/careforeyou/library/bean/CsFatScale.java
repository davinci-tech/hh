package com.careforeyou.library.bean;

import java.util.Date;

/* loaded from: classes2.dex */
public class CsFatScale {
    private double age;
    private double axunge;
    private double bmr;
    private double bone;
    private int deviceType;
    private float impedance;
    private boolean isHistoryData;
    private byte lockFlag;
    private double muscle;
    public double ori_visceral_fat;
    private String remark;
    private int roleId;
    private byte scaleproperty;
    private String scaleweight;
    private double visceral_fat;
    private double water;
    public Date weighingDate;
    private double weight;
    private int heartRateMeasuringType = -1;
    private int impedanceMeasuringType = -1;

    public int getHeartRateMeasuringType() {
        return this.heartRateMeasuringType;
    }

    public void setHeartRateMeasuringType(int i) {
        this.heartRateMeasuringType = i;
    }

    public int getImpedanceMeasuringType() {
        return this.impedanceMeasuringType;
    }

    public void setImpedanceMeasuringType(int i) {
        this.impedanceMeasuringType = i;
    }

    public String getScaleWeightC() {
        return this.scaleweight;
    }

    public void setScaleWeightC(String str) {
        this.scaleweight = str;
    }

    public byte getScaleProperty() {
        return this.scaleproperty;
    }

    public void setScaleProperty(byte b) {
        this.scaleproperty = b;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int i) {
        this.roleId = i;
    }

    public boolean isHistoryData() {
        return this.isHistoryData;
    }

    public void setHistoryData(boolean z) {
        this.isHistoryData = z;
    }

    public byte getLockFlag() {
        return this.lockFlag;
    }

    public void setLockFlag(byte b) {
        this.lockFlag = b;
    }

    public float getImpedance() {
        return this.impedance;
    }

    public void setImpedance(float f) {
        this.impedance = f;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String str) {
        this.remark = str;
    }

    public int getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(int i) {
        this.deviceType = i;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getAxunge() {
        return this.axunge;
    }

    public double getWater() {
        return this.water;
    }

    public double getMuscle() {
        return this.muscle;
    }

    public double getBmrC() {
        return this.bmr;
    }

    public double getVisceral_fat() {
        return this.visceral_fat;
    }

    public double getBone() {
        return this.bone;
    }

    public double getAge() {
        return this.age;
    }

    public void setWeight(double d) {
        this.weight = d / 10.0d;
    }

    public void setAxunge(double d) {
        double d2 = 0.0d;
        if (d >= 65535.0d) {
            this.axunge = 0.0d;
            return;
        }
        double d3 = d / 10.0d;
        if (d3 >= 5.0d && d3 <= 45.0d) {
            d2 = d3;
        }
        this.axunge = d2;
    }

    public void setWater(double d) {
        double d2 = d / 10.0d;
        if (d2 >= 100.0d || d2 < 20.0d || d2 > 85.0d) {
            d2 = 0.0d;
        }
        this.water = d2;
    }

    public void setMuscle(double d) {
        double d2 = d / 10.0d;
        if (d2 >= 100.0d) {
            d2 = 0.0d;
        }
        this.muscle = d2;
    }

    public void setBmrC(double d) {
        if (d >= 65535.0d) {
            this.bmr = 0.0d;
            return;
        }
        if (d >= 5000.0d) {
            d /= 10.0d;
        }
        this.bmr = d;
    }

    public void setVisceral_fat(double d) {
        this.ori_visceral_fat = d;
        double d2 = d / 10.0d;
        if (d2 >= 100.0d || d2 > 59.0d) {
            d2 = 0.0d;
        }
        this.visceral_fat = d2;
    }

    public void setBone(double d) {
        double d2 = 0.0d;
        if (d >= 65535.0d) {
            this.bone = 0.0d;
            return;
        }
        double d3 = d / 10.0d;
        if (d3 >= 1.0d) {
            d2 = 4.0d;
            if (d3 <= 4.0d) {
                d2 = d3;
            }
        }
        this.bone = d2;
    }

    public void setAge(double d) {
        this.age = d;
    }

    public void cleanFatInfo() {
        this.axunge = 0.0d;
        this.water = 0.0d;
        this.muscle = 0.0d;
        this.bmr = 0.0d;
        this.visceral_fat = 0.0d;
        this.bone = 0.0d;
    }

    public String toString() {
        return "CsFatScale{weight=" + this.weight + ", axunge=" + this.axunge + ", water=" + this.water + ", muscle=" + this.muscle + ", bmr=" + this.bmr + ", visceral_fat=" + this.visceral_fat + ", bone=" + this.bone + ", roleId=" + this.roleId + ", weighingDate=" + this.weighingDate + ", scaleweight='" + this.scaleweight + "', scaleproperty=" + ((int) this.scaleproperty) + ", heartRateMeasuringType=" + this.heartRateMeasuringType + ", impedanceMeasuringType=" + this.impedanceMeasuringType + ", ori_visceral_fat=" + this.ori_visceral_fat + ", isHistoryData=" + this.isHistoryData + ", lockFlag=" + ((int) this.lockFlag) + ", impedance=" + this.impedance + ", remark='" + this.remark + "', deviceType=" + this.deviceType + ", age=" + this.age + '}';
    }
}
