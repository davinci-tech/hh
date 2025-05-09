package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceUnbindReq implements IRequest {
    private static final String URL_WIFIDEVICEUNBIND = "/deviceAgent/unbindDevice";
    private String devId;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public String toString() {
        return "WifiDeviceUnbindReq{devId=" + CommonUtil.l(this.devId) + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFIDEVICEUNBIND;
    }
}
