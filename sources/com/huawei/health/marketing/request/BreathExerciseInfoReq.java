package com.huawei.health.marketing.request;

import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class BreathExerciseInfoReq implements IRequest {
    private String ids;

    public String getIds() {
        return this.ids;
    }

    public void setIds(String str) {
        this.ids = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("messageCenterUrl") + "/messageCenter/getBreathTrainingList";
    }
}
