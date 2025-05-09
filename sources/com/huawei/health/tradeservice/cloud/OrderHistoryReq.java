package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderHistoryReq implements IRequest {

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("language")
    private String language;

    @SerializedName(BleConstants.LIMIT)
    private long limit;

    @SerializedName("startTime")
    private long startTime;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/history?";
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public long getLimit() {
        return this.limit;
    }

    public void setLimit(long j) {
        this.limit = j;
    }
}
