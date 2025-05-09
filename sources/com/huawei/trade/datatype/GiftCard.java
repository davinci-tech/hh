package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class GiftCard {
    private long createTime;
    private int effectiveDays;
    private long effectiveEndTime;
    private int effectiveMode;
    private long effectiveStartTime;
    private String giftCardName;
    private int giftCardType;
    private String giftCardUniqueId;
    private int linkType;
    private String linkValue;
    private boolean showToReceiveFlag;
    private int status;
    private String userLabels;
    private int weight;

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setUserLabels(String str) {
        this.userLabels = str;
    }

    public String getUserLabels() {
        return this.userLabels;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setEffectiveDays(int i) {
        this.effectiveDays = i;
    }

    public int getEffectiveDays() {
        return this.effectiveDays;
    }

    public void setEffectiveEndTime(long j) {
        this.effectiveEndTime = j;
    }

    public long getEffectiveEndTime() {
        return this.effectiveEndTime;
    }

    public long getEffectiveStartTime() {
        return this.effectiveStartTime;
    }

    public void setEffectiveStartTime(long j) {
        this.effectiveStartTime = j;
    }

    public void setEffectiveMode(int i) {
        this.effectiveMode = i;
    }

    public int getEffectiveMode() {
        return this.effectiveMode;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setGiftCardType(int i) {
        this.giftCardType = i;
    }

    public int getGiftCardType() {
        return this.giftCardType;
    }

    public void setGiftCardName(String str) {
        this.giftCardName = str;
    }

    public String getGiftCardName() {
        return this.giftCardName;
    }

    public void setGiftCardUniqueId(String str) {
        this.giftCardUniqueId = str;
    }

    public String getGiftCardUniqueId() {
        return this.giftCardUniqueId;
    }

    public boolean isShowToReceiveFlag() {
        return this.showToReceiveFlag;
    }
}
