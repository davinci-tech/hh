package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class UserMergeInfo {

    @SerializedName("originalHuid")
    private String originalHuid;

    @SerializedName("status")
    private Integer status;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer num) {
        this.status = num;
    }

    public String getOriginalHuid() {
        return this.originalHuid;
    }

    public void setOriginalHuid(String str) {
        this.originalHuid = str;
    }

    public String toString() {
        return "UserMergeInfo{originalHuid=" + this.originalHuid + ", status=" + this.status + '}';
    }
}
