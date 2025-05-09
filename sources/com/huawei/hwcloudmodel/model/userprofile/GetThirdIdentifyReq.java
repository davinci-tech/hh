package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class GetThirdIdentifyReq {

    @SerializedName("lastUpdateTime")
    private Long lastUpdateTime;

    public Long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Long l) {
        this.lastUpdateTime = l;
    }

    public String toString() {
        return "GetThirdIdentifyReq{lastUpdateTime=" + this.lastUpdateTime + '}';
    }
}
