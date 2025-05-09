package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class KakaRedeemGiftReturnBody {
    private String mAwardId;
    private String mExchangeCode;
    private int mSumKaka;
    private String mToUseUrl;

    public String getAwardId() {
        return this.mAwardId;
    }

    public void setAwardId(String str) {
        this.mAwardId = str;
    }

    public int getSumKaka() {
        return this.mSumKaka;
    }

    public void setSumKaka(int i) {
        this.mSumKaka = i;
    }

    public String getExchangeCode() {
        return this.mExchangeCode;
    }

    public void setExchangeCode(String str) {
        this.mExchangeCode = str;
    }

    public String getToUseUrl() {
        return this.mToUseUrl;
    }

    public void setToUseUrl(String str) {
        this.mToUseUrl = str;
    }

    public String toString() {
        return "KakaRedeemGiftReturnBody{mAwardId='" + this.mAwardId + "', mSumKaka=" + this.mSumKaka + ", mExchangeCode='" + this.mExchangeCode + "', mToUseUrl='" + this.mToUseUrl + "'}";
    }
}
