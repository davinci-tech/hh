package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class WifiDeviceGetAllDeviceRsp extends CloudCommonReponse {
    private List<DeviceDetailInfo> authorizedDeviceDetailInfoList;
    private List<DeviceDetailInfo> deviceDetailInfoList;

    public void setDeviceDetailInfoList(List<DeviceDetailInfo> list) {
        this.deviceDetailInfoList = list;
    }

    public List<DeviceDetailInfo> getDeviceDetailInfoList() {
        return this.deviceDetailInfoList;
    }

    public void setAuthorizedDeviceDetailInfoList(List<DeviceDetailInfo> list) {
        this.authorizedDeviceDetailInfoList = list;
    }

    public List<DeviceDetailInfo> getAuthorizedDeviceDetailInfoList() {
        return this.authorizedDeviceDetailInfoList;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceGetAllDeviceRsp{deviceDetailInfoList=" + this.deviceDetailInfoList + ", authorizedDeviceDetailInfoList=" + this.authorizedDeviceDetailInfoList + '}';
    }
}
