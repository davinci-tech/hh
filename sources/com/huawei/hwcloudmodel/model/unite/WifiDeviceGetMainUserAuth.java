package com.huawei.hwcloudmodel.model.unite;

import com.huawei.networkclient.IRequest;

/* loaded from: classes5.dex */
public class WifiDeviceGetMainUserAuth implements IRequest {
    private static final String URL_GET_MAIN_USER_AUTH = "/deviceAgent/getMainUserAuthMsg";

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return URL_GET_MAIN_USER_AUTH;
    }
}
