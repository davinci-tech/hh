package com.huawei.featureuserprofile.todo.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class HistoriesDetailsBean {

    @SerializedName("activityDate")
    private String activityDate;

    @SerializedName("completeFlag")
    private int completeFlag;

    public String getActivityDate() {
        return this.activityDate;
    }

    public void setActivityDate(String str) {
        this.activityDate = str;
    }

    public int getCompleteFlag() {
        return this.completeFlag;
    }

    public void setCompleteFlag(int i) {
        this.completeFlag = i;
    }

    public String toString() {
        return "HistoriesDetailsBean{activityDate='" + this.activityDate + "', completeFlag=" + this.completeFlag + '}';
    }
}
