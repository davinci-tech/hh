package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes5.dex */
public class DeleteOperationRecord {

    @SerializedName("appType")
    private int appType;

    @SerializedName("operateTime")
    private long operateTime;

    @SerializedName(ParsedFieldTag.KAKA_TASK_SCENARIO)
    private int scenario;

    @SerializedName("status")
    private String status;

    public int getScenario() {
        return this.scenario;
    }

    public void setScenario(int i) {
        this.scenario = i;
    }

    public long getOperateTime() {
        return this.operateTime;
    }

    public void setOperateTime(long j) {
        this.operateTime = j;
    }

    public int getAppType() {
        return this.appType;
    }

    public void setAppType(int i) {
        this.appType = i;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "DeleteOperationRecord{scenario=" + this.scenario + ", operateTime=" + this.operateTime + ", appType=" + this.appType + ", status=" + this.status + '}';
    }
}
