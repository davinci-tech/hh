package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import java.util.List;

/* loaded from: classes8.dex */
public class DevicePerfPurchaseRsp extends BaseRsp {
    private List<DevicePerfPurchaseInfo> devicePerfPurchaseInfoList;

    public List<DevicePerfPurchaseInfo> getDevicePerfPurchaseInfoList() {
        return this.devicePerfPurchaseInfoList;
    }

    public void getDevicePerfPurchaseInfoList(List<DevicePerfPurchaseInfo> list) {
        this.devicePerfPurchaseInfoList = list;
    }
}
