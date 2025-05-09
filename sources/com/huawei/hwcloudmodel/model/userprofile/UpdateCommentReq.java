package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class UpdateCommentReq {

    @SerializedName("xVersion")
    private String xVersion;

    public String obtainDescription() {
        return this.xVersion;
    }

    public void putDescription(String str) {
        this.xVersion = str;
    }

    public String toString() {
        return "UpdateCommentReq{xVersion='" + this.xVersion + "'}";
    }
}
