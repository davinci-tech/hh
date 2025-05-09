package com.huawei.pluginachievement.manager.model;

/* loaded from: classes6.dex */
public class CalorieExchange {
    private int exchangeCalorieNum;
    private int exchangeKakaNum;

    public CalorieExchange(int i, int i2) {
        this.exchangeKakaNum = i;
        this.exchangeCalorieNum = i2;
    }

    public int getExchangeKakaNum() {
        return this.exchangeKakaNum;
    }

    public String toString() {
        return "CalorieExchange{exchangeKakaNum=" + this.exchangeKakaNum + ", exchangeCalorieNum=" + this.exchangeCalorieNum + '}';
    }
}
