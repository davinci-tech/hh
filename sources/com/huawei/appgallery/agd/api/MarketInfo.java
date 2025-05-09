package com.huawei.appgallery.agd.api;

/* loaded from: classes8.dex */
public class MarketInfo {
    public int versionCode;
    public String versionName;

    public String toString() {
        return "MarketInfo{, versionCode=" + this.versionCode + ", versionName='" + this.versionName + "'}";
    }

    public MarketInfo(String str, int i) {
        this.versionName = str;
        this.versionCode = i;
    }
}
