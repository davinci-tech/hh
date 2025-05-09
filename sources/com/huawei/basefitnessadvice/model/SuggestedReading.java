package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class SuggestedReading {

    @SerializedName("coverUrl")
    private String mCoverUrl;

    @SerializedName("id")
    private int mId;

    @SerializedName("subTitle")
    private String mSubTitle;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("url")
    private String mUrl;

    public int acquireId() {
        return this.mId;
    }

    public void saveId(int i) {
        this.mId = i;
    }

    public String acquireTitle() {
        return this.mTitle;
    }

    public void saveTitle(String str) {
        this.mTitle = str;
    }

    public String acquireSubTitle() {
        return this.mSubTitle;
    }

    public void saveSubTitle(String str) {
        this.mSubTitle = str;
    }

    public String acquireCoverUrl() {
        return this.mCoverUrl;
    }

    public void saveCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public String acquireUrl() {
        return this.mUrl;
    }

    public void saveUrl(String str) {
        this.mUrl = str;
    }

    public String toString() {
        return "SuggestedReading{mId=" + this.mId + ", mTitle='" + this.mTitle + "', mSubTitle='" + this.mSubTitle + "', mCoverUrl='" + this.mCoverUrl + "', mUrl='" + this.mUrl + "'}";
    }
}
