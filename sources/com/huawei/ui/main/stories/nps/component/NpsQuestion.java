package com.huawei.ui.main.stories.nps.component;

/* loaded from: classes9.dex */
public class NpsQuestion {
    private String mAnswer = "-1";
    private String mId;
    private String mPictureUrl;
    private String mRequired;
    private String mSubTitle;
    private String mTitle;
    private String mType;

    public NpsQuestion(String str, String str2, String str3, String str4) {
        this.mType = str;
        this.mSubTitle = str2;
        this.mTitle = str3;
        this.mPictureUrl = str4;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getSubTitle() {
        return this.mSubTitle;
    }

    public void setSubTitle(String str) {
        this.mSubTitle = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getPictureUrl() {
        return this.mPictureUrl;
    }

    public void setPictureUrl(String str) {
        this.mPictureUrl = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getRequired() {
        return this.mRequired;
    }

    public void setRequired(String str) {
        this.mRequired = str;
    }

    public String getAnswer() {
        return this.mAnswer;
    }

    public void setAnswer(String str) {
        this.mAnswer = str;
    }

    public String toString() {
        return "NpsQuestion{mId='" + this.mId + "', mTitle='" + this.mTitle + "', mType='" + this.mType + "', mRequired='" + this.mRequired + "', mAnswer='" + this.mAnswer + "'}";
    }
}
