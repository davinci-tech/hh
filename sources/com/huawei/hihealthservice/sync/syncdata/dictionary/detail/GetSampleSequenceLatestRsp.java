package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import com.huawei.hwcloudmodel.healthdatacloud.model.SampleSequence;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetSampleSequenceLatestRsp extends CloudCommonReponse {
    private List<SampleSequence> detailInfos;

    public List<SampleSequence> getDetailInfos() {
        return this.detailInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSampleSequenceLatestRsp{detailInfos = ");
        stringBuffer.append(this.detailInfos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
