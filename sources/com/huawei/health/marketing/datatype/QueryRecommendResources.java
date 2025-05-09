package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes8.dex */
public class QueryRecommendResources {

    @SerializedName("recommendResourceInfos")
    private List<RecommendResourceInfo> recommendResourceInfos;

    @SerializedName("totalNum")
    private int totalNum;

    public int getTotalNum() {
        return this.totalNum;
    }

    public void setTotalNum(int i) {
        this.totalNum = i;
    }

    public List<RecommendResourceInfo> getRecommendResourceInfos() {
        return this.recommendResourceInfos;
    }

    public void setRecommendResourceInfos(List<RecommendResourceInfo> list) {
        this.recommendResourceInfos = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("totalNum=");
        sb.append(this.totalNum);
        sb.append(" recommendResourceInfos=");
        sb.append(this.recommendResourceInfos);
        return sb.toString();
    }
}
