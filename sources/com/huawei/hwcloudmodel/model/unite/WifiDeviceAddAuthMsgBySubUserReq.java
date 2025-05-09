package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceAddAuthMsgBySubUserReq implements IRequest {
    private static final String URL_WIFI_DEVICE_ADD_AUTH_MSG_BY_SUBUSER = "/deviceAgent/addAuthMsgBySubUser";
    private String devId;
    private int expireTime = 604800;
    private String mainHuid;
    private String nickName;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setMainHuid(String str) {
        this.mainHuid = str;
    }

    public String getMainHuid() {
        return this.mainHuid;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setExpireTime(int i) {
        this.expireTime = i;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    public String toString() {
        return "WifiDeviceAddAuthorizeForSubUserReq{devId=" + CommonUtil.l(this.devId) + ", mainHuid=" + CommonUtil.l(this.mainHuid) + ", nickName=" + CommonUtil.l(this.nickName) + ", expireTime=" + this.expireTime + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_WIFI_DEVICE_ADD_AUTH_MSG_BY_SUBUSER;
    }
}
