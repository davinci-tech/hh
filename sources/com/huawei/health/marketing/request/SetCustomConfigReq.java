package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class SetCustomConfigReq implements IRequest {

    @SerializedName("customConfig")
    private CustomConfigInfo customConfig;

    public CustomConfigInfo getCustomConfig() {
        return this.customConfig;
    }

    public void setCustomConfig(CustomConfigInfo customConfigInfo) {
        this.customConfig = customConfigInfo;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/setCustomConfig";
    }
}
