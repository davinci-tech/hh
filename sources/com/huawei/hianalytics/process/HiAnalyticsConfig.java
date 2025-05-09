package com.huawei.hianalytics.process;

import com.huawei.hianalytics.a1;
import com.huawei.hianalytics.b1;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.i1;
import com.huawei.hianalytics.j;
import com.huawei.hianalytics.z0;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class HiAnalyticsConfig {

    /* renamed from: a, reason: collision with root package name */
    public a1 f3894a;

    public final void a(String str) {
        this.f3894a.g = str;
    }

    public final void b(String str) {
        this.f3894a.f11b = str;
    }

    public final void c(String str) {
        this.f3894a.f14c = str;
    }

    public final void d(int i) {
        this.f3894a.d = i;
    }

    public final void e(int i) {
        this.f3894a.f3833a = i;
    }

    public final void f(int i) {
        this.f3894a.c = i;
    }

    public final void a(int i) {
        this.f3894a.b = i;
    }

    public final void b(int i) {
        this.f3894a.e = i;
    }

    public final void c(int i) {
        this.f3894a.f = i;
    }

    public final void d(String str) {
        this.f3894a.i = str;
    }

    public final void a(Builder builder) {
        b1 m544a = this.f3894a.m544a();
        m544a.f21a = builder.isImeiEnabled;
        m544a.f3837a = builder.imeiCustom;
        m544a.f24d = builder.isAndroidIdEnabled;
        m544a.c = builder.androidIdCustom;
        m544a.f22b = builder.isSNEnabled;
        m544a.d = builder.snCustom;
        m544a.f23c = builder.isUDIDEnabled;
        m544a.b = builder.udidCustom;
    }

    public final void b(Boolean bool) {
        this.f3894a.f10b = bool;
    }

    public final void c(boolean z) {
        this.f3894a.f12b = z;
    }

    public void d(boolean z) {
        this.f3894a.f15c = z;
    }

    public final void b(boolean z) {
        this.f3894a.f17d = z;
    }

    public final void c(Boolean bool) {
        this.f3894a.f13c = bool;
    }

    public final void a(Boolean bool) {
        this.f3894a.f6a = bool;
    }

    public final void a(Map<String, String> map) {
        this.f3894a.f8a = map;
    }

    public final void a(boolean z) {
        this.f3894a.f9a = z;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        public static final String TAG = "HAConfig";
        public String aaidCustom;
        public String androidIdCustom;
        public String channel;
        public String collectURL;
        public Map<String, String> httpHeaders;
        public String imeiCustom;
        public boolean isAndroidIdEnabled;
        public Boolean isEncrypted;
        public boolean isImeiEnabled;
        public boolean isMccMncEnabled;
        public boolean isSNEnabled;
        public boolean isSessionEnabled;
        public boolean isUDIDEnabled;
        public Boolean localEncrypted;
        public String remoteServerUrl;
        public boolean shortLinkEnabled;
        public String snCustom;
        public String udidCustom;
        public Boolean uploadEncrypted;
        public int portLimitSize = 30;
        public int expiryTime = 7;
        public boolean isUUIDEnabled = false;
        public int metricPolicy = 1;
        public int decryptMaxBatchSize = 500;
        public int decryptBatchSize = 500;
        public int decryptBatchSleepTime = 0;

        public HiAnalyticsConfig build() {
            return new HiAnalyticsConfig(this);
        }

        public Builder setAutoReportThresholdSize(int i) {
            this.portLimitSize = Math.min(Math.max(i, 10), 100);
            return this;
        }

        public Builder setCacheExpireTime(int i) {
            this.expiryTime = Math.min(Math.max(i, 2), 7);
            return this;
        }

        public Builder setHttpHeader(Map<String, String> map) {
            if (map == null) {
                map = new HashMap<>();
            }
            LinkedHashMap<String, String> a2 = j.a(map, 50, 1024L, 1024L, "x-hasdk");
            if (a2 != null && a2.size() > 0) {
                this.httpHeaders = a2;
            }
            return this;
        }

        public Builder setUploadEncrypted(boolean z) {
            this.uploadEncrypted = Boolean.valueOf(z);
            return this;
        }

        public Builder setUdid(String str) {
            if (!j.a("Udid_CustomSet", str, 4096)) {
                str = "";
            }
            this.udidCustom = str;
            return this;
        }

        public Builder setShortLinkEnabled(boolean z) {
            this.shortLinkEnabled = z;
            return this;
        }

        public Builder setSN(String str) {
            if (!j.a("SN_CustomSet", str, 4096)) {
                str = "";
            }
            this.snCustom = str;
            return this;
        }

        public Builder setRemoteServerUrl(String str) {
            this.remoteServerUrl = str;
            return this;
        }

        public Builder setMetricPolicy(int i) {
            this.metricPolicy = i;
            return this;
        }

        public Builder setLocalEncrypted(boolean z) {
            this.localEncrypted = Boolean.valueOf(z);
            return this;
        }

        public Builder setImei(String str) {
            if (!j.a("IMEI_CustomSet", str, 4096)) {
                str = "";
            }
            this.imeiCustom = str;
            return this;
        }

        public Builder setEncrypted(boolean z) {
            this.isEncrypted = Boolean.valueOf(z);
            return this;
        }

        public Builder setEnableUUID(boolean z) {
            this.isUUIDEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setEnableUDID(boolean z) {
            this.isUDIDEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setEnableSession(boolean z) {
            this.isSessionEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setEnableSN(boolean z) {
            this.isSNEnabled = z;
            return this;
        }

        public Builder setEnableMccMnc(boolean z) {
            this.isMccMncEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setEnableImei(boolean z) {
            this.isImeiEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setEnableAndroidID(boolean z) {
            this.isAndroidIdEnabled = z;
            return this;
        }

        @Deprecated
        public Builder setDecryptBatchPolicy(int i, int i2, int i3) {
            HiLog.d(TAG, "HiAnalyticsConfig.Builder.setDecryptBatchPolicy is executed.");
            this.decryptMaxBatchSize = i;
            this.decryptBatchSize = i2;
            this.decryptBatchSleepTime = i3;
            return this;
        }

        public Builder setCollectURL(String str) {
            if (!i1.a(str)) {
                HiLog.i(TAG, "setCollectURL: url check failed");
                str = "";
            }
            if (str.endsWith("/") || str.endsWith("\\")) {
                str = str.substring(0, str.length() - 1);
            }
            this.collectURL = str;
            return this;
        }

        public Builder setChannel(String str) {
            String str2 = "";
            if (str == null) {
                str = "";
            }
            if (str.length() > 256) {
                HiLog.i(TAG, "unsupported channel, length() = " + str.length());
            } else {
                str2 = str;
            }
            this.channel = str2;
            return this;
        }

        public Builder setAndroidId(String str) {
            if (!j.a("AndroidId_CustomSet", str, 4096)) {
                str = "";
            }
            this.androidIdCustom = str;
            return this;
        }

        public Builder setAAID(String str) {
            if (!j.a("aaid_CustomSet", str, 4096)) {
                str = "";
            }
            this.aaidCustom = str;
            return this;
        }

        public Builder() {
            HiLog.setLogAdapter(new z0());
        }
    }

    public HiAnalyticsConfig(HiAnalyticsConfig hiAnalyticsConfig) {
        this.f3894a = new a1(hiAnalyticsConfig.f3894a);
    }

    public HiAnalyticsConfig(Builder builder) {
        this.f3894a = new a1();
        a(builder);
        b(builder.channel);
        c(builder.collectURL);
        a(builder.isMccMncEnabled);
        c(builder.isSessionEnabled);
        e(builder.portLimitSize);
        a(builder.expiryTime);
        d(builder.isUUIDEnabled);
        a(builder.httpHeaders);
        a(builder.aaidCustom);
        a(builder.isEncrypted);
        b(builder.localEncrypted);
        c(builder.uploadEncrypted);
        f(builder.metricPolicy);
        d(builder.remoteServerUrl);
        d(builder.decryptMaxBatchSize);
        b(builder.decryptBatchSize);
        c(builder.decryptBatchSleepTime);
        b(builder.shortLinkEnabled);
    }
}
