package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes9.dex */
public class GetSampleSequenceByTimeRsp extends CloudCommonReponse {

    @SerializedName("detailInfos")
    private List<SampleSequence> mDetailInfos;

    public List<SampleSequence> getDetailInfos() {
        return this.mDetailInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSampleSequenceByTimeRsp{detailInfos = ");
        stringBuffer.append(this.mDetailInfos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
