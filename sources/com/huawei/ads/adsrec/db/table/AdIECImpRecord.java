package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;

@DataKeep
/* loaded from: classes2.dex */
public class AdIECImpRecord extends RecordBean {
    private String contentId;
    private long lastRecallTime;
    private long lastShowTime;
    private String pkgName;
    private int showCount;
    private long updateTime = System.currentTimeMillis();

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 1296000000L;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "updateTime<?";
    }

    public int d() {
        return this.showCount;
    }

    public void e(String str) {
        this.pkgName = str;
    }

    public void b(long j) {
        this.updateTime = j;
    }

    public long a() {
        return this.lastRecallTime;
    }

    public void b(String str) {
        this.contentId = str;
    }

    public void c(long j) {
        this.lastShowTime = j;
    }

    public void a(int i) {
        this.showCount = i;
    }

    public String c() {
        return this.contentId;
    }
}
