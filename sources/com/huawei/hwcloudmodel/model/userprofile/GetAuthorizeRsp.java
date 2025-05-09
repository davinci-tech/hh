package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.ThirdUserToken;

/* loaded from: classes9.dex */
public class GetAuthorizeRsp extends CloudCommonReponse {

    @SerializedName("thirdUserToken")
    private ThirdUserToken thirdUserToken;

    public ThirdUserToken getThirdUserToken() {
        return this.thirdUserToken;
    }

    public void setThirdUserToken(ThirdUserToken thirdUserToken) {
        this.thirdUserToken = thirdUserToken;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetAuthorizeRsp{thirdUserToken=" + this.thirdUserToken + '}';
    }
}
