package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class WifiDeviceGetSubUserAuthMsgRsp extends CloudCommonReponse {
    private List<WifiDeviceSubUserAuthMsg> authMsgs;

    public List<WifiDeviceSubUserAuthMsg> getAuthMsgs() {
        return this.authMsgs;
    }

    public void setAuthMsgs(List<WifiDeviceSubUserAuthMsg> list) {
        this.authMsgs = list;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        List<WifiDeviceSubUserAuthMsg> list = this.authMsgs;
        if (list != null) {
            for (WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg : list) {
                stringBuffer.append(stringBuffer);
                stringBuffer.append(" ");
                stringBuffer.append(wifiDeviceSubUserAuthMsg.toString());
            }
        }
        return "CloudCommonReponse{resultCode=" + getResultCode() + ", resultDesc='" + getResultDesc() + "'authMsgs=" + stringBuffer.toString() + '}';
    }
}
