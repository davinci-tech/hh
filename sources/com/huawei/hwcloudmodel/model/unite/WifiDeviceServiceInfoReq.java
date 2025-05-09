package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceServiceInfoReq implements IRequest {
    private static final String URL_WIFIDEVICEGETSERVICEINFO = "/deviceAgent/getServiceInfo";
    private String devId;
    private String sid;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setSid(String str) {
        this.sid = str;
    }

    public String getSid() {
        return this.sid;
    }

    public String toString() {
        return "WifiDeviceServiceInfoReq{devId=" + CommonUtil.l(this.devId) + "sid=" + this.sid + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFIDEVICEGETSERVICEINFO;
    }
}
