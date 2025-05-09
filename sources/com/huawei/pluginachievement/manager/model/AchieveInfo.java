package com.huawei.pluginachievement.manager.model;

import defpackage.mcz;

/* loaded from: classes6.dex */
public class AchieveInfo extends mcz {
    private int expirationKaka;
    private String medals;
    private long syncTimestamp;
    private int userLevel;
    private int userPoint;
    private double userReachStandardDays;

    public AchieveInfo() {
        super(5);
        this.userLevel = 1;
        this.medals = "";
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(int i) {
        this.userLevel = i;
    }

    public int getUserPoint() {
        return this.userPoint;
    }

    public void setUserPoint(int i) {
        this.userPoint = i;
    }

    public String acquireMedals() {
        return this.medals;
    }

    public void saveMedals(String str) {
        this.medals = str;
    }

    public double getUserReachStandardDays() {
        return this.userReachStandardDays;
    }

    public void setUserReachStandardDays(double d) {
        this.userReachStandardDays = d;
    }

    public long getSyncTimestamp() {
        return this.syncTimestamp;
    }

    public void setSyncTimestamp(long j) {
        this.syncTimestamp = j;
    }

    public int getExpirationKaka() {
        return this.expirationKaka;
    }

    public void setExpirationKaka(int i) {
        this.expirationKaka = i;
    }

    public String toString() {
        return "AchieveInfo{huid='" + super.getHuid() + "', userLevel=" + this.userLevel + ", userPoint=" + this.userPoint + ", medals='" + this.medals + "', userReachingStandardDays=" + this.userReachStandardDays + ", syncTimestamp=" + this.syncTimestamp + '}';
    }
}
