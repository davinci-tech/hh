package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import health.compact.a.CommonUtil;

/* loaded from: classes9.dex */
public class WifiDeviceGetAuthorizeMainUserRsp extends CloudCommonReponse {
    private String mainUserAccount;
    private String mainUserHuid;

    public void setMainUserHuid(String str) {
        this.mainUserHuid = str;
    }

    public String getMainUserHuid() {
        return this.mainUserHuid;
    }

    public void setMainUserAccount(String str) {
        this.mainUserAccount = str;
    }

    public String getMainUserAccount() {
        return this.mainUserAccount;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceGetAuthorizeMainUserRsp{mainUserHuid='" + CommonUtil.l(this.mainUserHuid) + "', mainUserAccount='" + this.mainUserAccount + "'}";
    }
}
