package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.SportDetail;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class AddSportDataReq implements IRequest {
    private List<SportDetail> detailInfo;
    private String timeZone;

    public void setDetailInfo(List<SportDetail> list) {
        this.detailInfo = list;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        return "AddSportDataReq{detailInfo=" + this.detailInfo + ", timeZone='" + this.timeZone + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/sport/addSportsData";
    }
}
