package com.huawei.ui.main.stories.soical.operationactivity;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.List;

/* loaded from: classes7.dex */
public class UserActivityInfoReq implements IRequest {

    @SerializedName("activityId")
    private String activityId;

    @SerializedName("activityList")
    private List<String> activityList;

    @SerializedName("queryType")
    private int queryType;

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public void setQueryType(int i) {
        this.queryType = i;
    }

    public void setActivityList(List<String> list) {
        this.activityList = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("activityUrl") + "/activity/getUserActivityInfo";
    }
}
