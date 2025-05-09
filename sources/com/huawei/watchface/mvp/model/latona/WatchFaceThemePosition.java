package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class WatchFaceThemePosition {

    @SerializedName("@position_label")
    private String mPositionLabel;

    @SerializedName("@position_rect")
    private String mPositionRect;

    public String getPositionLabel() {
        return this.mPositionLabel;
    }

    public void setPositionLabel(String str) {
        this.mPositionLabel = str;
    }

    public String getPositionRect() {
        return this.mPositionRect;
    }

    public void setPositionRect(String str) {
        this.mPositionRect = str;
    }

    public String toString() {
        return "WatchFaceThemePosition{mPositionLabel='" + this.mPositionLabel + "', mPositionRect='" + this.mPositionRect + "'}";
    }
}
