package com.huawei.health.suggestion.h5pro.h5params;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class CourseDayPlanDetail {

    @SerializedName("calorie")
    private int mCalorie;

    @SerializedName("courseName")
    private String mCourseName;

    @SerializedName("duration")
    private int mDuration;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("punchFlag")
    private int mPunchFlag;

    public String getPicture() {
        return this.mPicture;
    }

    public void setPicture(String str) {
        this.mPicture = str;
    }

    public int getPunchFlag() {
        return this.mPunchFlag;
    }

    public void setPunchFlag(int i) {
        this.mPunchFlag = i;
    }

    public String getCourseName() {
        return this.mCourseName;
    }

    public void setCourseName(String str) {
        this.mCourseName = str;
    }

    public int getCalorie() {
        return this.mCalorie;
    }

    public void setCalorie(int i) {
        this.mCalorie = i;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }
}
