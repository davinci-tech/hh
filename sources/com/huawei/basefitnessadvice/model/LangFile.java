package com.huawei.basefitnessadvice.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class LangFile {

    @SerializedName("langId")
    private int mLangId;

    @SerializedName("langName")
    private String mLangName;

    @SerializedName("langUrl")
    private String mLangUrl;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("timbre")
    private String mTimbre;

    @SerializedName("version")
    private long mVersion;

    public String acquireLangName() {
        return this.mLangName;
    }

    public void saveLangName(String str) {
        this.mLangName = str;
    }

    public String acquireLangUrl() {
        return this.mLangUrl;
    }

    public void saveLangUrl(String str) {
        this.mLangUrl = str;
    }

    public long acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(long j) {
        this.mVersion = j;
    }

    public int acquireLangId() {
        return this.mLangId;
    }

    public void saveLangId(int i) {
        this.mLangId = i;
    }

    public String acquireLanguage() {
        return this.mLanguage;
    }

    public void saveLanguage(String str) {
        this.mLanguage = str;
    }

    public String getTimbre() {
        return this.mTimbre;
    }

    public void setTimbre(String str) {
        this.mTimbre = str;
    }

    public String toString() {
        return "LangFile{mLangName='" + this.mLangName + "', mLangUrl='" + this.mLangUrl + "', mVersion=" + this.mVersion + ", mLangId=" + this.mLangId + ", mLanguage='" + this.mLanguage + "', mTimbre='" + this.mTimbre + "'}";
    }
}
