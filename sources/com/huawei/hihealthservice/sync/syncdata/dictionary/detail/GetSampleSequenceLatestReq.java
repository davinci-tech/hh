package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetSampleSequenceLatestReq implements IRequest {
    private Integer counts;
    private int dataType;
    private Integer days;
    private long timestamp;
    private int type;

    public void setType(int i) {
        this.type = i;
    }

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
        return Utils.e() + "/dataQuery/sequence/getSampleSequenceLatest";
    }
}
