package com.huawei.health.messagecenter.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class MessagePushBean {

    @SerializedName("pushId")
    private long pushId;

    @SerializedName("pushType")
    private int pushType = 0;

    @SerializedName(CommonUtil.MSG_CONTENT)
    private String msgContext = "";

    public int getPushType() {
        return this.pushType;
    }

    public String getMsgContext() {
        return this.msgContext;
    }

    public long getPushId() {
        return this.pushId;
    }

    public void setMsgContext(String str) {
        this.msgContext = str;
    }

    public void setPushId(long j) {
        this.pushId = j;
    }

    public void setPushType(int i) {
        this.pushType = i;
    }
}
