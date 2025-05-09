package com.huawei.hianalytics.v2;

import android.content.Context;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.d1;
import com.huawei.hianalytics.f1;
import com.huawei.hianalytics.framework.threadpool.TaskThread;
import com.huawei.hianalytics.k;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import java.util.Map;

@Deprecated
/* loaded from: classes4.dex */
public class HiAnalyticsConf {

    /* loaded from: classes.dex */
    public static class Builder {
        public static final String TAG = "HAConfBuilder";
        public String appid;
        public Context mContext;
        public HiAnalyticsConfig.Builder maintConfigBuilder;
        public HiAnalyticsConfig.Builder operConfigBuilder;

        public void create() {
            if (this.mContext == null) {
                HiLog.e(TAG, "context is null, create failed");
                return;
            }
            HiAnalyticsConfig build = this.maintConfigBuilder.build();
            HiAnalyticsConfig build2 = this.operConfigBuilder.build();
            k.a().a(this.mContext);
            d1 d1Var = new d1("_default_config_tag");
            if (d1Var.f27a == null) {
                HiLog.e(TAG, "instance is null, create failed. tag: _default_config_tag");
                return;
            }
            d1Var.b(build2);
            d1Var.a(build);
            k.a().a("_default_config_tag", d1Var);
            TaskThread.getUpdateThread().addToQueue(new f1(this.mContext, "_default_config_tag"));
            HiAnalyticsManager.setAppid(this.appid);
        }

        public void refresh(boolean z) {
            HiAnalyticsConfig build = this.maintConfigBuilder.build();
            HiAnalyticsConfig build2 = this.operConfigBuilder.build();
            d1 a2 = k.a().a("_default_config_tag");
            if (a2 == null) {
                HiLog.sw(TAG, "tag: _default_config_tag has no instance. ");
                return;
            }
            a2.refresh(1, build);
            a2.refresh(0, build2);
            if (z) {
                k a3 = k.a();
                if (a3.f46a == null) {
                    HiLog.sw("HADM", "sdk not init. tag: _default_config_tag");
                } else {
                    a3.a("_default_config_tag").f27a.clearCacheDataByTag();
                }
            }
            HiAnalyticsManager.setAppid(this.appid);
        }

        @Deprecated
        public Builder setAutoReportThreshold(int i) {
            return this;
        }

        public Builder setUDID(String str) {
            this.operConfigBuilder.setUdid(str);
            this.maintConfigBuilder.setUdid(str);
            return this;
        }

        public Builder setSN(String str) {
            this.operConfigBuilder.setSN(str);
            this.maintConfigBuilder.setSN(str);
            return this;
        }

        public Builder setIMEI(String str) {
            this.operConfigBuilder.setImei(str);
            this.maintConfigBuilder.setImei(str);
            return this;
        }

        @Deprecated
        public Builder setHttpHeader(Map<String, String> map) {
            this.operConfigBuilder.setHttpHeader(map);
            this.maintConfigBuilder.setHttpHeader(map);
            return this;
        }

        @Deprecated
        public Builder setEnableUUID(boolean z) {
            this.operConfigBuilder.setEnableUUID(z);
            this.maintConfigBuilder.setEnableUUID(z);
            return this;
        }

        @Deprecated
        public Builder setEnableUDID(boolean z) {
            this.maintConfigBuilder.setEnableUDID(z);
            this.operConfigBuilder.setEnableUDID(z);
            return this;
        }

        @Deprecated
        public Builder setEnableSession(boolean z) {
            this.operConfigBuilder.setEnableSession(z);
            return this;
        }

        @Deprecated
        public Builder setEnableSN(boolean z) {
            this.maintConfigBuilder.setEnableSN(z);
            this.operConfigBuilder.setEnableSN(z);
            return this;
        }

        @Deprecated
        public Builder setEnableMccMnc(boolean z) {
            this.maintConfigBuilder.setEnableMccMnc(z);
            this.operConfigBuilder.setEnableMccMnc(z);
            return this;
        }

        @Deprecated
        public Builder setEnableImei(boolean z) {
            this.operConfigBuilder.setEnableImei(z);
            this.maintConfigBuilder.setEnableImei(z);
            return this;
        }

        @Deprecated
        public Builder setEnableAndroidID(boolean z) {
            this.maintConfigBuilder.setEnableAndroidID(z);
            this.operConfigBuilder.setEnableAndroidID(z);
            return this;
        }

        @Deprecated
        public Builder setDecryptBatchPolicy(int i, int i2, int i3) {
            this.operConfigBuilder.setDecryptBatchPolicy(i, i2, i3);
            this.maintConfigBuilder.setDecryptBatchPolicy(i, i2, i3);
            return this;
        }

        @Deprecated
        public Builder setCollectURL(int i, String str) {
            HiAnalyticsConfig.Builder builder;
            if (i == 0) {
                builder = this.operConfigBuilder;
            } else {
                if (i != 1) {
                    HiLog.w(TAG, "invalid type");
                    return this;
                }
                builder = this.maintConfigBuilder;
            }
            builder.setCollectURL(str);
            return this;
        }

        @Deprecated
        public Builder setChannel(String str) {
            this.operConfigBuilder.setChannel(str);
            this.maintConfigBuilder.setChannel(str);
            return this;
        }

        @Deprecated
        public Builder setCacheExpireTime(int i) {
            this.operConfigBuilder.setCacheExpireTime(i);
            this.maintConfigBuilder.setCacheExpireTime(i);
            return this;
        }

        @Deprecated
        public Builder setAppID(String str) {
            this.appid = str;
            return this;
        }

        public Builder setAndroidId(String str) {
            this.operConfigBuilder.setAndroidId(str);
            this.maintConfigBuilder.setAndroidId(str);
            return this;
        }

        public Builder(Context context) {
            if (context != null) {
                this.mContext = context.getApplicationContext();
            }
            this.maintConfigBuilder = new HiAnalyticsConfig.Builder();
            this.operConfigBuilder = new HiAnalyticsConfig.Builder();
        }
    }
}
