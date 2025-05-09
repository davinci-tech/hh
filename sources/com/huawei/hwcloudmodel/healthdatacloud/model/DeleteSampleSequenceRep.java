package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.unite.DataDeleteCondition;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class DeleteSampleSequenceRep implements IRequest {

    @SerializedName("conditions")
    private List<DataDeleteCondition> mConditions;

    public List<DataDeleteCondition> getDeleteSampleSequenceConditions() {
        return this.mConditions;
    }

    public void setDeleteSampleSequenceConditons(List<DataDeleteCondition> list) {
        this.mConditions = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeleteSampleSequenceRep { mConditions = ");
        stringBuffer.append(this.mConditions.toString());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/sequence/deleteSampleSequence";
    }
}
