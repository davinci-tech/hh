package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class GetSportDataByVersionRsp extends CloudCommonReponse {
    private Long currentVersion;
    private Map<String, List<SportDetail>> data;
    private List<SportDetail> detailInfos;

    public Map<String, List<SportDetail>> getData() {
        return this.data;
    }

    public void setData(Map<String, List<SportDetail>> map) {
        this.data = map;
    }

    public List<SportDetail> getDetailInfos() {
        return this.detailInfos;
    }

    public void setDetailInfos(List<SportDetail> list) {
        this.detailInfos = list;
    }

    public Long getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(Long l) {
        this.currentVersion = l;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetSportDataByVersionRsp{data = " + this.data + ", detailInfos=" + this.detailInfos + ", currentVersion=" + this.currentVersion + '}';
    }
}
