package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;

/* loaded from: classes5.dex */
public class WifiDeviceShareMemberInfoBySubUserReq implements IRequest {
    private static final String URL_GET_MEMBERINFO_BY_SUB_USER = "/deviceAgent/getMemberInfoBySubUser";
    private String devId;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_GET_MEMBERINFO_BY_SUB_USER;
    }
}
