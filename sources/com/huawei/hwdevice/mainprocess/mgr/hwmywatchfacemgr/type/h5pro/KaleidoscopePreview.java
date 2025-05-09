package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.gson.annotations.SerializedName;

/* loaded from: classes.dex */
public class KaleidoscopePreview {

    @SerializedName("desPath")
    private String desPath;

    @SerializedName("kaleidoscopeType")
    private int kaleidoscopeType;

    public String getDesPath() {
        return this.desPath;
    }

    public void setDesPath(String str) {
        this.desPath = str;
    }

    public int getKaleidoscopeType() {
        return this.kaleidoscopeType;
    }

    public void setKaleidoscopeType(int i) {
        this.kaleidoscopeType = i;
    }
}
