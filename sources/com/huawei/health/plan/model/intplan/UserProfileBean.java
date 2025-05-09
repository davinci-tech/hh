package com.huawei.health.plan.model.intplan;

import com.google.gson.annotations.SerializedName;
import com.huawei.basefitnessadvice.model.intplan.UserProfile;
import com.huawei.hms.support.feature.result.CommonConstant;

/* loaded from: classes3.dex */
public class UserProfileBean {

    @SerializedName("age")
    private int mAge;

    @SerializedName("bfr")
    private float mBfr;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int mGender;

    @SerializedName("height")
    private int mHeight;

    @SerializedName("weight")
    private float mWeight;

    public UserProfileBean() {
        this(null);
    }

    public UserProfileBean(UserProfile userProfile) {
        if (userProfile == null) {
            return;
        }
        this.mGender = userProfile.getGender();
        this.mAge = userProfile.getAge();
        this.mHeight = userProfile.getHeight();
        this.mWeight = userProfile.getWeight();
        this.mBfr = userProfile.getBfr();
    }

    public int getGender() {
        return this.mGender;
    }

    public void setGender(int i) {
        this.mGender = i;
    }

    public int getAge() {
        return this.mAge;
    }

    public void setAge(int i) {
        this.mAge = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public float getWeight() {
        return this.mWeight;
    }

    public void setWeight(float f) {
        this.mWeight = f;
    }

    public float getBfr() {
        return this.mBfr;
    }

    public void setBfr(float f) {
        this.mBfr = f;
    }
}
