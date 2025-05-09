package com.huawei.hwcloudmodel.model.unite;

import java.util.List;

/* loaded from: classes5.dex */
public class DeleteSportDataReq {
    private List<DataDeleteCondition> conditions;

    public List<DataDeleteCondition> getDeleteSportDataConditons() {
        return this.conditions;
    }

    public void setDeleteSportDataConditons(List<DataDeleteCondition> list) {
        this.conditions = list;
    }

    public String toString() {
        return "DeleteSportDataReq{delConditons=" + this.conditions.toString() + '}';
    }
}
