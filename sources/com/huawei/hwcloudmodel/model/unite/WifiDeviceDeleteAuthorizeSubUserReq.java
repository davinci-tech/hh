package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceDeleteAuthorizeSubUserReq implements IRequest {
    private static final String URL_WIFIDEVICEDELETEAUTHORIZESUBUSER = "/deviceAgent/deauthorizationSubUser";
    private String devId;
    private String subHuid;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setSubHuid(String str) {
        this.subHuid = str;
    }

    public String getSubHuid() {
        return this.subHuid;
    }

    public String toString() {
        return "WifiDeviceDeleteAuthorizeSubUserReq{devId=" + CommonUtil.l(this.devId) + ", subHuid=" + CommonUtil.l(this.subHuid) + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFIDEVICEDELETEAUTHORIZESUBUSER;
    }
}
