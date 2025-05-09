package com.huawei.datatype;

import defpackage.iyl;
import defpackage.jdy;

/* loaded from: classes3.dex */
public class DataDeviceInfo {
    private String btVersion;
    private String deviceEmmcId;
    private String deviceImei;
    private String deviceMac;
    private String deviceModel;
    private String deviceOpenSourceVersion;
    private String deviceOtaPackageName;
    private String devicePhoneNumber;
    private String deviceSn;
    private String deviceSoftVersion;
    private int deviceType;
    private String deviceVersion;

    public String getBtVersion() {
        return (String) jdy.d(this.btVersion);
    }

    public void setBtVersion(String str) {
        this.btVersion = (String) jdy.d(str);
    }

    public int getDeviceType() {
        return ((Integer) jdy.d(Integer.valueOf(this.deviceType))).intValue();
    }

    public void setDeviceType(int i) {
        this.deviceType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getDeviceVersion() {
        return (String) jdy.d(this.deviceVersion);
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = (String) jdy.d(str);
    }

    public String getDeviceSoftVersion() {
        return (String) jdy.d(this.deviceSoftVersion);
    }

    public void setDeviceSoftVersion(String str) {
        this.deviceSoftVersion = (String) jdy.d(str);
    }

    public String getDeviceImei() {
        return (String) jdy.d(this.deviceImei);
    }

    public void setDeviceImei(String str) {
        this.deviceImei = (String) jdy.d(str);
    }

    public String getDevicePhoneNumber() {
        return (String) jdy.d(this.devicePhoneNumber);
    }

    public void setDevicePhoneNumber(String str) {
        this.devicePhoneNumber = (String) jdy.d(str);
    }

    public String getDeviceBtMac() {
        return (String) jdy.d(this.deviceMac);
    }

    public void setDeviceBtMac(String str) {
        this.deviceMac = (String) jdy.d(str);
    }

    public String getDeviceOpenSourceVersion() {
        return this.deviceOpenSourceVersion;
    }

    public void setDeviceOpenSourceVersion(String str) {
        this.deviceOpenSourceVersion = (String) jdy.d(str);
    }

    public String getDeviceSn() {
        return (String) jdy.d(this.deviceSn);
    }

    public void setDeviceSn(String str) {
        this.deviceSn = (String) jdy.d(str);
    }

    public String getDeviceModel() {
        return (String) jdy.d(this.deviceModel);
    }

    public void setDeviceModel(String str) {
        this.deviceModel = (String) jdy.d(str);
    }

    public String getDeviceEmmcId() {
        return (String) jdy.d(this.deviceEmmcId);
    }

    public void setDeviceEmmcId(String str) {
        this.deviceEmmcId = (String) jdy.d(str);
    }

    public String getDeviceOtaPackageName() {
        return this.deviceOtaPackageName;
    }

    public void setDeviceOtaPackageName(String str) {
        this.deviceOtaPackageName = str;
    }

    public String toString() {
        return "DataDeviceInfo [btVersion=" + this.btVersion + ", deviceType=" + this.deviceType + ", deviceVersion=" + this.deviceVersion + ", deviceSoftVersion=" + this.deviceSoftVersion + ", deviceImei=" + iyl.d().e(this.deviceImei) + ", devicePhoneNumber=" + this.devicePhoneNumber + ", deviceBtMac=" + iyl.d().e(this.deviceMac) + ", deviceOpenSourceVersion=" + this.deviceOpenSourceVersion + ", deviceModel=" + this.deviceModel + ", deviceEmmcId=" + this.deviceEmmcId + ", deviceOtaPackageName=" + this.deviceOtaPackageName + "]";
    }
}
