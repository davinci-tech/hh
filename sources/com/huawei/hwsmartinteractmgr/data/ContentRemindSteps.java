package com.huawei.hwsmartinteractmgr.data;

/* loaded from: classes5.dex */
public class ContentRemindSteps {
    private String activityId;
    private String activityName;
    private int activityStatus;
    private String lastModifyTime;
    private int stepsStandard;

    public ContentRemindSteps(String str, int i, String str2, int i2, String str3) {
        this.activityName = str;
        this.stepsStandard = i;
        this.activityId = str2;
        this.activityStatus = i2;
        this.lastModifyTime = str3;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public int getRemainSteps() {
        return this.stepsStandard;
    }

    public void setRemainSteps(int i) {
        this.stepsStandard = i;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }

    public int getActivityStatus() {
        return this.activityStatus;
    }

    public void setActivityStatus(int i) {
        this.activityStatus = i;
    }

    public String getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(String str) {
        this.lastModifyTime = str;
    }

    public String toString() {
        return "ContentRemindSteps{activityName='" + this.activityName + "', stepsStandard=" + this.stepsStandard + ", activityId='" + this.activityId + "', activityStatus=" + this.activityStatus + ", lastModifyTime=" + this.lastModifyTime + '}';
    }
}
