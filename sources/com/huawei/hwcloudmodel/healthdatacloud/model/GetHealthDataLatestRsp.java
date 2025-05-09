package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import java.util.List;

/* loaded from: classes7.dex */
public class GetHealthDataLatestRsp extends CloudCommonReponse {
    private List<HealthDetail> detailInfos;

    public List<HealthDetail> getDetailInfos() {
        return this.detailInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetHealthDataLatestRsp{detailInfos = ");
        stringBuffer.append(this.detailInfos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
