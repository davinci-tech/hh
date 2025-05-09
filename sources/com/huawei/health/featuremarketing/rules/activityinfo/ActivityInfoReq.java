package com.huawei.health.featuremarketing.rules.activityinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes3.dex */
public class ActivityInfoReq implements IRequest {

    @SerializedName("activityId")
    private String activityId;

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public String getActivityId() {
        return this.activityId;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("activityUrl") + "/activity/getUserActivityInfo";
    }
}
