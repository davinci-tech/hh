package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.unite.DataTimeDelCondition;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class DeleteDataByTimeReq implements IRequest {

    @SerializedName("conditions")
    private List<DataTimeDelCondition> conditions;

    public List<DataTimeDelCondition> getDelDayConditons() {
        return this.conditions;
    }

    public void setDelDayDataConditons(List<DataTimeDelCondition> list) {
        this.conditions = list;
    }

    public String toString() {
        return "DeleteDayDataReq{conditions=" + this.conditions.toString() + "}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/deleteHealthDataByTime";
    }
}
