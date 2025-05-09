package com.huawei.health.h5pro.jsbridge.system.option;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class LightAppStartObj {

    @SerializedName("launchOption")
    public String b;

    @SerializedName("url")
    public String d;

    @SerializedName("mapJson")
    public String e;

    public void setUrl(String str) {
        this.d = str;
    }

    public void setMapJson(String str) {
        this.e = str;
    }

    public void setLaunchOption(String str) {
        this.b = str;
    }

    public String getUrl() {
        return this.d;
    }

    public String getMapJson() {
        return this.e;
    }

    public String getLaunchOption() {
        return this.b;
    }
}
