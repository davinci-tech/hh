package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.hwcloudmodel.model.unite.SportTotal;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes7.dex */
public class AddSportTotalReq implements IRequest {
    private int isForce;
    private String timeZone;
    private List<SportTotal> totalInfo;

    public List<SportTotal> getTotalInfo() {
        return this.totalInfo;
    }

    public void setTotalInfo(List<SportTotal> list) {
        this.totalInfo = list;
    }

    public void setIsForce(int i) {
        this.isForce = i;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSportTotalReq{totalInfo=");
        stringBuffer.append(this.totalInfo);
        stringBuffer.append(", timeZone='").append(this.timeZone);
        stringBuffer.append(", isForce=").append(this.isForce).append("'}");
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/sport/v2/addTotalSportsData";
    }
}
