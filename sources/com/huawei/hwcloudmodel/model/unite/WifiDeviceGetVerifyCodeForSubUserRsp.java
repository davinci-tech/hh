package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class WifiDeviceGetVerifyCodeForSubUserRsp extends CloudCommonReponse {
    private String expireTime;
    private String verifyCode;

    public void setVerifyCode(String str) {
        this.verifyCode = str;
    }

    public String getVerifyCode() {
        return this.verifyCode;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceGetVerifyCodeForSubUserRsp{verifyCode=" + CommonUtil.l(this.verifyCode) + ", expireTime=" + this.expireTime + '}';
    }
}
