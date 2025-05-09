package com.huawei.openalliance.ad.db.bean;

import com.huawei.openalliance.ad.annotations.e;

/* loaded from: classes5.dex */
public class ThirdPartyEventRecord extends a {
    public static final String AD_TYPE = "adType";
    public static final String LAST_REPORT_TIME = "lastReportTime";
    public static final String LOCK_TIME = "lockTime";
    public static final String REQUEST_ID = "requestId";
    public static final String TIME = "time";

    @e
    private String _id;
    private int adType;
    private String contentId;
    private String eventType;
    private long lastReportTime;
    private long lockTime;
    private String requestId;
    private int requestType;
    private String slotId;
    private long startShowTime;
    private long time;
    private EncryptionField<String> url;

    public long i() {
        return this.startShowTime;
    }

    public int h() {
        return this.requestType;
    }

    public String g() {
        return this.eventType;
    }

    public String f() {
        return this.contentId;
    }

    public void e(String str) {
        this.eventType = str;
    }

    public String e() {
        return this.slotId;
    }

    public void d(String str) {
        this.contentId = str;
    }

    public String d() {
        return this.requestId;
    }

    public void c(String str) {
        this.slotId = str;
    }

    public int c() {
        return this.adType;
    }

    public final void b(String str) {
        this.requestId = str;
    }

    public void b(long j) {
        this.startShowTime = j;
    }

    public void b(int i) {
        this.requestType = i;
    }

    public EncryptionField<String> b() {
        return this.url;
    }

    public final void a(String str) {
        if (this.url == null) {
            this.url = new EncryptionField<>(String.class);
        }
        this.url.a((EncryptionField<String>) str);
    }

    public void a(long j) {
        this.lockTime = j;
    }

    public void a(int i) {
        this.adType = i;
    }

    public String a() {
        return this._id;
    }

    public ThirdPartyEventRecord(int i, String str, String str2) {
        this();
        this.adType = i;
        a(str);
        b(str2);
        this.time = System.currentTimeMillis();
        this.lastReportTime = System.currentTimeMillis();
    }

    public ThirdPartyEventRecord() {
        this.lockTime = 0L;
        this.requestType = 0;
        this.startShowTime = -111111L;
    }
}
