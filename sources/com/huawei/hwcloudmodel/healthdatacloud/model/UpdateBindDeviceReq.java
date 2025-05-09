package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class UpdateBindDeviceReq implements IRequest {

    @SerializedName("deviceCode")
    private Long deviceCode;

    @SerializedName("manufacturer")
    private String manufacturer = "";

    @SerializedName("firmwareVersion")
    private String firmwareVersion = "";

    @SerializedName("hardwareVersion")
    private String hardwareVersion = "";

    @SerializedName("softwareVersion")
    private String softwareVersion = "";

    @SerializedName("name")
    private String name = "";

    @SerializedName("deviceData")
    private String deviceData = "";

    @SerializedName("prodid")
    private String prodid = "";

    @SerializedName(ProfileRequestConstants.SUB_PROD_ID)
    private String subProdId = "";

    @SerializedName("sn")
    private String sn = "";

    @SerializedName("mac")
    private String mac = "";

    @SerializedName("udid")
    private String udid = "";

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDeviceData() {
        return this.deviceData;
    }

    public void setDeviceData(String str) {
        this.deviceData = str;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String getProdid() {
        return this.prodid;
    }

    public void setProdid(String str) {
        this.prodid = str;
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
        return "UpdateBindDeviceReq{deviceCode=" + this.deviceCode + ", manufacturer='" + this.manufacturer + "', firmwareVersion='" + this.firmwareVersion + "', hardwareVersion='" + this.hardwareVersion + "', softwareVersion='" + this.softwareVersion + "', name='" + this.name + "', deviceData='" + this.deviceData + "', prodid='" + this.prodid + "', subProdId='" + this.subProdId + "', sn='" + this.sn + "', mac='" + this.mac + "', udid='" + this.udid + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/profile/device/updateBindDevice";
    }
}
