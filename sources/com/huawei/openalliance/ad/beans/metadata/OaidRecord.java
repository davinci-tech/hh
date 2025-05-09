package com.huawei.openalliance.ad.beans.metadata;

/* loaded from: classes9.dex */
public class OaidRecord {
    public static final String LIMIT_OAID_CLOSE_KEY = "limit_oaid_close";
    public static final String LIMIT_OAID_OPEN_KEY = "limit_oaid_open";
    public static final String OPEN_OAID_SETTING_KEY = "open_oaid_setting";
    public static final String RESET_OAID_KEY = "reset_oaid";
    private int readTimes = 0;
    private long lastReportTime = 0;

    public void c() {
        this.readTimes++;
    }

    public long b() {
        return this.lastReportTime;
    }

    public void a(long j) {
        this.lastReportTime = j;
    }

    public void a(int i) {
        this.readTimes = i;
    }

    public int a() {
        return this.readTimes;
    }
}
