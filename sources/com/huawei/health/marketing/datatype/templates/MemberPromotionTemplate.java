package com.huawei.health.marketing.datatype.templates;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class MemberPromotionTemplate extends BaseTemplate {

    @SerializedName("buttonName")
    private String mButtonName;

    @SerializedName("buttonVisibility")
    private boolean mButtonVisibility;

    @SerializedName("deadline")
    private long mDeadlineTime;

    @SerializedName("displayControls")
    private String mDisplayControls;

    @SerializedName("linkValue")
    private String mLinkValue;

    @SerializedName("picture")
    private String mPicture;

    @SerializedName("tahitiPicture")
    private String mTahitiPicture;

    @SerializedName("theme")
    private String mTheme;

    public String getTheme() {
        return this.mTheme;
    }

    public String getButtonName() {
        return this.mButtonName;
    }

    public boolean isButtonVisibility() {
        return this.mButtonVisibility;
    }

    public String getLinkValue() {
        return this.mLinkValue;
    }

    public String getPicture() {
        return this.mPicture;
    }

    public String getTahitiPicture() {
        return this.mTahitiPicture;
    }

    public void setTheme(String str) {
        this.mTheme = str;
    }

    public void setButtonName(String str) {
        this.mButtonName = str;
    }

    public void setButtonVisibility(boolean z) {
        this.mButtonVisibility = z;
    }

    public void setLinkValue(String str) {
        this.mLinkValue = str;
    }

    public void setPicture(String str) {
        this.mPicture = str;
    }

    public void setTahitiPicture(String str) {
        this.mTahitiPicture = str;
    }

    public String getDisplayControls() {
        return this.mDisplayControls;
    }

    public void setDisplayControls(String str) {
        this.mDisplayControls = str;
    }

    public void setDeadlineTime(long j) {
        this.mDeadlineTime = j;
    }

    public long getDeadlineTime() {
        return this.mDeadlineTime;
    }
}
