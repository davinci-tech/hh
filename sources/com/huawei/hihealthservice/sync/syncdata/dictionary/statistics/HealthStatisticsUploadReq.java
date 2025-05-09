package com.huawei.hihealthservice.sync.syncdata.dictionary.statistics;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HealthStatisticsUploadReq implements IRequest {

    @SerializedName("statisticTotal")
    private Map<String, List<HealthStatistics>> statisticTotal;

    @SerializedName("timeZone")
    private String timeZone;

    public HealthStatisticsUploadReq(Map<String, List<HealthStatistics>> map, String str) {
        new HashMap();
        this.statisticTotal = map;
        this.timeZone = str;
    }

    public String toString() {
        return "HealthStatisticsUploadReq{mStatisticTotal=" + this.statisticTotal + ", mTimeZone='" + this.timeZone + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/addHealthStatistics";
    }
}
