package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class DeleteHealthDataReq implements IRequest {
    private List<DataDeleteCondition> conditions;

    public List<DataDeleteCondition> getDelHealthDataConditons() {
        return this.conditions;
    }

    public void setDelHealthDataConditons(List<DataDeleteCondition> list) {
        this.conditions = list;
    }

    public String toString() {
        return "DeleteHealthDataReq{conditions=" + this.conditions.toString() + "}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/deleteHealthData";
    }
}
