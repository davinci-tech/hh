package com.huawei.health.vip.vipinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes4.dex */
public class VipInfoRsp extends CloudCommonReponse {

    @SerializedName("memberInfo")
    private UserMemberInfo memberInfo;

    public UserMemberInfo getUserMemberInfo() {
        return this.memberInfo;
    }

    public void setUserMemberInfo(UserMemberInfo userMemberInfo) {
        this.memberInfo = userMemberInfo;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("memberInfo=");
        sb.append(this.memberInfo);
        return sb.toString();
    }
}
