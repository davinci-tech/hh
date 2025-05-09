package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class LatonaStyle {

    @SerializedName("@index")
    private String mIndex;

    @SerializedName("@res_preview")
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

    public String toString() {
        return "LatonaStyle{mIndex='" + this.mIndex + "', mResPreview='" + this.mResPreview + "'}";
    }
}
