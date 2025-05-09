package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes7.dex */
public class GetHealthDataByVersionRsp extends CloudCommonReponse {
    private long currentVersion;
    private List<HealthDetail> detailInfos;

    public List<HealthDetail> getDetailInfos() {
        return this.detailInfos;
    }

    public void setDetailInfos(List<HealthDetail> list) {
        this.detailInfos = list;
    }

    public long getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(long j) {
        this.currentVersion = j;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetHealthDataByVersionRsp{detailInfos=");
        stringBuffer.append(this.detailInfos);
        stringBuffer.append(", currentVersion=").append(this.currentVersion);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
