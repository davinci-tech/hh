package com.huawei.health.tradeservice.cloud;

import com.huawei.trade.datatype.DeviceInboxInfo;
import java.util.List;

/* loaded from: classes8.dex */
public class DeviceInboxRsp extends BaseRsp {
    private List<DeviceInboxInfo> deviceInboxInfoList;

    public List<DeviceInboxInfo> getDeviceInboxInfoList() {
        return this.deviceInboxInfoList;
    }

    public void setDeviceInboxInfoList(List<DeviceInboxInfo> list) {
        this.deviceInboxInfoList = list;
    }
}
