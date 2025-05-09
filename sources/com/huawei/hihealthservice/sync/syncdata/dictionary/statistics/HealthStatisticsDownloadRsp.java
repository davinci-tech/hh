package com.huawei.hihealthservice.sync.syncdata.dictionary.statistics;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class HealthStatisticsDownloadRsp extends CloudCommonReponse {

    @SerializedName("statisticTotal")
    private Map<String, List<HealthStatistics>> mAllData = new HashMap();

    public Map<String, List<HealthStatistics>> getData() {
        return this.mAllData;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "HealthStatisticsDownloadRsp{mAllData=" + this.mAllData + '}';
    }
}
