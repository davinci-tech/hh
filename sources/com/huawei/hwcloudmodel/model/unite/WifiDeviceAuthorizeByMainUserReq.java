package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceAuthorizeByMainUserReq implements IRequest {
    private static final String URL_WIFI_DEVICE_AUTHORIZE_BY_MAIN_USER = "/deviceAgent/authorizeByMainUser";
    private String devId;
    private int intent;
    private String subHuid;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public String getSubHuid() {
        return this.subHuid;
    }

    public void setSubHuid(String str) {
        this.subHuid = str;
    }

    public int getIntent() {
        return this.intent;
    }

    public void setIntent(int i) {
        this.intent = i;
    }

    public String toString() {
        return "WifiDeviceAddAuthorizeForSubUserReq{devId=" + CommonUtil.l(this.devId) + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFI_DEVICE_AUTHORIZE_BY_MAIN_USER;
    }
}
