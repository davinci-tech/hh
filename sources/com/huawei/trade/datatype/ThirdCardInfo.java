package com.huawei.trade.datatype;

/* loaded from: classes6.dex */
public class ThirdCardInfo {
    private String cardNum;
    private String cardPass;
    private String cardPoolId;
    private String cardPoolName;
    private int cardStatus;
    private int description;
    private long effectiveTime;
    private long expirateTime;
    private boolean isEnterprisePurchase;
    private int linkType;
    private String linkValue;
    private String provider;

    public boolean isEnterprisePurchase() {
        return this.isEnterprisePurchase;
    }

    public void setEnterprisePurchase(boolean z) {
        this.isEnterprisePurchase = z;
    }

    public int getCardStatus() {
        return this.cardStatus;
    }

    public void setCardStatus(int i) {
        this.cardStatus = i;
    }

    public String getCardPoolId() {
        return this.cardPoolId;
    }

    public void setCardPoolId(String str) {
        this.cardPoolId = str;
    }

    public String getCardPoolName() {
        return this.cardPoolName;
    }

    public void setCardPoolName(String str) {
        this.cardPoolName = str;
    }

    public int getDescription() {
        return this.description;
    }

    public void setDescription(int i) {
        this.description = i;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String str) {
        this.cardNum = str;
    }

    public String getCardPass() {
        return this.cardPass;
    }

    public void setCardPass(String str) {
        this.cardPass = str;
    }

    public long getEffectiveTime() {
        return this.effectiveTime;
    }

    public void setEffectiveTime(long j) {
        this.effectiveTime = j;
    }

    public long getExpirateTime() {
        return this.expirateTime;
    }

    public void setExpirateTime(long j) {
        this.expirateTime = j;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getLinkValue() {
        return this.linkValue;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String toString() {
        return "ThirdCardInfo{cardPoolName='" + this.cardPoolName + "', description=" + this.description + ", cardNum='" + this.cardNum + "', cardPass='" + this.cardPass + "', effectiveTime=" + this.effectiveTime + ", expirateTime=" + this.expirateTime + ", provider='" + this.provider + "', linkType=" + this.linkType + ", linkValue=" + this.linkValue + '}';
    }
}
