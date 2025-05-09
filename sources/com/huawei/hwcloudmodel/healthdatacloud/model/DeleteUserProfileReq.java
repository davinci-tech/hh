package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes9.dex */
public class DeleteUserProfileReq implements IRequest {
    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/profile/user/deleteUserProfile";
    }
}
