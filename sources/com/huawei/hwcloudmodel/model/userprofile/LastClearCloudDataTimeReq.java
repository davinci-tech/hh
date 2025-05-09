package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class LastClearCloudDataTimeReq implements IRequest {

    @SerializedName(ParsedFieldTag.KAKA_TASK_SCENARIO)
    private int scenario = 0;

    public int getScenario() {
        return this.scenario;
    }

    public void setScenario(int i) {
        this.scenario = i;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/profile/user/lastDelOperation";
    }
}
