package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceBenefits {

    @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
    private String mDeviceSn;

    @SerializedName("deviceType")
    private String mDeviceType;

    @SerializedName("inboxInfos")
    private List<DeviceInboxInfo> mInboxInfos = new ArrayList(10);

    @SerializedName("perfPurchaseInfos")
    private List<DevicePerfPurchaseInfo> mPerfPurchaseInfos = new ArrayList(10);

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(String str) {
        this.mDeviceType = str;
    }

    public String getDeviceSn() {
        return this.mDeviceSn;
    }

    public void setDeviceSn(String str) {
        this.mDeviceSn = str;
    }

    public List<DeviceInboxInfo> getInboxInfos() {
        return this.mInboxInfos;
    }

    public void setInboxInfos(List<DeviceInboxInfo> list) {
        if (koq.c(list)) {
            this.mInboxInfos.addAll(list);
        }
    }

    public List<DevicePerfPurchaseInfo> getPerfPurchaseInfos() {
        return this.mPerfPurchaseInfos;
    }

    public void setPerfPurchaseInfos(List<DevicePerfPurchaseInfo> list) {
        if (koq.c(list)) {
            this.mPerfPurchaseInfos.addAll(list);
        }
    }

    public String toString() {
        return "DeviceBenefits{mDeviceType='" + this.mDeviceType + "', mDeviceSn='" + this.mDeviceSn + "', mInboxInfos=" + this.mInboxInfos + ", mPerfPurchaseInfos=" + this.mPerfPurchaseInfos + '}';
    }
}
