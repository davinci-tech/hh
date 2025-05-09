package com.huawei.health.vip.datatypes;

/* loaded from: classes4.dex */
public class MemberMessage {
    private long expireTime;
    private long genTime;
    private int infoType;
    private String infoValue;
    private String msgId;
    private long nowTime;
    private int pushType;
    private int visitFlag;

    public String getMsgId() {
        return this.msgId;
    }

    public long getGenTime() {
        return this.genTime;
    }

    public int getPushType() {
        return this.pushType;
    }

    public int getInfoType() {
        return this.infoType;
    }

    public String getInfoValue() {
        return this.infoValue;
    }

    public int getVisitFlag() {
        return this.visitFlag;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setMsgId(String str) {
        this.msgId = str;
    }

    public void setGenTime(long j) {
        this.genTime = j;
    }

    public void setPushType(int i) {
        this.pushType = i;
    }

    public void setInfoType(int i) {
        this.infoType = i;
    }

    public void setInfoValue(String str) {
        this.infoValue = str;
    }

    public void setVisitFlag(int i) {
        this.visitFlag = i;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public String toString() {
        return "MemberMessage{msgId='" + this.msgId + "', genTime=" + this.genTime + ", pushType=" + this.pushType + ", infoType=" + this.infoType + ", infoValue='" + this.infoValue + "', visitFlag=" + this.visitFlag + ", nowTime=" + this.nowTime + ", expireTime=" + this.expireTime + '}';
    }
}
