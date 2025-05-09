package com.huawei.health.marketing.request;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.marketing.datatype.QueryRecommendResources;

/* loaded from: classes8.dex */
public class RecommendResourceRsp {

    @SerializedName("resultInfo")
    private QueryRecommendResources resultInfo;

    public QueryRecommendResources getResultInfo() {
        return this.resultInfo;
    }

    public void setResultInfo(QueryRecommendResources queryRecommendResources) {
        this.resultInfo = queryRecommendResources;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("resultInfo=");
        sb.append(this.resultInfo);
        return sb.toString();
    }
}
