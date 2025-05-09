package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes9.dex */
public class GetThirdIdentifyRsp extends CloudCommonReponse {

    @SerializedName("blackList")
    private String blackList;

    @SerializedName("whiteList")
    private String whiteList;

    public String getBlackList() {
        return this.blackList;
    }

    public void setBlackList(String str) {
        this.blackList = str;
    }

    public String getWhiteList() {
        return this.whiteList;
    }

    public void setWhiteList(String str) {
        this.whiteList = str;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "GetThirdIdentifyRsp{blackList='" + this.blackList + "', whiteList='" + this.whiteList + "'}";
    }
}
