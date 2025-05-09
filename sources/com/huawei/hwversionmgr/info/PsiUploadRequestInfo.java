package com.huawei.hwversionmgr.info;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class PsiUploadRequestInfo {

    @SerializedName("commonInfo")
    private CommonInfo commonInfo;

    @SerializedName("componentVersion")
    private ComponentVersion componentVersion;

    @SerializedName("deviceInfo")
    private DeviceInfo deviceInfo;

    @SerializedName("extraInfo")
    private String extraInfo;

    @SerializedName("marketFlag")
    private MarketFlag marketFlag;

    @SerializedName("securityInfo")
    private SecurityInfo securityInfo;

    @SerializedName("systemVersion")
    private SystemVersion systemVersion;

    public CommonInfo getCommonInfo() {
        return this.commonInfo;
    }

    public void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }

    public SecurityInfo getSecurityInfo() {
        return this.securityInfo;
    }

    public void setSecurityInfo(SecurityInfo securityInfo) {
        this.securityInfo = securityInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public ComponentVersion getComponentVersion() {
        return this.componentVersion;
    }

    public void setComponentVersion(ComponentVersion componentVersion) {
        this.componentVersion = componentVersion;
    }

    public SystemVersion getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(SystemVersion systemVersion) {
        this.systemVersion = systemVersion;
    }

    public MarketFlag getMarketFlag() {
        return this.marketFlag;
    }

    public void setMarketFlag(MarketFlag marketFlag) {
        this.marketFlag = marketFlag;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public void setExtraInfo(String str) {
        this.extraInfo = str;
    }
}
