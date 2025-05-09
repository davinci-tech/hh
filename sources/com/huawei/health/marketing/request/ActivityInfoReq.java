package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes3.dex */
public class ActivityInfoReq implements IRequest {

    @SerializedName("activityIdList")
    private List<String> activityIdList;

    public List<String> getActivityIdList() {
        return this.activityIdList;
    }

    public void setActivityIdList(List<String> list) {
        this.activityIdList = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("activityUrl") + "/activity/getActivityInfoByIds";
    }
}
