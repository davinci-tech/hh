package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class AchieveLevelEventInfo extends mcz {
    private long lastModifyTime;
    private int rewardExperience;
    private long syncTime;
    private int taskCount;
    private String taskId;
    private int taskSumCount;

    public AchieveLevelEventInfo() {
        super(16);
    }

    public String acquireTaskId() {
        return this.taskId;
    }

    public void saveTaskId(String str) {
        this.taskId = str;
    }

    public int acquireRewardExperience() {
        return this.rewardExperience;
    }

    public void saveRewardExperience(int i) {
        this.rewardExperience = i;
    }

    public int acquireTaskCount() {
        return this.taskCount;
    }

    public void saveTaskCount(int i) {
        this.taskCount = i;
    }

    public long acquireLastModifyTime() {
        return this.lastModifyTime;
    }

    public void saveLastModifyTime(long j) {
        this.lastModifyTime = j;
    }

    public int acquireTaskSumCount() {
        return this.taskSumCount;
    }

    public void saveTaskSumCount(int i) {
        this.taskSumCount = i;
    }

    public long acquireSyncTime() {
        return this.syncTime;
    }

    public void saveSyncTime(long j) {
        this.syncTime = j;
    }

    public String toString() {
        return "AchieveLevelEventInfo{taskId='" + this.taskId + "', rewardExperience=" + this.rewardExperience + ", taskCount=" + this.taskCount + ", lastModifyTime=" + this.lastModifyTime + ", taskSumCount=" + this.taskSumCount + ", syncTime=" + this.syncTime + '}';
    }
}
