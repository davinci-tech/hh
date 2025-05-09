package com.huawei.health.vip.vipinfo;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.vip.datatypes.MemberMessage;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import java.util.List;

/* loaded from: classes4.dex */
public class VipMessageListRsp extends CloudCommonReponse {

    @SerializedName("messages")
    private List<MemberMessage> messages;

    public List<MemberMessage> getMessages() {
        return this.messages;
    }
}
