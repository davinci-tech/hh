package com.huawei.health.vip.datatypes;

/* loaded from: classes8.dex */
public class TransferInfo {
    private long createTime;
    private long expirationTime;
    private long nowTime;
    private int status;
    private String transferId;

    public String getTransferId() {
        return this.transferId;
    }

    public void setTransferId(String str) {
        this.transferId = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(long j) {
        this.expirationTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public String toString() {
        return "TransferInfo{transferId='" + this.transferId + "', status=" + this.status + ", expirationTime=" + this.expirationTime + ", createTime=" + this.createTime + ", nowTime=" + this.nowTime + '}';
    }
}
