package com.huawei.datatype;

import defpackage.jdy;

/* loaded from: classes3.dex */
public class PayApduInfo {
    private String mApdu;
    private int mChannelId;
    private int mReqId;

    public int getReqid() {
        return ((Integer) jdy.d(Integer.valueOf(this.mReqId))).intValue();
    }

    public void setReqid(int i) {
        this.mReqId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getApdu() {
        return (String) jdy.d(this.mApdu);
    }

    public void setApdu(String str) {
        this.mApdu = (String) jdy.d(str);
    }

    public int getChannelId() {
        return ((Integer) jdy.d(Integer.valueOf(this.mChannelId))).intValue();
    }

    public void setChannelId(int i) {
        this.mChannelId = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }
}
