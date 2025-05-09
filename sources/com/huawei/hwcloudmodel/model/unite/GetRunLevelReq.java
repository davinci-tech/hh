package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.Set;

/* loaded from: classes5.dex */
public class GetRunLevelReq implements IRequest {

    @SerializedName("endDay")
    private int endDay;

    @SerializedName("queryType")
    private int queryType;

    @SerializedName("startDay")
    private int startDay;

    @SerializedName("types")
    private Set<Integer> types;

    public int getStartDay() {
        return this.startDay;
    }

    public void setStartDay(int i) {
        this.startDay = i;
    }

    public int getEndDay() {
        return this.endDay;
    }

    public void setEndDay(int i) {
        this.endDay = i;
    }

    public int getQueryType() {
        return this.queryType;
    }

    public void setQueryType(int i) {
        this.queryType = i;
    }

    public Set<Integer> getTypes() {
        return this.types;
    }

    public void setTypes(Set<Integer> set) {
        this.types = set;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("GetRunLevelReq{startDay=");
        sb.append(this.startDay);
        sb.append(", endDay=");
        sb.append(this.endDay);
        sb.append(", queryType=");
        sb.append(this.queryType);
        sb.append(", types=");
        sb.append(this.types);
        sb.append('}');
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getRunLevel";
    }
}
