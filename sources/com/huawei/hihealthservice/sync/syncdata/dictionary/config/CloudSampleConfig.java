package com.huawei.hihealthservice.sync.syncdata.dictionary.config;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes7.dex */
public class CloudSampleConfig {

    @SerializedName("configData")
    private final String mConfigData;

    @SerializedName("configDescription")
    private final String mConfigDescription;

    @SerializedName("id")
    private final String mConfigId;

    @SerializedName("deviceCode")
    private final long mDeviceCode;

    @SerializedName("metaData")
    private final String mMetaData;

    @SerializedName(ParsedFieldTag.TASK_MODIFY_TIME)
    private final long mModifyTime;

    @SerializedName("scopeApp")
    private final String mScopeApp;

    @SerializedName("scopeDeviceType")
    private final String mScopeDeviceType;

    @SerializedName("type")
    private final String mType;

    public String getType() {
        return this.mType;
    }

    public String getConfigId() {
        return this.mConfigId;
    }

    public String getScopeApp() {
        return this.mScopeApp;
    }

    public String getScopeDeviceType() {
        return this.mScopeDeviceType;
    }

    public String getConfigData() {
        return this.mConfigData;
    }

    public String getConfigDescription() {
        return this.mConfigDescription;
    }

    public String getMetaData() {
        return this.mMetaData;
    }

    public long getDeviceCode() {
        return this.mDeviceCode;
    }

    public long getModifyTime() {
        return this.mModifyTime;
    }

    public CloudSampleConfig(Builder builder) {
        this.mType = builder.mType;
        this.mConfigId = builder.mConfigId;
        this.mScopeApp = builder.mScopeApp;
        this.mScopeDeviceType = builder.mScopeDeviceType;
        this.mConfigData = builder.mConfigData;
        this.mConfigDescription = builder.mConfigDescription;
        this.mMetaData = builder.mMetaData;
        this.mDeviceCode = builder.mDeviceCode;
        this.mModifyTime = builder.mModifyTime;
    }

    public static class Builder {
        private String mConfigData;
        private String mConfigDescription;
        private String mConfigId;
        private long mDeviceCode;
        private String mMetaData;
        private long mModifyTime;
        private String mScopeApp;
        private String mScopeDeviceType;
        private String mType;

        public Builder type(String str) {
            this.mType = str;
            return this;
        }

        public Builder configId(String str) {
            this.mConfigId = str;
            return this;
        }

        public Builder scopeApp(String str) {
            this.mScopeApp = str;
            return this;
        }

        public Builder scopeDeviceType(String str) {
            this.mScopeDeviceType = str;
            return this;
        }

        public Builder configData(String str) {
            this.mConfigData = str;
            return this;
        }

        public Builder configDescription(String str) {
            this.mConfigDescription = str;
            return this;
        }

        public Builder metaData(String str) {
            this.mMetaData = str;
            return this;
        }

        public Builder deviceCode(long j) {
            this.mDeviceCode = j;
            return this;
        }

        public Builder modifyTime(long j) {
            this.mModifyTime = j;
            return this;
        }

        public CloudSampleConfig build() {
            return new CloudSampleConfig(this);
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("CloudSampleConfig { type = ");
        stringBuffer.append(this.mType);
        stringBuffer.append(", id = ").append(this.mConfigId);
        stringBuffer.append(", scopeApp = ").append(this.mScopeApp);
        stringBuffer.append(", scopeDeviceType = ").append(this.mScopeDeviceType);
        stringBuffer.append(", configData = ").append(this.mConfigData);
        stringBuffer.append(", configDescription = ").append(this.mConfigDescription);
        stringBuffer.append(", metaData = ").append(this.mMetaData);
        stringBuffer.append(", deviceCode = ").append(this.mDeviceCode);
        stringBuffer.append(", modifyTime = ").append(this.mModifyTime);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
