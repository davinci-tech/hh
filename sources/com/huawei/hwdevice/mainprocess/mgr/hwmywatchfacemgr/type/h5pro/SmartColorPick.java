package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class SmartColorPick {

    @SerializedName("colorString")
    private String colorString;

    @SerializedName("positionRect")
    private String positionRect;

    @SerializedName("resPreviewPath")
    private String resPreviewPath;

    @SerializedName("sourceUrl")
    private String sourceUrl;

    @SerializedName("watchFaceBgPath")
    private String watchFaceBgPath;

    public String getWatchFaceBgPath() {
        return this.watchFaceBgPath;
    }

    public void setWatchFaceBgPath(String str) {
        this.watchFaceBgPath = str;
    }

    public String getPositionRect() {
        return this.positionRect;
    }

    public void setPositionRect(String str) {
        this.positionRect = str;
    }

    public String getResPreviewPath() {
        return this.resPreviewPath;
    }

    public void setResPreviewPath(String str) {
        this.resPreviewPath = str;
    }

    public String getColorString() {
        return this.colorString;
    }

    public void setColorString(String str) {
        this.colorString = str;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String str) {
        this.sourceUrl = str;
    }
}
