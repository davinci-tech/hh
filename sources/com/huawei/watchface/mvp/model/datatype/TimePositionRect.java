package com.huawei.watchface.mvp.model.datatype;

/* loaded from: classes9.dex */
public class TimePositionRect {
    private String mPositionLabel;
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
        return "TimePositionRect{mPositionLabel='" + this.mPositionLabel + "', mPositionRect='" + this.mPositionRect + "'}";
    }
}
