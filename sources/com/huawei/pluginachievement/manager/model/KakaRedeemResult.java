package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class KakaRedeemResult {
    private int mKakaSum;
    private int mRedeemTimes;
    private Integer mRemainingKaka;

    public int getKakaSum() {
        return this.mKakaSum;
    }

    public void setKakaSum(int i) {
        this.mKakaSum = i;
    }

    public int getRedeemTimes() {
        return this.mRedeemTimes;
    }

    public void setRedeemTimes(int i) {
        this.mRedeemTimes = i;
    }

    public Integer getRemainingKaka() {
        return this.mRemainingKaka;
    }

    public void setRemainingKaka(Integer num) {
        this.mRemainingKaka = num;
    }

    public String toString() {
        return "KakaRedeemResult{mKakaSum=" + this.mKakaSum + ", mRedeemTimes=" + this.mRedeemTimes + ", mRemainingKaka=" + this.mRemainingKaka + '}';
    }
}
