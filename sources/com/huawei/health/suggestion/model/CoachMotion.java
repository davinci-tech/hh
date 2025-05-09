package com.huawei.health.suggestion.model;

import com.huawei.health.suggestion.ui.fitness.module.Motion;

/* loaded from: classes4.dex */
public class CoachMotion {
    private boolean isButtonVisible;
    private int mCurrMotionNum;
    private Motion mMotion;
    private String mMotionImageUrl;
    private int mTotalMotionNum;

    public int getCurrMotionNum() {
        return this.mCurrMotionNum;
    }

    public void setCurrMotionNum(int i) {
        this.mCurrMotionNum = i;
    }

    public String getMotionImageUrl() {
        return this.mMotionImageUrl;
    }

    public void setMotionImageUrl(String str) {
        this.mMotionImageUrl = str;
    }

    public Motion getMotion() {
        return this.mMotion;
    }

    public void setMotion(Motion motion) {
        this.mMotion = motion;
    }

    public int getTotalMotionNum() {
        return this.mTotalMotionNum;
    }

    public void setTotalMotionNum(int i) {
        this.mTotalMotionNum = i;
    }

    public boolean isButtonVisible() {
        return this.isButtonVisible;
    }

    public void setButtonVisible(boolean z) {
        this.isButtonVisible = z;
    }
}
