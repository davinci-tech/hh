package com.huawei.health.featuremarketing.rules.activityinfo;

/* loaded from: classes3.dex */
public class UserActivityInfo {
    private final String activityId;
    private final int joinStatus;
    private final long joinTime;
    private final int level;

    public UserActivityInfo(e eVar) {
        this.activityId = eVar.b;
        this.level = eVar.d;
        this.joinStatus = eVar.e;
        this.joinTime = eVar.c;
    }

    /* loaded from: classes8.dex */
    public static class e {
        private String b;
        private long c;
        private int d;
        private int e;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public int getLevel() {
        return this.level;
    }

    public int getJoinStatus() {
        return this.joinStatus;
    }

    public long getJoinTime() {
        return this.joinTime;
    }
}
