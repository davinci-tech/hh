package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class DeviceBenefitQueryParam {
    private static final String TAG = "DevBenefitQueryParam";

    @SerializedName("benefitType")
    private DeviceBenefitType mBenefitType;

    @SerializedName("deviceCategory")
    private int mDeviceCategory;

    @SerializedName("deviceInfo")
    private DeviceInfo mDeviceInfo;

    @SerializedName("deviceNumber")
    private String mDeviceNumber;

    @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
    private String mDeviceSn;

    @SerializedName("deviceType")
    private String mDeviceType;

    @SerializedName("isNeedProductInfo")
    private boolean mIsNeedProductInfo;

    @SerializedName("salt")
    private String mSalt;

    public static class Builder {
        private DeviceBenefitType mBenefitType;
        private int mDeviceCategory;
        private DeviceInfo mDeviceInfo;
        private String mDeviceNumber;
        private String mDeviceSn;
        private String mDeviceType;
        private boolean mIsNeedProductInfo;
        private String mSalt;

        public DeviceBenefitQueryParam build() {
            return new DeviceBenefitQueryParam(this);
        }

        public Builder setDeviceType(String str) {
            this.mDeviceType = str;
            return this;
        }

        public Builder setDeviceSn(String str) {
            this.mDeviceSn = str;
            return this;
        }

        public Builder setDeviceNumber(String str) {
            this.mDeviceNumber = str;
            return this;
        }

        public Builder setSalt(String str) {
            this.mSalt = str;
            return this;
        }

        public Builder setBenefitType(DeviceBenefitType deviceBenefitType) {
            this.mBenefitType = deviceBenefitType;
            return this;
        }

        public Builder setNeedProductInfo(boolean z) {
            this.mIsNeedProductInfo = z;
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo deviceInfo) {
            this.mDeviceInfo = deviceInfo;
            return this;
        }

        public Builder setDeviceCategory(int i) {
            this.mDeviceCategory = i;
            return this;
        }
    }

    public DeviceBenefitQueryParam(Builder builder) {
        if (builder != null) {
            this.mDeviceType = builder.mDeviceType;
            this.mDeviceSn = builder.mDeviceSn;
            this.mDeviceNumber = builder.mDeviceNumber;
            this.mSalt = builder.mSalt;
            this.mBenefitType = builder.mBenefitType;
            this.mIsNeedProductInfo = builder.mIsNeedProductInfo;
            this.mDeviceInfo = builder.mDeviceInfo;
            this.mDeviceCategory = builder.mDeviceCategory;
            return;
        }
        LogUtil.h(TAG, "builder is null");
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getDeviceSn() {
        return this.mDeviceSn;
    }

    public String getDeviceNumber() {
        return this.mDeviceNumber;
    }

    public String getSalt() {
        return this.mSalt;
    }

    public DeviceBenefitType getBenefitType() {
        return this.mBenefitType;
    }

    public boolean isNeedProductInfo() {
        return this.mIsNeedProductInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return this.mDeviceInfo;
    }

    public int getDeviceCategory() {
        return this.mDeviceCategory;
    }

    public enum DeviceBenefitType {
        DEVICE_BENEFIT_TYPE_ALL(0),
        DEVICE_BENEFIT_TYPE_INBOX(1),
        DEVICE_BENEFIT_TYPE_PERF_PURCHASE(2);

        private int defaultIndex;

        DeviceBenefitType(int i) {
            this.defaultIndex = i;
        }
    }

    public enum DeviceCategory {
        DEVICE_CATEGORY_PHONE(1),
        DEVICE_CATEGORY_WEAR(2);

        private int category;

        DeviceCategory(int i) {
            this.category = i;
        }

        public int getCategory() {
            return this.category;
        }
    }
}
