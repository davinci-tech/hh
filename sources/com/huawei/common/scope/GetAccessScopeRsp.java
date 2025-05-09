package com.huawei.common.scope;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class GetAccessScopeRsp {

    @SerializedName("client_id")
    private String appid;

    @SerializedName("scope")
    private String scope;

    @SerializedName("uid")
    private String uid;

    public String getScope() {
        return this.scope;
    }

    public String getUid() {
        return this.uid;
    }
}
