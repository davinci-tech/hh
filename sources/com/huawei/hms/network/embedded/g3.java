package com.huawei.hms.network.embedded;

import android.os.SystemClock;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.embedded.g7;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.inner.api.DnsNetworkService;
import com.huawei.hms.network.inner.api.NetworkKitInnerImpl;
import com.huawei.hms.network.inner.api.NetworkService;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class g3 extends g7 {
    public static final String h = "HttpEventListener";
    public static final AtomicLong i = new AtomicLong(1);
    public static final a j = new a();

    /* renamed from: a, reason: collision with root package name */
    public final long f5262a;
    public final l3 b;
    public final DnsNetworkService c;
    public long d;
    public int e = 0;
    public e3 f;
    public z2 g;

    public void setRCEventListener(z2 z2Var) {
        this.g = z2Var;
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void secureConnectStart(t6 t6Var) {
        super.secureConnectStart(t6Var);
        this.b.getMetricsRealTime().setSecureConnectStartTime();
        this.b.getMetricsTime().setSecureConnectStartTime();
        a("secureConnectStart", this.b.getMetricsRealTime().getSecureConnectStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void secureConnectEnd(t6 t6Var, @Nullable i7 i7Var) {
        super.secureConnectEnd(t6Var, i7Var);
        this.b.getMetricsRealTime().setSecureConnectEndTime();
        this.b.getMetricsTime().setSecureConnectEndTime();
        a("secureConnectEnd", this.b.getMetricsRealTime().getSecureConnectEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void responseHeadersStart(t6 t6Var) {
        super.responseHeadersStart(t6Var);
        this.b.getMetricsRealTime().setResponseHeadersStartTime();
        this.b.getMetricsTime().setResponseHeadersStartTime();
        this.b.getMetricsRealTime().setTtfb(this.b.getMetricsRealTime().getResponseHeadersStartTime());
        this.b.getMetricsTime().setTtfb(this.b.getMetricsTime().getResponseHeadersStartTime());
        a("responseHeadersStart", this.b.getMetricsRealTime().getResponseHeadersStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void responseHeadersEnd(t6 t6Var, v7 v7Var) {
        super.responseHeadersEnd(t6Var, v7Var);
        this.b.getMetricsRealTime().setResponseHeadersEndTime();
        this.b.getMetricsTime().setResponseHeadersEndTime();
        this.b.getMetricsRealTime().setTtfbV1(this.b.getMetricsRealTime().getResponseHeadersEndTime());
        this.b.getMetricsTime().setTtfbV1(this.b.getMetricsTime().getResponseHeadersEndTime());
        a(v7Var);
        a("responseHeadersEnd", this.b.getMetricsRealTime().getResponseHeadersEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void responseFailed(t6 t6Var, IOException iOException) {
        super.responseFailed(t6Var, iOException);
        this.b.getMetricsRealTime().setResponseBodyEndTime();
        this.b.getMetricsTime().setResponseBodyEndTime();
        a("responseFailed", this.b.getMetricsRealTime().getResponseBodyEndTime());
        z2 z2Var = this.g;
        if (z2Var != null) {
            z2Var.responseFailed();
        }
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void responseBodyStart(t6 t6Var) {
        super.responseBodyStart(t6Var);
        this.b.getMetricsRealTime().setResponseBodyStartTime();
        this.b.getMetricsTime().setResponseBodyStartTime();
        a("responseBodyStart", this.b.getMetricsRealTime().getResponseBodyStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void responseBodyEnd(t6 t6Var, long j2) {
        super.responseBodyEnd(t6Var, j2);
        this.b.getMetricsRealTime().setResponseBodyEndTime();
        this.b.getMetricsTime().setResponseBodyEndTime();
        a("responseBodyEnd", this.b.getMetricsRealTime().getResponseBodyEndTime());
        z2 z2Var = this.g;
        if (z2Var != null) {
            z2Var.responseBodyEnd();
        }
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void requestHeadersStart(t6 t6Var) {
        super.requestHeadersStart(t6Var);
        this.b.getMetricsRealTime().setRequestHeadersStartTime();
        this.b.getMetricsTime().setRequestHeadersStartTime();
        a("requestHeadersStart", this.b.getMetricsRealTime().getRequestHeadersStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void requestHeadersEnd(t6 t6Var, t7 t7Var) {
        super.requestHeadersEnd(t6Var, t7Var);
        this.b.getMetricsRealTime().setRequestHeadersEndTime();
        this.b.getMetricsTime().setRequestHeadersEndTime();
        a("requestHeadersEnd", this.b.getMetricsRealTime().getRequestHeadersEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void requestBodyStart(t6 t6Var) {
        super.requestBodyStart(t6Var);
        this.b.getMetricsRealTime().setRequestBodyStartTime();
        this.b.getMetricsTime().setRequestBodyStartTime();
        a("requestBodyStart", this.b.getMetricsRealTime().getRequestBodyStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void requestBodyEnd(t6 t6Var, long j2) {
        super.requestBodyEnd(t6Var, j2);
        this.b.getMetrics().setRequestByteCount(j2);
        this.b.getMetricsRealTime().setRequestBodyEndTime();
        this.b.getMetricsTime().setRequestBodyEndTime();
        a("requestBodyEnd", this.b.getMetricsRealTime().getRequestBodyEndTime());
    }

    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.b;
    }

    public e3 getConnectionInfo() {
        return this.f;
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void dnsStart(t6 t6Var, String str) {
        super.dnsStart(t6Var, str);
        this.b.getMetricsRealTime().setDnsStartTime();
        this.b.getMetricsTime().setDnsStartTime();
        a("dnsStart", this.b.getMetricsRealTime().getDnsStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void dnsEnd(t6 t6Var, String str, List<InetAddress> list) {
        super.dnsEnd(t6Var, str, list);
        this.b.getMetricsRealTime().setDnsEndTime();
        this.b.getMetricsTime().setDnsEndTime();
        this.b.getMetrics().setDnsCache(this.c.getDnsCache());
        this.b.getMetrics().setDnsType(this.c.getDnsType());
        this.b.getMetrics().setDnsTtl(this.c.getDnsTtl());
        this.b.getMetrics().setDnsStatus(this.c.getDnsStatus());
        a("dnsEnd", this.b.getMetricsRealTime().getDnsEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void connectionReleased(t6 t6Var, y6 y6Var) {
        super.connectionReleased(t6Var, y6Var);
        this.b.getMetricsRealTime().setConnectionReleasedTime();
        this.b.getMetricsTime().setConnectionReleasedTime();
        a("connectionReleased", this.b.getMetricsRealTime().getConnectionReleasedTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void connectionAcquired(t6 t6Var, y6 y6Var) {
        String str;
        String str2;
        super.connectionAcquired(t6Var, y6Var);
        y8 y8Var = (y8) y6Var;
        this.b.getMetricsRealTime().setConnectionAcquiredTime();
        this.b.getMetricsTime().setConnectionAcquiredTime();
        a("connectionAcquired, connection id = " + y6Var.hashCode(), this.b.getMetricsRealTime().getConnectionAcquiredTime());
        if (y8Var == null) {
            return;
        }
        this.f = new e3(this.b.getHost(), y8Var);
        x7 b = y8Var.b();
        r7 d = y8Var.d();
        if (y8Var.a() != null) {
            str = y8Var.a().f().a();
            str2 = y8Var.a().a().a();
        } else {
            str = null;
            str2 = null;
        }
        if (str != null) {
            this.b.getMetrics().setTlsVersion(str);
        }
        if (str2 != null) {
            this.b.getMetrics().setCipherSuite(str2);
        }
        if (d != null) {
            this.b.getMetrics().setProtocol(d.toString());
        }
        a(b.d(), true);
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void connectStart(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(t6Var, inetSocketAddress, proxy);
        u2 metrics = this.b.getMetrics();
        int i2 = this.e;
        this.e = i2 + 1;
        metrics.setConnectRetryTime(i2);
        a(inetSocketAddress, false);
        if (this.b.getMetricsRealTime().getConnectStartTime() == 0) {
            this.b.getMetricsRealTime().setConnectStartTime();
            this.b.getMetricsTime().setConnectStartTime();
        }
        a("connectStart", this.b.getMetricsRealTime().getConnectStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void connectFailed(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable r7 r7Var, IOException iOException) {
        super.connectFailed(t6Var, inetSocketAddress, proxy, r7Var, iOException);
        if (r7Var != null) {
            this.b.getMetrics().setProtocol(r7Var.toString());
        }
        this.b.getMetricsRealTime().setConnectEndTime();
        this.b.getMetricsTime().setConnectEndTime();
        a("connectFailed", this.b.getMetricsRealTime().getConnectEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void connectEnd(t6 t6Var, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable r7 r7Var) {
        super.connectEnd(t6Var, inetSocketAddress, proxy, r7Var);
        if (r7Var != null) {
            this.b.getMetrics().setProtocol(r7Var.toString());
        }
        a(inetSocketAddress, true);
        this.b.getMetricsRealTime().setConnectEndTime();
        this.b.getMetricsTime().setConnectEndTime();
        a("connectEnd", this.b.getMetricsRealTime().getConnectEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void callStart(t6 t6Var) {
        super.callStart(t6Var);
        this.b.getMetricsRealTime().setCallStartTime();
        this.b.getMetricsTime().setCallStartTime();
        this.b.setUrl(t6Var.request().k().toString());
        this.d = SystemClock.elapsedRealtime();
        a("callStart", this.b.getMetricsRealTime().getCallStartTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void callFailed(t6 t6Var, IOException iOException) {
        super.callFailed(t6Var, iOException);
        this.b.setException(iOException);
        this.b.getMetricsRealTime().setCallEndTime();
        this.b.getMetricsTime().setCallEndTime();
        this.b.getMetrics().setDnsType(this.c.getDnsType());
        this.b.getMetrics().setDnsCache(this.c.getDnsCache());
        a("callFailed", this.b.getMetricsRealTime().getCallEndTime());
    }

    @Override // com.huawei.hms.network.embedded.g7
    public void callEnd(t6 t6Var) {
        super.callEnd(t6Var);
        this.b.getMetricsRealTime().setCallEndTime();
        this.b.getMetricsTime().setCallEndTime();
        a("callEnd", this.b.getMetricsRealTime().getCallEndTime());
    }

    public static a getFactory() {
        return j;
    }

    public static class a implements g7.b {
        public WeakHashMap<t6, WeakReference<g3>> events = new WeakHashMap<>();
        public final Object lock = new Object();

        public g3 getListener(t6 t6Var) {
            WeakReference<g3> weakReference;
            synchronized (this.lock) {
                weakReference = this.events.get(t6Var);
            }
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // com.huawei.hms.network.embedded.g7.b
        public g3 create(t6 t6Var) {
            g3 g3Var = new g3();
            synchronized (this.lock) {
                this.events.put(t6Var, new WeakReference<>(g3Var));
            }
            return g3Var;
        }
    }

    private void a(InetSocketAddress inetSocketAddress, boolean z) {
        InetAddress address;
        if (inetSocketAddress == null || (address = inetSocketAddress.getAddress()) == null) {
            return;
        }
        if (z) {
            this.b.getMetrics().setSuccessIp(address.getHostAddress());
        } else {
            this.b.getMetrics().addConnectIps(address.getHostAddress());
        }
    }

    private void a(String str, long j2) {
        Logger.v(h, "callId = %d / %s : ElapsedTime = %d", Long.valueOf(this.f5262a), str, Long.valueOf(j2 - this.d));
    }

    private void a(v7 v7Var) {
        String b = v7Var.b(j2.w);
        Logger.v(h, "content-length : " + b);
        this.b.getMetrics().setResponseByteCount(StringUtils.stringToLong(b, -1L));
    }

    public g3() {
        NetworkService service = NetworkKitInnerImpl.getInstance().getService("dns");
        if (!(service instanceof DnsNetworkService)) {
            throw new IllegalStateException("DNS service not available");
        }
        this.c = (DnsNetworkService) service;
        this.f5262a = i.getAndIncrement();
        this.b = new l3();
    }
}
