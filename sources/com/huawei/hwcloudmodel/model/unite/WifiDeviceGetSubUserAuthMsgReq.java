package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceGetSubUserAuthMsgReq implements IRequest {
    private static final String URL_WIFI_DEVICE_GET_SUB_USER_AUTH_MSG = "/deviceAgent/getSubUserAuthMsg";
    private String devId;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public String toString() {
        return "WifiDeviceAddAuthorizeForSubUserReq{devId=" + CommonUtil.l(this.devId) + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFI_DEVICE_GET_SUB_USER_AUTH_MSG;
    }
}
