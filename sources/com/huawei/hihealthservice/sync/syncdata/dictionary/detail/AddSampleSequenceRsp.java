package com.huawei.hihealthservice.sync.syncdata.dictionary.detail;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.ParamValidDetail;

/* loaded from: classes7.dex */
public class AddSampleSequenceRsp extends CloudCommonReponse {

    @SerializedName("hasMore")
    private boolean hasMore;

    @SerializedName("paramValidDetail")
    private ParamValidDetail mParamValidDetail;

    @SerializedName("timestamp")
    private long mTimestamp;

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public ParamValidDetail getParamValidDetail() {
        return this.mParamValidDetail;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddSampleSequenceRsp {timestamp = ");
        stringBuffer.append(this.mTimestamp);
        stringBuffer.append(" hasMore = ").append(this.hasMore);
        stringBuffer.append(" paramValidDetail = ").append(this.mParamValidDetail);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
