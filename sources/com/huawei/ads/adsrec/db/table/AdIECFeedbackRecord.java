package com.huawei.ads.adsrec.db.table;

import com.huawei.ads.fund.anno.DataKeep;
import com.huawei.ads.fund.db.RecordBean;

@DataKeep
/* loaded from: classes2.dex */
public class AdIECFeedbackRecord extends RecordBean {
    private String contentId;
    private int dislike = 0;
    private long updateTime = System.currentTimeMillis();

    @Override // com.huawei.ads.fund.db.Table
    public long getMaxStoreTime() {
        return 1296000000L;
    }

    @Override // com.huawei.ads.fund.db.Table
    public String getExpireCleanWhereClause() {
        return "updateTime<?";
    }

    public void c(String str) {
        this.contentId = str;
    }

    public void e(long j) {
        this.updateTime = j;
    }

    public void a(int i) {
        this.dislike = i;
    }
}
