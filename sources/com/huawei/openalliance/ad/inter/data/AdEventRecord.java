package com.huawei.openalliance.ad.inter.data;

/* loaded from: classes9.dex */
public class AdEventRecord {
    private IAd ad;
    private long endTime;
    private Long eventTime;
    private String eventType;
    private long startTime;

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setEventTime(Long l) {
        this.eventTime = l;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setAd(IAd iAd) {
        this.ad = iAd;
    }

    public String d() {
        return this.eventType;
    }

    public long c() {
        return this.endTime;
    }

    public long b() {
        return this.startTime;
    }

    public IAd a() {
        return this.ad;
    }

    public AdEventRecord(a aVar, long j, long j2, String str, Long l) {
        this.ad = aVar;
        this.startTime = j;
        this.endTime = j2;
        this.eventType = str;
        this.eventTime = l;
    }

    public AdEventRecord() {
    }
}
