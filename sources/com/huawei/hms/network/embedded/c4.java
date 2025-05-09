package com.huawei.hms.network.embedded;

import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.Response;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class c4 {

    /* renamed from: a, reason: collision with root package name */
    public d4 f5200a = new d4();

    public void a() {
    }

    public void a(String str, String str2, String str3, d1 d1Var) {
    }

    public void a(Map<String, List<String>> map) {
    }

    public void b() {
    }

    public void b(String str) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.f5200a;
    }

    public void b(long j) {
        this.f5200a.getMetrics().setResponseByteCount(j);
    }

    public void a(String str) {
        this.f5200a.setUrl(str);
        this.f5200a.getMetricsTime().setCallStartTime();
        this.f5200a.getMetricsRealTime().setCallStartTime();
    }

    public void a(Exception exc) {
        this.f5200a.setException(exc);
        this.f5200a.getMetricsTime().setCallEndTime();
        this.f5200a.getMetricsRealTime().setCallEndTime();
    }

    public void a(Response response) {
        this.f5200a.getMetricsTime().setCallEndTime();
        this.f5200a.getMetricsRealTime().setCallEndTime();
    }

    public void a(long j) {
        this.f5200a.getMetrics().setRequestByteCount(j);
    }
}
