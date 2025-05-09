package com.huawei.health.suggestion.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class DeviceResponse {

    @SerializedName("ability")
    private int mAbility = -1;

    @SerializedName("errorCode")
    private int mErrorCode;

    @SerializedName("language")
    private String[] mLanguage;

    @SerializedName("sendResult")
    private int mSendResult;

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getAbility() {
        return this.mAbility;
    }

    public void setAbility(int i) {
        this.mAbility = i;
    }

    public int getSendResult() {
        return this.mSendResult;
    }

    public void setSendResult(int i) {
        this.mSendResult = i;
    }

    public String[] getLanguage() {
        return this.mLanguage;
    }

    public void setLanguage(String[] strArr) {
        this.mLanguage = strArr;
    }
}
