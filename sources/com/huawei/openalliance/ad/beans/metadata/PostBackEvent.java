package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes5.dex */
public class PostBackEvent {
    private int adType;
    private String contentId;
    private String eventType;
    private int maxShowRatio;
    private String packageName;
    private String requestId;
    private String showId;
    private long showTimeDuration;
    private String slotId;
    private long time;

    public String i() {
        return this.requestId;
    }

    public long h() {
        return this.time;
    }

    public String g() {
        return this.eventType;
    }

    public void f(String str) {
        this.requestId = str;
    }

    public int f() {
        return this.maxShowRatio;
    }

    public void e(String str) {
        this.eventType = str;
    }

    public long e() {
        return this.showTimeDuration;
    }

    public void d(String str) {
        this.showId = str;
    }

    public String d() {
        return this.showId;
    }

    public void c(String str) {
        this.contentId = str;
    }

    public String c() {
        return this.contentId;
    }

    public void b(String str) {
        this.slotId = str;
    }

    public void b(long j) {
        this.time = j;
    }

    public void b(int i) {
        this.adType = i;
    }

    public String b() {
        return this.slotId;
    }

    public void a(String str) {
        this.packageName = str;
    }

    public void a(long j) {
        this.showTimeDuration = j;
    }

    public void a(int i) {
        this.maxShowRatio = i;
    }

    public String a() {
        return this.packageName;
    }
}
