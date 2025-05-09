package com.huawei.health.messagecenter.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.h5pro.jsmodules.complaint.ComplaintConstants;

/* loaded from: classes3.dex */
public class Group {

    @SerializedName("activityId")
    private String mActivityId;

    @SerializedName("activityName")
    private String mActivityName;

    @SerializedName("groupId")
    private String mGroupId;

    @SerializedName(ComplaintConstants.GROUP_NAME_PARAM_KEY)
    private String mGroupName;

    public void setActivityId(String str) {
        this.mActivityId = str;
    }

    public void setActivityName(String str) {
        this.mActivityName = str;
    }

    public void setGroupId(String str) {
        this.mGroupId = str;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public String getActivityId() {
        return this.mActivityId;
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public String getGroupName() {
        return this.mGroupName;
    }
}
