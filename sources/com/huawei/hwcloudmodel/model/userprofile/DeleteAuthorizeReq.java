package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class DeleteAuthorizeReq {

    @SerializedName("thirdAccountType")
    private Integer thirdAccountType;

    public Integer getThirdAccountType() {
        return this.thirdAccountType;
    }

    public void setThirdAccountType(Integer num) {
        this.thirdAccountType = num;
    }

    public String toString() {
        return "DelAuthorizeReq{thirdAccountType=" + this.thirdAccountType + '}';
    }
}
