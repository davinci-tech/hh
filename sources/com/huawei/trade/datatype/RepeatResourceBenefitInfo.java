package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class RepeatResourceBenefitInfo {
    private int availableTimes;
    private long createTime;
    private Long expireTime;
    private String extendInfo;
    private long nowTime;
    private String resourceId;
    private int resourceType;

    public int getResourceType() {
        return this.resourceType;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public String getExtendInfo() {
        return this.extendInfo;
    }

    public Long getExpireTime() {
        if (this.resourceType == 0 && this.expireTime == null) {
            return 0L;
        }
        return this.expireTime;
    }

    public void setExpireTime(Long l) {
        this.expireTime = l;
    }

    public int getAvailableTimes() {
        return this.availableTimes;
    }

    public void setAvailableTimes(int i) {
        this.availableTimes = i;
    }

    public long getNowTime() {
        return this.nowTime;
    }

    public void setNowTime(long j) {
        this.nowTime = j;
    }
}
