package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.koq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class DevicePolicyInfos {

    @SerializedName("deviceInboxInfoList")
    private List<DeviceInboxInfo> deviceInboxInfoList = new ArrayList(10);

    @SerializedName("devicePerfPurchaseInfoList")
    private List<DevicePerfPurchaseInfo> devicePerfPurchaseInfoList = new ArrayList(10);

    public List<DeviceInboxInfo> getDeviceInboxInfoList() {
        return this.deviceInboxInfoList;
    }

    public void setDeviceInboxInfoList(List<DeviceInboxInfo> list) {
        if (koq.c(list)) {
            this.deviceInboxInfoList.addAll(list);
        }
    }

    public List<DevicePerfPurchaseInfo> getDevicePerfPurchaseInfoList() {
        return this.devicePerfPurchaseInfoList;
    }

    public void setDevicePerfPurchaseInfoList(List<DevicePerfPurchaseInfo> list) {
        if (koq.c(list)) {
            this.devicePerfPurchaseInfoList.addAll(list);
        }
    }
}
