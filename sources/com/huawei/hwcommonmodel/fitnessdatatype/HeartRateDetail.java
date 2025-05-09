package com.huawei.hwcommonmodel.fitnessdatatype;

/* loaded from: classes9.dex */
public class HeartRateDetail {
    public static final int HEART_RATE_TYPE_SPORT = 600;
    public static final int HEART_RATE_TYPE_TRANQUILLIZATION = 601;
    private long timeStamp = 0;
    private int type = 601;
    private int hrValue = 0;

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public void setHeartRateType(int i) {
        this.type = i;
    }

    public int getHeartRateValue() {
        return this.hrValue;
    }

    public void setHeartRateValue(int i) {
        this.hrValue = i;
    }

    public String toString() {
        return "HeartRateDetail{timeStamp=" + this.timeStamp + ", type=" + this.type + ", hrValue=" + this.hrValue + '}';
    }
}
