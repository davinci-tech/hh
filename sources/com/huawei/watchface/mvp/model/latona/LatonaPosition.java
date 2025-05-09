package com.huawei.watchface.mvp.model.latona;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class LatonaPosition {

    @SerializedName("@index")
    private String mPositionIndex;

    @SerializedName("@position_rect")
    private String mPositionRect;

    public String getPositionLabel() {
        return this.mPositionIndex;
    }

    public void setPositionLabel(String str) {
        this.mPositionIndex = str;
    }

    public String getPositionRect() {
        return this.mPositionRect;
    }

    public void setPositionRect(String str) {
        this.mPositionRect = str;
    }

    public String toString() {
        return "Position{mPositionIndex='" + this.mPositionIndex + "', mPositionRect='" + this.mPositionRect + "'}";
    }
}
