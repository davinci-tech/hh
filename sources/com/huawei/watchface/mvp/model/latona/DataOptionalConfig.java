package com.huawei.watchface.mvp.model.latona;

/* loaded from: classes9.dex */
public class DataOptionalConfig {
    private String mDataType;
    private String mIndex;
    private String mResPreview;

    public String getIndex() {
        return this.mIndex;
    }

    public void setIndex(String str) {
        this.mIndex = str;
    }

    public String getResPreview() {
        return this.mResPreview;
    }

    public void setResPreview(String str) {
        this.mResPreview = str;
    }

    public String getDataType() {
        return this.mDataType;
    }

    public void setDataType(String str) {
        this.mDataType = str;
    }

    public String toString() {
        return "DataOptionalConfig{mIndex='" + this.mIndex + "', mResPreview='" + this.mResPreview + "', mDataType='" + this.mDataType + "'}";
    }
}
