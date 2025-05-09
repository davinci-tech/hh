package com.huawei.hwcloudmodel.model.userprofile;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class DeleteAllUserProfileReq implements IRequest {
    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/deleteAllUserProfile";
    }
}
