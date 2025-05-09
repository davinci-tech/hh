package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;

/* loaded from: classes5.dex */
public class WifiDeviceShareByMainUserReq implements IRequest {
    private static final String URL_ADD_AUTH_BY_MAIN_USER = "/deviceAgent/addAuthMsgByMainUser";
    private String devId;
    private int expireTime;
    private String nickname;
    private String subHuid;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getSubHuid() {
        return this.subHuid;
    }

    public void setSubHuid(String str) {
        this.subHuid = str;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(int i) {
        this.expireTime = i;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_ADD_AUTH_BY_MAIN_USER;
    }
}
