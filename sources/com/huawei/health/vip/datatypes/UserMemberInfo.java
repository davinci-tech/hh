package com.huawei.health.vip.datatypes;

import java.util.Objects;

/* loaded from: classes4.dex */
public class UserMemberInfo {
    private int autoRenew;
    private int autoRenewType;
    private long expireTime;
    private int memberFlag;
    private int memberType;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserMemberInfo userMemberInfo = (UserMemberInfo) obj;
        return this.memberFlag == userMemberInfo.memberFlag && this.memberType == userMemberInfo.memberType && this.autoRenew == userMemberInfo.autoRenew && this.autoRenewType == userMemberInfo.autoRenewType && this.nowTime == userMemberInfo.nowTime && this.expireTime == userMemberInfo.expireTime;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.memberFlag), Integer.valueOf(this.memberType), Integer.valueOf(this.autoRenew), Integer.valueOf(this.autoRenewType), Long.valueOf(this.expireTime));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("memberFlag=");
        sb.append(this.memberFlag);
        sb.append(" memberType=");
        sb.append(this.memberType);
        sb.append(" autoRenew=");
        sb.append(this.autoRenew);
        sb.append(" expireTime=");
        sb.append(this.expireTime);
        sb.append(" nowTime=");
        sb.append(this.nowTime);
        return sb.toString();
    }
}
