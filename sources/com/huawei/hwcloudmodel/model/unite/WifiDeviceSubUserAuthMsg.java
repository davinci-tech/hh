package com.huawei.hwcloudmodel.model.unite;

import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class WifiDeviceSubUserAuthMsg implements Comparable<WifiDeviceSubUserAuthMsg> {
    private static final int AUTH_REQUEST_STATUS_NEW = 1;
    private long createTime;
    private String devId;
    private int expireTime;
    private long lastModifyTime;
    private int status;
    private String subHuid;
    private String subUserNickName;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getSubUserNickName() {
        return this.subUserNickName;
    }

    public void setSubUserNickName(String str) {
        this.subUserNickName = str;
    }

    public String getSubHuid() {
        return this.subHuid;
    }

    public void setSubHuid(String str) {
        this.subHuid = str;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public long getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(long j) {
        this.lastModifyTime = j;
    }

    public int getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(int i) {
        this.expireTime = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg) {
        if (this.status == 1 && wifiDeviceSubUserAuthMsg.getStatus() != 1) {
            return -1;
        }
        if (this.status == 1 || wifiDeviceSubUserAuthMsg.getStatus() != 1) {
            return (int) (wifiDeviceSubUserAuthMsg.getLastModifyTime() - this.lastModifyTime);
        }
        return 1;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "WifiDeviceSubUserAuthMsg{subUserNickName='" + CommonUtil.l(this.subUserNickName) + "', subHuid='" + CommonUtil.l(this.subHuid) + "', devId='" + CommonUtil.l(this.devId) + "', createTime=" + this.createTime + ", status=" + this.status + ", lastModifyTime=" + this.lastModifyTime + ", expireTime=" + this.expireTime + '}';
    }
}
