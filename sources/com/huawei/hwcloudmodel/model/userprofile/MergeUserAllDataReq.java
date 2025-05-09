package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class MergeUserAllDataReq {

    @SerializedName("originalHuid")
    private String originalHuid;

    @SerializedName("originalST")
    private String originalST;

    public String getOriginalHuid() {
        return this.originalHuid;
    }

    public void setOriginalHuid(String str) {
        this.originalHuid = str;
    }

    public String getOriginalServiceToken() {
        return this.originalST;
    }

    public void setOriginalServiceToken(String str) {
        this.originalST = str;
    }

    public String toString() {
        return "MergeUserAllDataReq{originalHuid=" + this.originalHuid + ", originalST=" + this.originalST + '}';
    }
}
