package com.huawei.hwcloudjs.service.jsapi.network;

import com.huawei.hms.network.embedded.r3;
import com.huawei.hwcloudjs.annotation.JSField;
import com.huawei.hwcloudjs.annotation.JSNativeMsg;
import com.huawei.hwcloudjs.service.jsmsg.NativeMsg;

@JSNativeMsg(isOpen = true, type = "onNetworkStatusChange")
/* loaded from: classes9.dex */
public class NetworkChangeMsg extends NativeMsg {

    @JSField(r3.y)
    private String networkType;

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public String getNetworkType() {
        return this.networkType;
    }
}
