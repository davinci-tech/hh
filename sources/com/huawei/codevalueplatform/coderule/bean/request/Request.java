package com.huawei.codevalueplatform.coderule.bean.request;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class Request {

    @SerializedName("appPkgName")
    private String appPkgName;

    @SerializedName("appVersion")
    private String appVersion;

    @SerializedName("deviceInfo")
    private DeviceInfo deviceInfo;

    @SerializedName("transactionID")
    private String transactionID;

    public String getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(String str) {
        this.transactionID = str;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public void setAppPkgName(String str) {
        this.appPkgName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }
}
