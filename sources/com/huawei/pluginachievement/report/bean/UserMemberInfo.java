package com.huawei.pluginachievement.report.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes6.dex */
public class UserMemberInfo {

    @SerializedName("autoRenew")
    private int autoRenew;

    @SerializedName("autoRenewType")
    private int autoRenewType;

    @SerializedName("createTime")
    private long createTime;

    @SerializedName("expireTime")
    private long expireTime;

    @SerializedName("memberFlag")
    private int memberFlag;

    @SerializedName("memberType")
    private int memberType;

    @SerializedName("nowTime")
    private long nowTime;

    public int getMemberFlag() {
        return this.memberFlag;
    }

    public void setMemberFlag(int i) {
        this.memberFlag = i;
    }

    public int getMemberType() {
        return this.memberType;
    }

    public void setMemberType(int i) {
        this.memberType = i;
    }

    public int getAutoRenew() {
        return this.autoRenew;
    }

    public void setAutoRenew(int i) {
        this.autoRenew = i;
    }

    public int getAutoRenewType() {
        return this.autoRenewType;
    }

    public void setAutoRenewType(int i) {
        this.autoRenewType = i;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }
}
