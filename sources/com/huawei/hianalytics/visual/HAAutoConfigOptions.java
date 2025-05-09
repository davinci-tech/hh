package com.huawei.hianalytics.visual;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.visual.autocollect.EventType;
import java.util.List;

/* loaded from: classes4.dex */
public class HAAutoConfigOptions {
    public List<EventType> autoCollectEventTypes;
    public boolean isAutoCollectEnabled;
    public final boolean isVisualConfigEnabled;
    public final boolean isVisualizedEnabled;
    public final boolean isWebViewBridgeEnabled;
    public final String jsSdkUrlAndVersion;
    public final String remoteConfigUrl;

    /* loaded from: classes.dex */
    public static class Builder {
        public static final String TAG = "HAAutoConfigOptions.Builder";
        public List<EventType> autoCollectEventTypes;
        public boolean isAutoCollectEnabled;
        public boolean isVisualConfigEnabled;
        public boolean isVisualizedEnabled;
        public boolean isWebViewBridgeEnabled;
        public String jsSdkUrlAndVersion;
        public String remoteConfigUrl;

        public HAAutoConfigOptions build() {
            HiLog.d(TAG, "HAAutoConfigOptions.Builder.build() is executed");
            return new HAAutoConfigOptions(this);
        }

        public Builder setAutoCollectEnabled(boolean z) {
            this.isAutoCollectEnabled = z;
            return this;
        }

        public Builder setAutoCollectEventTypes(List<EventType> list) {
            this.autoCollectEventTypes = list;
            return this;
        }

        public Builder setJsSdkUrlAndVersion(String str) {
            this.jsSdkUrlAndVersion = str;
            return this;
        }

        public Builder setRemoteConfigUrl(String str) {
            this.remoteConfigUrl = str;
            return this;
        }

        public Builder setVisualConfigEnabled(boolean z) {
            this.isVisualConfigEnabled = z;
            return this;
        }

        public Builder setVisualizedEnabled(boolean z) {
            this.isVisualizedEnabled = z;
            return this;
        }

        public Builder setWebViewBridgeEnabled(boolean z) {
            this.isWebViewBridgeEnabled = z;
            return this;
        }
    }

    public List<EventType> getAutoCollectEventTypes() {
        return this.autoCollectEventTypes;
    }

    public String getJsSdkUrlAndVersion() {
        return this.jsSdkUrlAndVersion;
    }

    public String getRemoteConfigUrl() {
        return this.remoteConfigUrl;
    }

    public boolean isAutoCollectEnabled() {
        return this.isAutoCollectEnabled;
    }

    public boolean isVisualConfigEnabled() {
        return this.isVisualConfigEnabled;
    }

    public boolean isVisualizedEnabled() {
        return this.isVisualizedEnabled;
    }

    public boolean isWebViewBridgeEnabled() {
        return this.isWebViewBridgeEnabled;
    }

    public void setAutoCollectEnabled(boolean z) {
        this.isAutoCollectEnabled = z;
    }

    public void setAutoCollectEventTypes(List<EventType> list) {
        this.autoCollectEventTypes = list;
    }

    public HAAutoConfigOptions(Builder builder) {
        this.isAutoCollectEnabled = builder.isAutoCollectEnabled;
        this.isWebViewBridgeEnabled = builder.isWebViewBridgeEnabled;
        this.autoCollectEventTypes = builder.autoCollectEventTypes;
        this.isVisualizedEnabled = builder.isVisualizedEnabled;
        this.remoteConfigUrl = builder.remoteConfigUrl;
        this.isVisualConfigEnabled = builder.isVisualConfigEnabled;
        this.jsSdkUrlAndVersion = builder.jsSdkUrlAndVersion;
    }
}
