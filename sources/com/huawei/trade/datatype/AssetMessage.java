package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class AssetMessage {

    @SerializedName("genTime")
    private long genTime;

    @SerializedName("infoType")
    private int infoType;

    @SerializedName("infoValue")
    private String infoValue;

    @SerializedName("msgId")
    private String msgId;

    @SerializedName("pushType")
    private int pushType;

    @SerializedName("visitFlag")
    private boolean visitFlag;

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public long getGenTime() {
        return this.genTime;
    }

    public void setGenTime(long j) {
        this.genTime = j;
    }

    public int getPushType() {
        return this.pushType;
    }

    public void setPushType(int i) {
        this.pushType = i;
    }

    public int getInfoType() {
        return this.infoType;
    }

    public void setInfoType(int i) {
        this.infoType = i;
    }

    public String getInfoValue() {
        return this.infoValue;
    }

    public void setInfoValue(String str) {
        this.infoValue = str;
    }

    public void setVisitFlag(boolean z) {
        this.visitFlag = z;
    }

    public boolean getVisitFlag() {
        return this.visitFlag;
    }
}
