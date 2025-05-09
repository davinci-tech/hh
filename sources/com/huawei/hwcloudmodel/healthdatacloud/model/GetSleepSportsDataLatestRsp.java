package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.SportDetail;
import java.util.List;

/* loaded from: classes7.dex */
public class GetSleepSportsDataLatestRsp extends CloudCommonReponse {
    private List<SportDetail> detailInfos;

    public List<SportDetail> getDetailInfos() {
        return this.detailInfos;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetSleepSportsDataLatestRsp{detailInfos = ");
        stringBuffer.append(this.detailInfos);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
