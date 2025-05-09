package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;

/* loaded from: classes5.dex */
public class EventMonitorRecord extends a {
    public static final String ADD_TIME = "addTime";
    public static final String AD_TYPE = "adType";
    public static final String EVENT_ID = "eventId";

    @e
    private String _id;
    private int adType = -1;
    private long addTime;
    private String eventId;

    public void a(String str) {
        this.eventId = str;
    }

    public void a(long j) {
        this.addTime = j;
    }

    public void a(int i) {
        this.adType = i;
    }

    public String a() {
        return this.eventId;
    }
}
