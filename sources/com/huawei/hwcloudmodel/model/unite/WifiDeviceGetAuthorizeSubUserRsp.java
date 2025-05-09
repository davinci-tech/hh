package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes5.dex */
public class WifiDeviceGetAuthorizeSubUserRsp extends CloudCommonReponse {
    private List<AuthorizeSubUserInfo> authorizeSubUserList;

    public void setAuthorizeSubUserList(List<AuthorizeSubUserInfo> list) {
        this.authorizeSubUserList = list;
    }

    public List<AuthorizeSubUserInfo> getAuthorizeSubUserList() {
        return this.authorizeSubUserList;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceGetAuthorizeSubUserRsp{" + this.authorizeSubUserList + '}';
    }
}
