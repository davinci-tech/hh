package com.huawei.health.suggestion.h5pro.h5params;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class SpeakParams {

    @SerializedName("actionNameUrl")
    private String mActionNameUrl;

    @SerializedName("code")
    private String mCode;

    @SerializedName("groupNum")
    private int mGroupNum;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("repeats")
    private int mRepeats;

    @SerializedName("type")
    private int mType;

    @SerializedName("url")
    private String mUrl;

    public String getLanguage() {
        return this.mLanguage;
    }

    public void setLanguage(String str) {
        this.mLanguage = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getCode() {
        return this.mCode;
    }

    public void setCode(String str) {
        this.mCode = str;
    }

    public String getActionNameUrl() {
        return this.mActionNameUrl;
    }

    public void setActionNameUrl(String str) {
        this.mActionNameUrl = str;
    }

    public int getGroupNum() {
        return this.mGroupNum;
    }

    public void setGroupNum(int i) {
        this.mGroupNum = i;
    }

    public int getRepeats() {
        return this.mRepeats;
    }

    public void setRepeats(int i) {
        this.mRepeats = i;
    }
}
