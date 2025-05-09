package com.huawei.health.vip.datatypes;

import java.util.List;

/* loaded from: classes4.dex */
public class VipMessageInfo {
    private boolean autoRenew;
    private long createTime;
    private String deviceType;
    private String duration;
    private long effectiveEndTime;
    private long expireTime;
    private String huid;
    private long interval;
    private int memberType;
    private long nextChargeTime;
    private int operateType;
    private String orderCode;
    private String productId;
    private String promotionId;
    private String promotionPolicyId;
    private int remindDay;
    private List<Integer> resourceTypes;
    private String shareInfoUniqueId;
    private String userAccountAnon;
    private String userId;

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public long getNextChargeTime() {
        return this.nextChargeTime;
    }

    public void setNextChargeTime(long j) {
        this.nextChargeTime = j;
    }

    public int getOperateType() {
        return this.operateType;
    }

    public int getMemberType() {
        return this.memberType;
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public long getInterval() {
        return this.interval;
    }

    public boolean isAutoRenew() {
        return this.autoRenew;
    }

    public String getUserAccountAnon() {
        return this.userAccountAnon;
    }

    public String getHuid() {
        return this.huid;
    }

    public String getShareInfoUniqueId() {
        return this.shareInfoUniqueId;
    }

    public void setOperateType(int i) {
        this.operateType = i;
    }

    public void setMemberType(int i) {
        this.memberType = i;
    }

    public void setExpireTime(long j) {
        this.expireTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String str) {
        this.duration = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setInterval(long j) {
        this.interval = j;
    }

    public void setAutoRenew(boolean z) {
        this.autoRenew = z;
    }

    public void setUserAccountAnon(String str) {
        this.userAccountAnon = str;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public void setShareInfoUniqueId(String str) {
        this.shareInfoUniqueId = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public long getEffectiveEndTime() {
        return this.effectiveEndTime;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setEffectiveEndTime(long j) {
        this.effectiveEndTime = j;
    }

    public List<Integer> getResourceTypes() {
        return this.resourceTypes;
    }

    public void setResourceTypes(List<Integer> list) {
        this.resourceTypes = list;
    }

    public int getRemindDay() {
        return this.remindDay;
    }

    public void setRemindDay(int i) {
        this.remindDay = i;
    }

    public String getPromotionPolicyId() {
        return this.promotionPolicyId;
    }

    public void setPromotionPolicyId(String str) {
        this.promotionPolicyId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public String toString() {
        return "VipMessageInfo{operateType=" + this.operateType + ", memberType=" + this.memberType + ", expireTime=" + this.expireTime + ", createTime=" + this.createTime + ", userId='" + this.userId + "', interval=" + this.interval + ", autoRenew=" + this.autoRenew + ", userAccountAnon='" + this.userAccountAnon + "', huid='" + this.huid + "', shareInfoUniqueId='" + this.shareInfoUniqueId + "', nextChargeTime=" + this.nextChargeTime + ", orderCode='" + this.orderCode + "', deviceType='" + this.deviceType + "', effectiveEndTime=" + this.effectiveEndTime + ", resourceTypes=" + this.resourceTypes + ", promotionPolicyId='" + this.promotionPolicyId + "', productId='" + this.productId + "', promotionId='" + this.promotionId + "'}";
    }
}
