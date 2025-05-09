package com.huawei.health.vip.vipinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.MemberMessage;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes4.dex */
public class VipMessageRsp extends CloudCommonReponse {

    @SerializedName("message")
    private MemberMessage message;

    public MemberMessage getMessage() {
        return this.message;
    }

    public void setMessage(MemberMessage memberMessage) {
        this.message = memberMessage;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "VipMessageRsp{message=" + this.message + '}';
    }
}
