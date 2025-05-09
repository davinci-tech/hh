package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes.dex */
public class AddHealthDataReq implements IRequest {
    private List<HealthDetail> detailInfo;
    private Long localMaxVersion;
    private String timeZone;

    public void setDetailInfo(List<HealthDetail> list) {
        this.detailInfo = list;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public void setLocalMaxVersion(Long l) {
        this.localMaxVersion = l;
    }

    public Long getLocalMaxVersion() {
        return this.localMaxVersion;
    }

    public String toString() {
        return "AddHealthDataReq{detailInfo=" + this.detailInfo + "localMaxVersion=" + this.localMaxVersion + ", timeZone='" + this.timeZone + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/addHealthData";
    }
}
