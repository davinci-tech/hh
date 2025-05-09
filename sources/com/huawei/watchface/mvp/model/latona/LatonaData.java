package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class LatonaData {

    @SerializedName("@index")
    private String mDataIndex;

    @SerializedName("@data_type")
    private String mDataType;

    @SerializedName("@res_preview")
    private String mResPreview;

    public String getDataLabel() {
        return this.mDataIndex;
    }

    public void setDataLabel(String str) {
        this.mDataIndex = str;
    }

    public String getDataType() {
        return this.mDataType;
    }

    public void setDataType(String str) {
        this.mDataType = str;
    }

    public String getResPreview() {
        return this.mResPreview;
    }

    public void setResPreview(String str) {
        this.mResPreview = str;
    }

    public String toString() {
        return "LatonaData{mDataIndex='" + this.mDataIndex + "', mPositionRect='" + this.mDataType + "', mResPreview='" + this.mResPreview + "'}";
    }
}
