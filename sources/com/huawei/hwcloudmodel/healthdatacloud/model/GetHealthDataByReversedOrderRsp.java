package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class GetHealthDataByReversedOrderRsp extends CloudCommonReponse {

    @SerializedName("currentRecordDay")
    private int currentRecordDay;

    @SerializedName("data")
    private Map<String, List<HealthDetail>> data;

    public Map<String, List<HealthDetail>> getDetailInfos() {
        return this.data;
    }

    public void setDetailInfos(Map<String, List<HealthDetail>> map) {
        this.data = map;
    }

    public int getCurrentRecordDay() {
        return this.currentRecordDay;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetHealthDataByReversedOrderRsp{detailInfos=" + this.data + ", currentRecordDay=" + this.currentRecordDay + '}';
    }
}
