package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes7.dex */
public class AddMotionPathRsp extends CloudCommonReponse {

    @SerializedName("hasMore")
    private boolean hasMore;
    private ParamValidDetail paramValidDetail;
    private Long timestamp;

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public ParamValidDetail getParamValidDetail() {
        return this.paramValidDetail;
    }

    public void setParamValidDetail(ParamValidDetail paramValidDetail) {
        this.paramValidDetail = paramValidDetail;
    }

    public boolean isHasMore() {
        return this.hasMore;
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "AddMotionPathRsp{timestamp = " + this.timestamp + " hasMore = " + this.hasMore + " paramValidDetail = " + this.paramValidDetail + '}';
    }
}
