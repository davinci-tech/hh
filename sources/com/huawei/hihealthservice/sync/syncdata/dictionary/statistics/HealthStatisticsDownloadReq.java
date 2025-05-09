package com.huawei.hihealthservice.sync.syncdata.dictionary.statistics;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;
import java.util.Set;

/* loaded from: classes7.dex */
public class HealthStatisticsDownloadReq implements IRequest {

    @SerializedName("dataSource")
    private final int dataSource;

    @SerializedName("deviceCode")
    private final long deviceCode;

    @SerializedName("endTime")
    private final int endTime;

    @SerializedName("startTime")
    private final int startTime;

    @SerializedName("types")
    private final Set<Integer> types;

    public HealthStatisticsDownloadReq(Builder builder) {
        this.startTime = builder.mStartTime;
        this.endTime = builder.mEndTime;
        this.dataSource = builder.mDataSource;
        this.deviceCode = builder.mDeviceCode;
        this.types = builder.mDownloadTypes;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataQuery/health/getHealthStatistics";
    }

    public static class Builder {
        private int mDataSource;
        private long mDeviceCode;
        private Set<Integer> mDownloadTypes;
        private int mEndTime;
        private int mStartTime;

        public Builder startTime(int i) {
            this.mStartTime = i;
            return this;
        }

        public Builder endTime(int i) {
            this.mEndTime = i;
            return this;
        }

        public Builder dataSource(int i) {
            this.mDataSource = i;
            return this;
        }

        public Builder deviceCode(int i) {
            this.mDeviceCode = i;
            return this;
        }

        public Builder types(Set<Integer> set) {
            this.mDownloadTypes = set;
            return this;
        }

        public HealthStatisticsDownloadReq build() {
            return new HealthStatisticsDownloadReq(this);
        }
    }

    public String toString() {
        return "HealthStatisticsDownloadReq{sTm=" + this.startTime + ",eTm=" + this.endTime + ",Source=" + this.dataSource + ",devCode=" + this.deviceCode + ",tp=" + this.types + '}';
    }
}
