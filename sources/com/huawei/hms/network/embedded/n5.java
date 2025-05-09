package com.huawei.hms.network.embedded;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class n5 {
    public static final String i = "RequestMetrics";

    /* renamed from: a, reason: collision with root package name */
    public String f5390a;
    public long b;
    public long c;
    public long d;
    public long e;
    public long f;
    public String g;
    public boolean h;

    public JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("domain", this.f5390a);
            jSONObject.put(p5.d, this.g);
            jSONObject.put(p5.e, this.b);
            jSONObject.put(p5.f, this.c);
            jSONObject.put(p5.g, this.d);
            jSONObject.put(p5.h, this.e);
        } catch (JSONException unused) {
            Logger.w(i, "Generate RequestMetrics Error");
        }
        return jSONObject;
    }

    public boolean h() {
        return this.h;
    }

    public long g() {
        return this.d;
    }

    public long f() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String d() {
        return this.f5390a;
    }

    public long c() {
        return this.b;
    }

    public long b() {
        return this.c;
    }

    public long a() {
        return this.e;
    }

    public n5(RequestFinishedInfo requestFinishedInfo) {
        if (requestFinishedInfo == null || requestFinishedInfo.getMetricsTime() == null) {
            return;
        }
        RequestFinishedInfo.MetricsTime metricsTime = requestFinishedInfo.getMetricsTime();
        this.f5390a = requestFinishedInfo.getHost();
        this.b = metricsTime.getDnsEndTime() - metricsTime.getDnsStartTime();
        this.c = metricsTime.getConnectEndTime() - metricsTime.getConnectStartTime();
        this.d = metricsTime.getSecureConnectEndTime() - metricsTime.getSecureConnectStartTime();
        this.e = metricsTime.getConnectStartTime();
        this.f = metricsTime.getSecureConnectStartTime();
        this.g = requestFinishedInfo.getMetrics().getSuccessIp();
        this.h = requestFinishedInfo.getResponse() == null ? false : requestFinishedInfo.getResponse().isOK();
    }

    public n5(Interceptor.Chain chain) {
        RequestFinishedInfo requestFinishedInfo = chain.requestFinishedInfo();
        if (requestFinishedInfo != null) {
            RequestFinishedInfo.MetricsTime metricsTime = requestFinishedInfo.getMetricsTime();
            this.f5390a = chain.requestFinishedInfo().getHost();
            this.g = requestFinishedInfo.getMetrics().getSuccessIp();
            this.h = requestFinishedInfo.getResponse() == null ? false : requestFinishedInfo.getResponse().isOK();
            if (metricsTime != null) {
                this.b = metricsTime.getDnsEndTime() - metricsTime.getDnsStartTime();
                this.c = metricsTime.getConnectEndTime() - metricsTime.getConnectStartTime();
                this.d = metricsTime.getSecureConnectEndTime() - metricsTime.getSecureConnectStartTime();
                this.e = metricsTime.getConnectStartTime();
                this.f = metricsTime.getSecureConnectStartTime();
            }
        }
    }
}
