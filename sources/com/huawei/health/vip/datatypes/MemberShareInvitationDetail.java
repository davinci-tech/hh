package com.huawei.health.vip.datatypes;

/* loaded from: classes8.dex */
public class MemberShareInvitationDetail {
    private String adminHuid;
    private String adminNickname;
    private String adminUserAccountAnon;
    private long createTime;
    private long expireTime;
    private long modifyTime;
    private String sharedInfoUniqueId;
    private int status;

    public String getSharedInfoUniqueId() {
        return this.sharedInfoUniqueId;
    }

    public void setSharedInfoUniqueId(String str) {
        this.sharedInfoUniqueId = str;
    }

    public String getAdminHuid() {
        return this.adminHuid;
    }

    public void setAdminHuid(String str) {
        this.adminHuid = str;
    }

    public String getAdminUserAccountAnon() {
        return this.adminUserAccountAnon;
    }

    public void setAdminUserAccountAnon(String str) {
        this.adminUserAccountAnon = str;
    }

    public String getAdminNickname() {
        return this.adminNickname;
    }

    public void setAdminNickname(String str) {
        this.adminNickname = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(long j) {
        this.modifyTime = j;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("sharedInfoUniqueId=");
        sb.append(this.sharedInfoUniqueId);
        sb.append(" adminHuid=");
        sb.append(this.adminHuid);
        sb.append(" adminUserAccountAnon=");
        sb.append(this.adminUserAccountAnon);
        sb.append(" adminNickname=");
        sb.append(this.adminNickname);
        sb.append(" status=");
        sb.append(this.status);
        sb.append(" status=");
        sb.append(this.createTime);
        sb.append(" createTime=");
        sb.append(this.modifyTime);
        sb.append(" modifyTime=");
        sb.append(this.status);
        sb.append(" expireTime=");
        sb.append(this.expireTime);
        return sb.toString();
    }
}
