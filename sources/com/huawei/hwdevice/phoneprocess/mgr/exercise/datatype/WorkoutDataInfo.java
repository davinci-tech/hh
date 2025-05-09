package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.ffr;
import java.util.LinkedHashMap;

/* loaded from: classes5.dex */
public class WorkoutDataInfo {
    private static final int DEFAULT_BUNDLE_VALUE = -1;

    @SerializedName("data6")
    private int mDataAttitude;

    @SerializedName("data8")
    private int mDataCalories;

    @SerializedName("data9")
    private int mDataFrequency;

    @SerializedName("data1")
    private int mDataHeartRate;

    @SerializedName("data7")
    private int mDataRunPosture;

    @SerializedName("data2")
    private int mDataSpeed;

    @SerializedName("data3")
    private int mDataStepRate;

    @SerializedName("data5")
    private int mDataSwimRate;

    @SerializedName("data4")
    private int mDataSwolf;

    @SerializedName("data11")
    private int mExtendedFieldEleven;

    @SerializedName("data15")
    private int mExtendedFieldFifteen;

    @SerializedName("data14")
    private int mExtendedFieldFourteen;

    @SerializedName("data16")
    private int mExtendedFieldSixteen;

    @SerializedName("data13")
    private int mExtendedFieldThirteen;

    @SerializedName("data12")
    private int mExtendedFieldTwelve;

    @SerializedName("extraDataMap")
    LinkedHashMap<String, String> mExtraDataMap = new LinkedHashMap<>();

    @SerializedName("data10")
    private int mRidePower;
    private ffr mRunPostureDataInfo;

    public int getDataHeartRate() {
        return this.mDataHeartRate;
    }

    public void setDataHeartRate(int i) {
        this.mDataHeartRate = i;
    }

    public int getDataSpeed() {
        return this.mDataSpeed;
    }

    public void setDataSpeed(int i) {
        this.mDataSpeed = i;
    }

    public int getDataStepRate() {
        return this.mDataStepRate;
    }

    public void setDataStepRate(int i) {
        this.mDataStepRate = i;
    }

    public int getDataSwolf() {
        return this.mDataSwolf;
    }

    public void setDataSwolf(int i) {
        this.mDataSwolf = i;
    }

    public int getDataSwimRate() {
        return this.mDataSwimRate;
    }

    public void setDataSwimRate(int i) {
        this.mDataSwimRate = i;
    }

    public int getDataAttitude() {
        return this.mDataAttitude;
    }

    public void setDataAttitude(int i) {
        this.mDataAttitude = i;
    }

    public int getDataRunPosture() {
        return this.mDataRunPosture;
    }

    public void setDataRunPosture(int i) {
        this.mDataRunPosture = i;
    }

    public int getDataCalories() {
        return this.mDataCalories;
    }

    public void setDataCalories(int i) {
        this.mDataCalories = i;
    }

    public int getDataFrequency() {
        return this.mDataFrequency;
    }

    public void setDataFrequency(int i) {
        this.mDataFrequency = i;
    }

    public int getRidePower() {
        return this.mRidePower;
    }

    public void setRidePower(int i) {
        this.mRidePower = i;
    }

    public int getExtendedFieldEleven() {
        return this.mExtendedFieldEleven;
    }

    public void setExtendedFieldEleven(int i) {
        this.mExtendedFieldEleven = i;
    }

    public int getExtendedFieldTwelve() {
        return this.mExtendedFieldTwelve;
    }

    public void setExtendedFieldTwelve(int i) {
        this.mExtendedFieldTwelve = i;
    }

    public int getExtendedFieldThirteen() {
        return this.mExtendedFieldThirteen;
    }

    public void setExtendedFieldThirteen(int i) {
        this.mExtendedFieldThirteen = i;
    }

    public int getExtendedFieldFourteen() {
        return this.mExtendedFieldFourteen;
    }

    public void setExtendedFieldFourteen(int i) {
        this.mExtendedFieldFourteen = i;
    }

    public int getExtendedFieldFifteen() {
        return this.mExtendedFieldFifteen;
    }

    public void setExtendedFieldFifteen(int i) {
        this.mExtendedFieldFifteen = i;
    }

    public int getExtendedFieldSixteen() {
        return this.mExtendedFieldSixteen;
    }

    public void setExtendedFieldSixteen(int i) {
        this.mExtendedFieldSixteen = i;
    }

    public ffr getRunPostureDataInfo() {
        return this.mRunPostureDataInfo;
    }

    public LinkedHashMap<String, String> getExtraDataMap() {
        return this.mExtraDataMap;
    }

    public void setRunPostureDataInfo(ffr ffrVar) {
        this.mRunPostureDataInfo = ffrVar;
    }
}
