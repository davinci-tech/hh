package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceUpdateAuthorizeSubUserReq implements IRequest {
    private static final String URL_WIFIDEVICEUPDATEAUTHORIZESUBUSER = "/deviceAgent/updateAuthorizationSubUser";
    private String devId;
    private String nickname;
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

    public void setNickName(String str) {
        this.nickname = str;
    }

    public String getNickName() {
        return this.nickname;
    }

    public String toString() {
        return "WifiDeviceUpdateAuthorizeSubUserReq{devId=" + CommonUtil.l(this.devId) + ", subHuid=" + CommonUtil.l(this.subHuid) + ", nickName=" + CommonUtil.l(this.nickname) + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFIDEVICEUPDATEAUTHORIZESUBUSER;
    }
}
