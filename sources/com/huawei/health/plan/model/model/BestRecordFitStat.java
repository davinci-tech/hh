package com.huawei.health.plan.model.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class BestRecordFitStat {
    private static final String TAG = "Suggestion_BestRecordFitStat";
    private Map<String, String> mBestRecords;
    private long mCompleteTime;
    private String mId;

    public void setValue(String str, int i, String str2, long j) {
        this.mId = str;
        this.mCompleteTime = j;
        if (this.mBestRecords == null) {
            this.mBestRecords = new HashMap(1);
        }
        this.mBestRecords.put(i + "", str2);
    }

    public String acquireId() {
        return this.mId;
    }

    public long acquireCompleteTime() {
        return this.mCompleteTime;
    }

    public Map<String, String> acquireBestRecords() {
        return this.mBestRecords;
    }
}
