package com.huawei.hwversionmgr.manager;

/* loaded from: classes5.dex */
public class OverseaAppUpdateConfig {
    String downloadUrl;
    boolean isOverseaHealthUpdateOpen;
    int latestVersionCode;

    OverseaAppUpdateConfig() {
    }

    public int getVersionCode() {
        return this.latestVersionCode;
    }

    public String getUrl() {
        return this.downloadUrl;
    }

    public boolean getIsUpdateOpen() {
        return this.isOverseaHealthUpdateOpen;
    }

    public String toString() {
        return "url: " + this.downloadUrl + ";versionCode: " + this.latestVersionCode + ";isOverseaUpdateOpen: " + this.isOverseaHealthUpdateOpen;
    }
}
