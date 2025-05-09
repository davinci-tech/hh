package com.huawei.health.plan.model.model;

/* loaded from: classes3.dex */
public class BasicPackage {
    private String mGender;
    private String mLanguage;
    private String mPostfix;
    private String mProvider;
    private int mSize;
    private String mType;
    private String mVersion;

    public String acquireLanguage() {
        return this.mLanguage;
    }

    public void saveLanguage(String str) {
        this.mLanguage = str;
    }

    public String acquireType() {
        return this.mType;
    }

    public void saveType(String str) {
        this.mType = str;
    }

    public String acquirePostFix() {
        return this.mPostfix;
    }

    public void savePostFix(String str) {
        this.mPostfix = str;
    }

    public String acquireProvider() {
        return this.mProvider;
    }

    public void saveProvider(String str) {
        this.mProvider = str;
    }

    public String acquireVersion() {
        return this.mVersion;
    }

    public void saveVersion(String str) {
        this.mVersion = str;
    }

    public int acquireSize() {
        return this.mSize;
    }

    public void saveSize(int i) {
        this.mSize = i;
    }

    public String acquireGender() {
        return this.mGender;
    }

    public void saveGender(String str) {
        this.mGender = str;
    }

    public String toString() {
        return "BasicPackage{mLanguage='" + this.mLanguage + "', mType='" + this.mType + "', mPostfix='" + this.mPostfix + "', mProvider='" + this.mProvider + "', mVersion='" + this.mVersion + "', mSize=" + this.mSize + ", mGender='" + this.mGender + '}';
    }
}
