package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;

@DataKeep
/* loaded from: classes2.dex */
public class AdEventRecord extends RecordBean {
    private String clientRequestId;
    private String contentId;
    private long eventTime = System.currentTimeMillis();
    private String eventType;
    private int maxShowRatio;
    private String pkgName;
    private long showDuration;
    private String showId;
    private String slotId;

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 7776000000L;
    }

    public String toString() {
        return "AdEventRecord{pkgName='" + this.pkgName + "', slotId='" + this.slotId + "', contentId='" + this.contentId + "', showId='" + this.showId + "', showDuration=" + this.showDuration + ", maxShowRatio=" + this.maxShowRatio + ", eventType='" + this.eventType + "', eventTime=" + this.eventTime + ", clientRequestId=" + this.clientRequestId + '}';
    }

    public String g() {
        return this.slotId;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "eventTime<?";
    }

    public String f() {
        return this.showId;
    }

    public void h(String str) {
        this.slotId = str;
    }

    public long h() {
        return this.showDuration;
    }

    public void e(String str) {
        this.showId = str;
    }

    public String e() {
        return this.pkgName;
    }

    public void b(String str) {
        this.pkgName = str;
    }

    public String b() {
        return this.eventType;
    }

    public void c(String str) {
        this.eventType = str;
    }

    public long a() {
        return this.eventTime;
    }

    public void d(String str) {
        this.contentId = str;
    }

    public void a(long j) {
        this.showDuration = j;
    }

    public String d() {
        return this.contentId;
    }

    public void a(String str) {
        this.clientRequestId = str;
    }

    public void d(long j) {
        this.eventTime = j;
    }

    public void e(int i) {
        this.maxShowRatio = i;
    }

    public String c() {
        return this.clientRequestId;
    }
}
