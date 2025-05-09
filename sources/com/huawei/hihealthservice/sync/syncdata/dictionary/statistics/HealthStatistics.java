package com.huawei.hihealthservice.sync.syncdata.dictionary.statistics;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes7.dex */
public class HealthStatistics {

    @SerializedName("dataSource")
    private int mDataSource;

    @SerializedName("deviceCode")
    private long mDeviceCode;

    @SerializedName("generateTime")
    private long mGenerateTime;

    @SerializedName(ParsedFieldTag.RECORD_DAY)
    private int mRecordDay;

    @SerializedName("statistics")
    private JsonObject mStatistics;

    @SerializedName("timeZone")
    private String mTimezone;

    public HealthStatistics(Builder builder) {
        this.mRecordDay = builder.mRecordDay;
        this.mDataSource = builder.mDataSource;
        this.mDeviceCode = builder.mDeviceCode;
        this.mStatistics = builder.mStatistics;
        this.mTimezone = builder.mTimezone;
        this.mGenerateTime = builder.mGenerateTime;
    }

    public int getRecordDay() {
        return this.mRecordDay;
    }

    public long getDeviceCode() {
        return this.mDeviceCode;
    }

    public JsonObject getStatistics() {
        return this.mStatistics;
    }

    public String getTimezone() {
        return this.mTimezone;
    }

    public long getGenerateTime() {
        return this.mGenerateTime;
    }

    public static class Builder {
        private int mDataSource;
        private long mDeviceCode;
        private long mGenerateTime;
        private int mRecordDay;
        private JsonObject mStatistics;
        private String mTimezone;

        public Builder recordDay(int i) {
            this.mRecordDay = i;
            return this;
        }

        public Builder dataSource(int i) {
            this.mDataSource = i;
            return this;
        }

        public Builder deviceCode(long j) {
            this.mDeviceCode = j;
            return this;
        }

        public Builder statistics(JsonObject jsonObject) {
            this.mStatistics = jsonObject;
            return this;
        }

        public Builder timezone(String str) {
            this.mTimezone = str;
            return this;
        }

        public Builder generateTime(long j) {
            this.mGenerateTime = j;
            return this;
        }

        public HealthStatistics build() {
            return new HealthStatistics(this);
        }
    }

    public String toString() {
        return "HealthStatistics{mRecordDay=" + this.mRecordDay + ", mDataSource=" + this.mDataSource + ", mDeviceCode=" + this.mDeviceCode + ", mStatistics=" + this.mStatistics + ", mTimezone='" + this.mTimezone + "'}";
    }
}
