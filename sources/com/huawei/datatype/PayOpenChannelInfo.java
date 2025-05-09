package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class PayOpenChannelInfo {
    private String apdu = "";
    private int channelId;

    public String getApdu() {
        return (String) jdy.d(this.apdu);
    }

    public void setApdu(String str) {
        this.apdu = (String) jdy.d(str);
    }

    public int getChannelId() {
        return ((Integer) jdy.d(Integer.valueOf(this.channelId))).intValue();
    }

    public void setChannelId(int i) {
        this.channelId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
