package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetSleepSportsDataLatestReq implements IRequest {
    private Integer counts;
    private int dataType;
    private Integer days;
    private long timestamp;

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setDays(int i) {
        this.days = Integer.valueOf(i);
    }

    public void setCounts(int i) {
        this.counts = Integer.valueOf(i);
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/sport/getSleepSportsDataLatest";
    }
}
