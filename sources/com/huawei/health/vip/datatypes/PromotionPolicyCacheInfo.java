package com.huawei.health.vip.datatypes;

import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.db.bean.ContentRecord;

/* loaded from: classes4.dex */
public class PromotionPolicyCacheInfo {

    @SerializedName("lastPolicyShowCount")
    private int lastPolicyShowCount;

    @SerializedName(ContentRecord.LAST_SHOW_TIME)
    private long lastShowTime;

    @SerializedName("policyId")
    private String policyId;

    public long getLastShowTime() {
        return this.lastShowTime;
    }

    public void setLastShowTime(long j) {
        this.lastShowTime = j;
    }

    public String getPolicyId() {
        return this.policyId;
    }

    public void setPolicyId(String str) {
        this.policyId = str;
    }

    public int getLastPolicyShowCount() {
        return this.lastPolicyShowCount;
    }

    public void setLastPolicyShowCount(int i) {
        this.lastPolicyShowCount = i;
    }
}
