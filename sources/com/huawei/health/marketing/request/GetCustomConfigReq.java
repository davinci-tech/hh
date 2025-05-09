package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class GetCustomConfigReq implements IRequest {

    @SerializedName("customConfig")
    private ArrayList<String> customConfig;

    public ArrayList<String> getCustomConfig() {
        return this.customConfig;
    }

    public void setCustomConfig(ArrayList<String> arrayList) {
        this.customConfig = arrayList;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/getCustomConfig";
    }
}
