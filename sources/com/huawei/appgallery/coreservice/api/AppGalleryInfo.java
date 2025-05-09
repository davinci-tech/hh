package com.huawei.appgallery.coreservice.api;

/* loaded from: classes8.dex */
public class AppGalleryInfo {
    public int versionCode;
    public String versionName;

    public String toString() {
        return "AppGalleryInfo{, versionCode=" + this.versionCode + ", versionName='" + this.versionName + "'}";
    }

    public AppGalleryInfo(String str, int i) {
        this.versionName = str;
        this.versionCode = i;
    }
}
