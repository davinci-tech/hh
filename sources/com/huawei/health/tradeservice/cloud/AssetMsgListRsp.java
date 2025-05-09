package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.trade.datatype.AssetMessage;
import java.util.List;

/* loaded from: classes4.dex */
public class AssetMsgListRsp extends BaseRsp {

    @SerializedName("messages")
    List<AssetMessage> messages;

    public List<AssetMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(List<AssetMessage> list) {
        this.messages = list;
    }
}
