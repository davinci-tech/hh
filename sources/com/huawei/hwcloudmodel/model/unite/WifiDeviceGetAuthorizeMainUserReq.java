package com.huawei.hwcloudmodel.model.unite;

import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class WifiDeviceGetAuthorizeMainUserReq {
    private String devId;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public String toString() {
        return "WifiDeviceGetAuthorizeMainUserReq{devId=" + CommonUtil.l(this.devId) + '}';
    }
}
