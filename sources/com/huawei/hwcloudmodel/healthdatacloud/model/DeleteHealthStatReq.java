package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.Set;

/* loaded from: classes7.dex */
public class DeleteHealthStatReq implements IRequest {

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

    public DeleteHealthStatReq(Builder builder) {
        this.startTime = builder.mStartTime;
        this.endTime = builder.mEndTime;
        this.dataSource = builder.mDataSource;
        this.deviceCode = builder.mDeviceCode;
        this.types = builder.mTypes;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataSync/health/deleteHealthStat";
    }

    public static class Builder {
        private int mDataSource;
        private long mDeviceCode;
        private int mEndTime;
        private int mStartTime;
        private Set<Integer> mTypes;

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
            this.mTypes = set;
            return this;
        }

        public DeleteHealthStatReq build() {
            return new DeleteHealthStatReq(this);
        }
    }

    public String toString() {
        return "DeleteHealthStatReq{mStartTime = " + this.startTime + ", mEndTime = " + this.endTime + ", mDataSource = " + this.dataSource + ", mDeviceCode = " + this.deviceCode + ", mTypes = " + this.types + '}';
    }
}
