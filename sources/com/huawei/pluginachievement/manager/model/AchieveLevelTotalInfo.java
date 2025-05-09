package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class AchieveLevelTotalInfo extends mcz {
    private int level;
    private long syncTime;
    private int userNumber;

    public AchieveLevelTotalInfo() {
        super(15);
    }

    public int acquireLevel() {
        return this.level;
    }

    public void saveLevel(int i) {
        this.level = i;
    }

    public int acquireUserNumber() {
        return this.userNumber;
    }

    public void saveUserNumber(int i) {
        this.userNumber = i;
    }

    public long acquireSyncTime() {
        return this.syncTime;
    }

    public void saveSyncTime(long j) {
        this.syncTime = j;
    }

    public String toString() {
        return "AchieveLevelTotalInfo{level=" + this.level + ", userNumber=" + this.userNumber + ", syncTime=" + this.syncTime + '}';
    }
}
