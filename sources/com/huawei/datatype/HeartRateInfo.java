package com.huawei.datatype;

import com.google.gson.annotations.SerializedName;
import defpackage.jdy;
import health.compact.a.CommonUtil;

/* loaded from: classes3.dex */
public class HeartRateInfo {

    @SerializedName("hri_info")
    private int heartRateInfo;

    @SerializedName("hr_info")
    private int heartRateQualityInfo;

    @SerializedName("hrsqi_info")
    private int heartRateSqiInfo;

    @SerializedName("time_info")
    private long timeInfo;
    private int heartRateCredibility = -3;

    @SerializedName("UUID")
    private String mUuid = "";

    public int getHeartRateCredibility() {
        return ((Integer) jdy.d(Integer.valueOf(this.heartRateCredibility))).intValue();
    }

    public void setHeartRateCredibility(int i) {
        this.heartRateCredibility = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getHeartRateQualityInfo() {
        return ((Integer) jdy.d(Integer.valueOf(this.heartRateQualityInfo))).intValue();
    }

    public void setHeartRateQualityInfo(int i) {
        this.heartRateQualityInfo = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getHrriInfo() {
        return ((Integer) jdy.d(Integer.valueOf(this.heartRateInfo))).intValue();
    }

    public void setHrriInfo(int i) {
        this.heartRateInfo = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getHeartRateSqiInfo() {
        return ((Integer) jdy.d(Integer.valueOf(this.heartRateSqiInfo))).intValue();
    }

    public void setHeartRateSqiInfo(int i) {
        this.heartRateSqiInfo = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long getTimeInfo() {
        return ((Long) jdy.d(Long.valueOf(this.timeInfo))).longValue();
    }

    public void setTimeInfo(long j) {
        this.timeInfo = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }

    public String toEncryptionDataString() {
        return "HeartRateInfo{hri_info=" + this.heartRateInfo + ", hrsqi_info=" + this.heartRateSqiInfo + ", hr_info=" + this.heartRateQualityInfo + ", time_info=" + this.timeInfo + ", heartRateCredibility=" + this.heartRateCredibility + ", UUID='" + CommonUtil.l(this.mUuid) + '}';
    }
}
