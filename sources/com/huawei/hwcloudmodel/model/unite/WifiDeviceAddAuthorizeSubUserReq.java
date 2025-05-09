package com.huawei.hwcloudmodel.model.unite;

import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class WifiDeviceAddAuthorizeSubUserReq {
    private String devId;
    private String nickname;
    private String subHuid;
    private String verifyCode;

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

    public void setVerifyCode(String str) {
        this.verifyCode = str;
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    public String toString() {
        return "WifiDeviceAddAuthorizeSubUserReq{devId=" + CommonUtil.l(this.devId) + ", subHuid=" + CommonUtil.l(this.subHuid) + ", nickName=" + CommonUtil.l(this.nickname) + ", verifyCode=" + CommonUtil.l(this.verifyCode) + '}';
    }
}
