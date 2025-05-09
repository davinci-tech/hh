package com.huawei.ui.main.stories.nps.npsstate;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;

/* loaded from: classes7.dex */
public class NativeConfigBean {

    @SerializedName("isNeedSurvey")
    private boolean mIsNeedSurvey = false;

    @SerializedName("questionContent")
    private String mQuestionContent = "";

    @SerializedName(SmartMsgConstant.MSG_SHOW_TIME)
    private long mShowTime = 0;

    @SerializedName("huidStr")
    private String mHuidStr = "";

    @SerializedName("isHasNativeConfig")
    private boolean mIsHasNativeConfig = false;

    @SerializedName("expireTime")
    private long mExpireTime = 0;

    @SerializedName("isNeedShowNpsEnter")
    private boolean mIsNeedShowNpsEnter = true;

    public boolean isNeedShowNpsEnter() {
        return this.mIsNeedShowNpsEnter;
    }

    public void setNeedShowNpsEnter(boolean z) {
        this.mIsNeedShowNpsEnter = z;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long j) {
        this.mExpireTime = j;
    }

    public boolean isHasNativeConfig() {
        return this.mIsHasNativeConfig;
    }

    public void setHasNativeConfig(boolean z) {
        this.mIsHasNativeConfig = z;
    }

    public String getHuidStr() {
        return this.mHuidStr;
    }

    public void setHuidStr(String str) {
        this.mHuidStr = str;
    }

    public boolean isNeedSurvey() {
        return this.mIsNeedSurvey;
    }

    public void setNeedSurvey(boolean z) {
        this.mIsNeedSurvey = z;
    }

    public String getQuestionContent() {
        return this.mQuestionContent;
    }

    public void setQuestionContent(String str) {
        this.mQuestionContent = str;
    }

    public long getShowTime() {
        return this.mShowTime;
    }

    public void setShowTime(long j) {
        this.mShowTime = j;
    }
}
