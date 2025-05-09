package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;

/* loaded from: classes5.dex */
public class WifiDeviceSubuserAuthorize implements IRequest {
    private static final String URL_SUB_USER_AUTHORIZE = "/deviceAgent/authorizeBySubUser";
    private String devId;
    private int intent;
    private String mainHuid;
    private String nickname;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getMainHuid() {
        return this.mainHuid;
    }

    public void setMainHuid(String str) {
        this.mainHuid = str;
    }

    public int getIntent() {
        return this.intent;
    }

    public void setIntent(int i) {
        this.intent = i;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_SUB_USER_AUTHORIZE;
    }
}
