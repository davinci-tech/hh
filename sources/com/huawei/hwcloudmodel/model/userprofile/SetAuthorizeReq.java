package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.ThirdUserToken;

/* loaded from: classes5.dex */
public class SetAuthorizeReq {

    @SerializedName("thirdUserToken")
    private ThirdUserToken thirdUserToken;

    public ThirdUserToken getThirdUserToken() {
        return this.thirdUserToken;
    }

    public void setThirdUserToken(ThirdUserToken thirdUserToken) {
        this.thirdUserToken = thirdUserToken;
    }

    public String toString() {
        return "SetAuthorizeReq{thirdUserToken=" + this.thirdUserToken + '}';
    }
}
