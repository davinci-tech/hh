package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class HandStyle {

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
        return "Hand{mIndex='" + this.mIndex + "', mResPreview='" + this.mResPreview + "'}";
    }
}
