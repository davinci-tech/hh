package com.huawei.watchface.mvp.model.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class WatchDeviceInfo {

    @SerializedName("mDeviceCapability")
    private String mDeviceCapability;

    @SerializedName("mDeviceModel")
    private String mDeviceModel;

    @SerializedName("mDeviceName")
    private String mDeviceName;

    @SerializedName("mSoftVersion")
    private String mSoftVersion;

    @SerializedName("mDeviceConnectState")
    private int mDeviceConnectState = 0;

    @SerializedName("mProductType")
    private int mProductType = -1;

    public String getDeviceCapability() {
        return this.mDeviceCapability;
    }

    public void setDeviceCapability(String str) {
        this.mDeviceCapability = str;
    }

    public int getDeviceConnectState() {
        return this.mDeviceConnectState;
    }

    public void setDeviceConnectState(int i) {
        this.mDeviceConnectState = i;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public int getProductType() {
        return this.mProductType;
    }

    public void setProductType(int i) {
        this.mProductType = i;
    }

    public String getSoftVersion() {
        return this.mSoftVersion;
    }

    public void setSoftVersion(String str) {
        this.mSoftVersion = str;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public void setDeviceModel(String str) {
        this.mDeviceModel = str;
    }

    public String toString() {
        return "WatchDeviceInfo{, mDeviceConnectState=" + this.mDeviceConnectState + ", mDeviceName='" + this.mDeviceName + "', mProductType=" + this.mProductType + ", mSoftVersion='" + this.mSoftVersion + "', mDeviceModel='" + this.mDeviceModel + "', mDeviceCapability='" + this.mDeviceCapability + "'}";
    }
}
