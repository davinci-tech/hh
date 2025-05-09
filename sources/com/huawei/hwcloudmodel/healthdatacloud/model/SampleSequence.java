package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class SampleSequence {

    @SerializedName("dataId")
    private final long mDataId;

    @SerializedName("dataSource")
    private final int mDataSource;

    @SerializedName("detailData")
    private final String mDetailData;

    @SerializedName("deviceCode")
    private final long mDeviceCode;

    @SerializedName("endTime")
    private final long mEndTime;

    @SerializedName("extendData")
    private final String mExtendData;

    @SerializedName("startTime")
    private final long mStartTime;

    @SerializedName("subType")
    private final int mSubType;

    @SerializedName("summaryData")
    private final String mSummaryData;

    @SerializedName("timeZone")
    private final String mTimeZone;

    @SerializedName("type")
    private final int mType;

    public SampleSequence(Builder builder) {
        this.mDataId = builder.mDataId;
        this.mDeviceCode = builder.mDeviceCode;
        this.mStartTime = builder.mStartTime;
        this.mEndTime = builder.mEndTime;
        this.mTimeZone = builder.mTimeZone;
        this.mType = builder.mType;
        this.mSubType = builder.mSubType;
        this.mDetailData = builder.mDetailData;
        this.mSummaryData = builder.mSummaryData;
        this.mExtendData = builder.mExtendData;
        this.mDataSource = builder.mDataSource;
    }

    public long getDataId() {
        return this.mDataId;
    }

    public long getDeviceCode() {
        return this.mDeviceCode;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public String getTimeZone() {
        return this.mTimeZone;
    }

    public int getType() {
        return this.mType;
    }

    public int getSubType() {
        return this.mSubType;
    }

    public String getDetailData() {
        return this.mDetailData;
    }

    public String getSummaryData() {
        return this.mSummaryData;
    }

    public String getExtendData() {
        return this.mExtendData;
    }

    public int getDataSource() {
        return this.mDataSource;
    }

    public static class Builder {
        private long mDataId;
        private int mDataSource;
        private String mDetailData;
        private long mDeviceCode;
        private long mEndTime;
        private String mExtendData;
        private long mStartTime;
        private int mSubType;
        private String mSummaryData;
        private String mTimeZone;
        private int mType;

        public Builder dataId(long j) {
            this.mDataId = j;
            return this;
        }

        public Builder deviceCode(long j) {
            this.mDeviceCode = j;
            return this;
        }

        public Builder startTime(long j) {
            this.mStartTime = j;
            return this;
        }

        public Builder endTime(long j) {
            this.mEndTime = j;
            return this;
        }

        public Builder timeZone(String str) {
            this.mTimeZone = str;
            return this;
        }

        public Builder type(int i) {
            this.mType = i;
            return this;
        }

        public Builder subType(int i) {
            this.mSubType = i;
            return this;
        }

        public Builder detailData(String str) {
            this.mDetailData = str;
            return this;
        }

        public Builder mSummaryData(String str) {
            this.mSummaryData = str;
            return this;
        }

        public Builder extendData(String str) {
            this.mExtendData = str;
            return this;
        }

        public Builder dataSource(int i) {
            this.mDataSource = i;
            return this;
        }

        public SampleSequence build() {
            return new SampleSequence(this);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SampleSequence { mDataId = ");
        stringBuffer.append(this.mDataId);
        stringBuffer.append("mDeviceCode = ").append(this.mDeviceCode);
        stringBuffer.append(", mStartTime = ").append(this.mStartTime);
        stringBuffer.append(", mEndTime = ").append(this.mEndTime);
        stringBuffer.append(", mTimeZone = ").append(this.mTimeZone);
        stringBuffer.append(", mType = ").append(this.mType);
        stringBuffer.append(", mSummaryData = ").append(this.mSummaryData);
        stringBuffer.append(", mDataSource = ").append(this.mDataSource);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
