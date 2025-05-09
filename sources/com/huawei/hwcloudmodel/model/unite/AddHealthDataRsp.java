package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class AddHealthDataRsp extends CloudCommonReponse {
    private boolean hasMore;
    private Long timestamp;

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "AddHealthDataRsp{timestamp=" + this.timestamp + ", hasMore=" + this.hasMore + '}';
    }
}
