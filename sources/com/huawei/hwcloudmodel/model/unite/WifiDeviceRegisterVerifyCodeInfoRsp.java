package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class WifiDeviceRegisterVerifyCodeInfoRsp extends CloudCommonReponse {
    private RegisterInfo registerInfo;

    public void setRegisterInfo(RegisterInfo registerInfo) {
        this.registerInfo = registerInfo;
    }

    public RegisterInfo getRegisterInfo() {
        return this.registerInfo;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceRegisterVerifyCodeInfoRsp{" + this.registerInfo + '}';
    }
}
