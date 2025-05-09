package com.huawei.basefitnessadvice.model;

/* loaded from: classes3.dex */
public class Data {
    private String mContent;
    private String mFileName;
    private String mId;
    private String mMd5;
    private long mSize;
    private String mUrl;

    public long acquireSize() {
        return this.mSize;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public String acquireFileName() {
        return this.mFileName;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String acquireContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public String acquireId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String acquireUrl() {
        return this.mUrl;
    }

    public void saveUrl(String str) {
        this.mUrl = str;
    }

    public String acquireMd5() {
        return this.mMd5;
    }

    public void saveMd5(String str) {
        this.mMd5 = str;
    }

    public String toString() {
        return "Data{mContent='" + this.mContent + "', mId='" + this.mId + "', mUrl='" + this.mUrl + "', mMd5='" + this.mMd5 + "', mSize='" + this.mSize + "', mFileName='" + this.mFileName + "'}";
    }
}
