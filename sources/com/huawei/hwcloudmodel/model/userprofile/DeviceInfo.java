package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.profile.coordinator.ProfileRequestConstants;

/* loaded from: classes5.dex */
public class DeviceInfo {

    @SerializedName("deviceCode")
    private Long deviceCode;

    @SerializedName("deviceData")
    private String deviceData;

    @SerializedName("firmwareVersion")
    private String firmwareVersion;

    @SerializedName("hardwareVersion")
    private String hardwareVersion;

    @SerializedName("mac")
    private String mac;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private Long modifyTime;

    @SerializedName("name")
    private String name;

    @SerializedName("appName")
    private String packageName;

    @SerializedName("priority")
    private Integer priority;

    @SerializedName("prodId")
    private String prodId;

    @SerializedName("productId")
    private Integer productId;

    @SerializedName("sn")
    private String sn;

    @SerializedName("softwareVersion")
    private String softwareVersion;

    @SerializedName(ProfileRequestConstants.SUB_PROD_ID)
    private String subProdId;

    @SerializedName("thirdAppInfo")
    private String thirdAppInfo;

    @SerializedName("udid")
    private String udid;

    @SerializedName("uniqueId")
    private String uniqueId;

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    public String getHardwareVersion() {
        return this.hardwareVersion;
    }

    public void setHardwareVersion(String str) {
        this.hardwareVersion = str;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String getDeviceData() {
        return this.deviceData;
    }

    public void setDeviceData(String str) {
        this.deviceData = str;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer num) {
        this.priority = num;
    }

    public Long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Long l) {
        this.modifyTime = l;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getThirdAppInfo() {
        return this.thirdAppInfo;
    }

    public void setThirdAppInfo(String str) {
        this.thirdAppInfo = str;
    }

    public String getProdId() {
        return this.prodId;
    }

    public void setProdId(String str) {
        this.prodId = str;
    }

    public String getSubProdId() {
        return this.subProdId;
    }

    public void setSubProdId(String str) {
        this.subProdId = str;
    }

    public String getSn() {
        return this.sn;
    }

    public void setSn(String str) {
        this.sn = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String str) {
        this.udid = str;
    }

    public String toString() {
        return "DeviceInfo{productId=" + this.productId + ", uniqueId='" + this.uniqueId + "', manufacturer='" + this.manufacturer + "', firmwareVersion='" + this.firmwareVersion + "', hardwareVersion='" + this.hardwareVersion + "', softwareVersion='" + this.softwareVersion + "', name='" + this.name + "', deviceCode=" + this.deviceCode + ", deviceData='" + this.deviceData + "', priority=" + this.priority + ", modifyTime=" + this.modifyTime + ", packageName=" + this.packageName + ", thirdAppInfo=" + this.thirdAppInfo + ", prodId=" + this.prodId + ", subProdId=" + this.subProdId + ", sn=" + this.sn + ", mac=" + this.mac + ", udid=" + this.udid + '}';
    }
}
