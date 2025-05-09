package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class WifiDeviceControlDataModelReq implements IRequest {
    private static final String URL_WIFIDEVICECONTROLDATA = "/deviceAgent/deviceControl";
    private String devId;
    private List<DeviceServiceInfo> services;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setDeviceServiceInfo(List<DeviceServiceInfo> list) {
        this.services = list;
    }

    public List<DeviceServiceInfo> getDeviceServiceInfo() {
        return this.services;
    }

    public String toString() {
        return "WifiDeviceControlDataModelReq{devId=" + CommonUtil.l(this.devId) + ", services=" + this.services + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFIDEVICECONTROLDATA;
    }
}
