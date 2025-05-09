package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.network.base.common.Headers;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.embedded.f1;
import com.huawei.hms.network.embedded.h1;
import com.huawei.hms.network.embedded.u0;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.RequestFinishedInfo;
import com.huawei.hms.network.httpclient.websocket.WebSocket;
import com.huawei.hms.network.inner.api.PolicyNetworkService;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.CronetEngine;
import org.chromium.net.CronetException;
import org.chromium.net.ExperimentalBidirectionalStream;
import org.chromium.net.ExperimentalUrlRequest;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes9.dex */
public class j2 implements d1 {
    public static final String A = "received_packet_estimated_bandwidth_bps";
    public static final int B = 50;
    public static final String s = "j2";
    public static final String t = "Content-Length";
    public static final String u = "Content-Encoding";
    public static final String v = "Accept-Encoding";
    public static final String w = "content-length";
    public static final int x = 5;
    public static final int y = 16384;
    public static final long z = 10;

    /* renamed from: a, reason: collision with root package name */
    public final CronetEngine f5319a;
    public final k2 c;
    public UrlRequest d;
    public UrlResponseInfo f;
    public IOException g;
    public boolean h;
    public boolean i;
    public h1.d j;
    public boolean k;
    public volatile boolean l;
    public boolean n;
    public z2 o;
    public ExperimentalBidirectionalStream p;
    public CountDownLatch q = new CountDownLatch(1);
    public volatile int r = 0;
    public final l2 b = new l2();
    public f2 e = new f2(this);
    public RequestFinishedInfo m = new i2();

    @Override // com.huawei.hms.network.embedded.d1
    public e3 getConnectionInfo() {
        return null;
    }

    public void setReadStatus(int i) {
        this.r = i;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public void setRcEventListener(z2 z2Var) {
        this.o = z2Var;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public h1.d request() {
        return this.j;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public boolean isExecuted() {
        boolean z2;
        synchronized (this) {
            z2 = this.k;
        }
        return z2;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public boolean isCanceled() {
        return this.l;
    }

    public UrlResponseInfo getResponseInfo() {
        return this.f;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public RequestFinishedInfo getRequestFinishedInfo() {
        return this.m;
    }

    public InputStream getInputStream() throws IOException {
        h();
        return this.e;
    }

    public InputStream getErrorStream() {
        try {
            h();
            if (this.f.getHttpStatusCode() >= 400) {
                return this.e;
            }
        } catch (IOException unused) {
        }
        return null;
    }

    @Override // com.huawei.hms.network.embedded.d1
    public h1.f execute(h1.d dVar, WebSocket webSocket) throws IOException {
        String str = s;
        Logger.i(str, "the request has used the cronet!");
        if (webSocket != null) {
            Logger.w(str, "cronet can't use websocket");
            throw t0.d("cronet can't use websocket");
        }
        CheckParamUtils.checkNotNull(dVar, "request == null");
        synchronized (this) {
            if (this.k) {
                throw new IllegalStateException("Already executed");
            }
            this.k = true;
        }
        if (this.l) {
            throw t0.a("Canceled");
        }
        if (HianalyticsHelper.getInstance().isQuicEnableReport(ContextHolder.getAppContext()) && g4.getInstance().addQuicTrace()) {
            Request.Builder newBuilder = dVar.newBuilder();
            g4.getInstance().addUUIDWithoutDash(newBuilder, dVar.getHeaders());
            this.j = new h1.d(newBuilder.build());
        } else {
            this.j = dVar;
        }
        a(dVar);
        if (!this.l) {
            return i();
        }
        disconnect();
        throw t0.a("Canceled");
    }

    public void disconnect() {
        if (this.n) {
            j();
        }
    }

    @Override // com.huawei.hms.network.embedded.d1
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d1 m633clone() {
        return new j2(this.f5319a, this.c);
    }

    @Override // com.huawei.hms.network.embedded.d1
    public void cancel() {
        this.l = true;
        disconnect();
    }

    public void a(ByteBuffer byteBuffer) throws IOException {
        if (this.j.getBody() == null || !this.j.getBody().isDuplex()) {
            this.d.read(byteBuffer);
        } else {
            try {
                if (!this.q.await(10L, TimeUnit.SECONDS)) {
                    throw new IOException("Duplex read body timeout");
                }
                this.p.read(byteBuffer);
            } catch (InterruptedException e) {
                Logger.e(s, "getMoreData await error", e);
            } catch (RuntimeException unused) {
                Logger.e(s, "Duplex getMoreData error");
            }
        }
        this.b.loop(g());
    }

    public void a() {
        String str = s;
        Logger.v(str, "onRequestFinish");
        if (this.o != null) {
            Logger.v(str, "callback rcEventListener#callFinishAtNetLib");
            this.o.callFinishAtNetLib(this.r);
        }
    }

    public final String a(String str) {
        try {
            h();
            return a(d(), str);
        } catch (IOException unused) {
            return null;
        }
    }

    public long a(HashMap<String, List<String>> hashMap, String str, long j) {
        String a2 = a(hashMap, str);
        if (!TextUtils.isEmpty(a2)) {
            try {
                return Long.parseLong(a2);
            } catch (NumberFormatException e) {
                Logger.w(s, "getHeaderFieldLong failed, Exception:%s", e.getClass().getSimpleName());
                return j;
            }
        }
        Logger.w(s, "getHeaderFieldLong value null,name is " + str);
        return j;
    }

    private void l() throws IOException {
        if (this.n) {
            return;
        }
        Map<String, List<String>> headers = this.j.getHeaders();
        b(headers, v);
        ExperimentalUrlRequest.Builder newUrlRequestBuilder = this.f5319a.newUrlRequestBuilder(this.j.getUrl() == null ? "" : this.j.getUrl(), new c(), this.b);
        newUrlRequestBuilder.addRequestAnnotation(this);
        String method = this.j.getMethod();
        a((UrlRequest.Builder) newUrlRequestBuilder, Headers.of(headers));
        if (this.j.getBody() != null) {
            if (method.equals("GET")) {
                method = "POST";
            }
            if (TextUtils.isEmpty(b("Content-Length"))) {
                a((UrlRequest.Builder) newUrlRequestBuilder, "Content-Length", this.j.getBody().contentLength() + "");
            }
            Logger.i(s, "using cronet to request" + this.j.getBody().contentLength());
            z3 z3Var = new z3(this.j, this.b);
            newUrlRequestBuilder.setUploadDataProvider(z3Var, this.b);
            a((UrlRequest.Builder) newUrlRequestBuilder, "Content-Type", this.j.getBody().contentType());
            if (TextUtils.isEmpty(b("Content-Length"))) {
                a((UrlRequest.Builder) newUrlRequestBuilder, "Content-Length", "" + z3Var.getLength());
            }
        }
        newUrlRequestBuilder.setHttpMethod(method);
        if (!this.c.isEnableConnectionMigrated()) {
            newUrlRequestBuilder.disableConnectionMigration();
        }
        ExperimentalUrlRequest build = newUrlRequestBuilder.build();
        this.d = build;
        build.start();
        this.n = true;
    }

    private void k() throws IOException {
        if (this.n) {
            return;
        }
        ExperimentalBidirectionalStream.Builder newBidirectionalStreamBuilder = this.f5319a.newBidirectionalStreamBuilder(this.j.getUrl() == null ? "" : this.j.getUrl(), new b(), this.b);
        String method = this.j.getMethod();
        newBidirectionalStreamBuilder.addRequestAnnotation(this);
        a(newBidirectionalStreamBuilder, Headers.of(this.j.getHeaders()));
        if (this.j.getBody() != null && method.equals("GET")) {
            method = "POST";
        }
        newBidirectionalStreamBuilder.setHttpMethod(method);
        ExperimentalBidirectionalStream build = newBidirectionalStreamBuilder.build();
        this.p = build;
        build.start();
        this.n = true;
    }

    private void j() {
        if (this.j.getBody() == null || !this.j.getBody().isDuplex()) {
            if (this.d.isDone()) {
                return;
            } else {
                this.d.cancel();
            }
        } else if (this.p.isDone()) {
            return;
        } else {
            this.p.cancel();
        }
        try {
            this.b.loop(50);
        } catch (Exception e) {
            Logger.w(s, "disconnect loop failed " + e.getMessage());
        }
    }

    private h1.f i() throws IOException {
        h();
        int httpStatusCode = this.f.getHttpStatusCode();
        HashMap<String, List<String>> hashMap = new HashMap<>();
        hashMap.putAll(this.f.getAllHeaders());
        if (b(hashMap, "Content-Encoding")) {
            b(hashMap, "Content-Length");
        }
        if (this.l) {
            disconnect();
            throw t0.a("Canceled");
        }
        f1.b bVar = new f1.b();
        String e = e();
        MediaType parse = e != null ? MediaType.parse(e) : null;
        bVar.inputStream(httpStatusCode >= 400 ? getErrorStream() : getInputStream()).contentLength(a(hashMap)).contentType(e).charSet(parse != null ? parse.charset() : null);
        f1 build = bVar.build();
        String url = this.f.getUrl() != null ? this.f.getUrl() : this.j.getUrl();
        u0.b bVar2 = new u0.b();
        bVar2.body(new h1.g(build)).code(httpStatusCode).message(this.f.getHttpStatusText()).url(url).headers(hashMap);
        if (!this.l) {
            return new h1.f(bVar2.build());
        }
        disconnect();
        throw t0.a("Canceled");
    }

    private void h() throws IOException {
        if (!this.i) {
            a(this.j);
            this.b.loop(g());
        }
        c();
    }

    private int g() {
        return this.j.getNetConfig().getReadTimeout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String f() {
        try {
            return new URL(this.j.getUrl()).getProtocol();
        } catch (MalformedURLException e) {
            Logger.v(s, "getProtocol failed, Exception:%s", e.getClass().getSimpleName());
            return "";
        }
    }

    private String e() {
        return a(com.alipay.sdk.m.p.e.f);
    }

    private Map<String, List<String>> d() {
        return this.f.getAllHeaders();
    }

    private void c() throws IOException {
        if (!this.i) {
            throw new IllegalStateException("No response.");
        }
        IOException iOException = this.g;
        if (iOException != null) {
            throw iOException;
        }
        if (this.f == null) {
            throw new NullPointerException("Response info is null when there is no exception.");
        }
    }

    private boolean b(Map<String, List<String>> map, String str) {
        boolean z2 = false;
        if (str == null) {
            return false;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (str.equalsIgnoreCase(it.next())) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    private String b(String str) {
        return Headers.of(this.j.getHeaders()).get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(UrlResponseInfo urlResponseInfo) {
        LinkedHashMap<String, String> quicStatsMap;
        if (urlResponseInfo == null || (quicStatsMap = n2.getQuicStatsMap(urlResponseInfo)) == null || quicStatsMap.isEmpty()) {
            return;
        }
        a((Map<String, String>) quicStatsMap);
        u2 metrics = ((i2) this.m).getMetrics();
        String str = quicStatsMap.get(n2.CONGESTION_CONTROL_TYPE);
        if (!TextUtils.isEmpty(str)) {
            metrics.setCongestionControlType(str);
        }
        String str2 = quicStatsMap.get(n2.MULTIPATH_ALGORITHM);
        if (!TextUtils.isEmpty(str2)) {
            metrics.setMultipathAlgorithm(StringUtils.stringToInteger(str2, 0));
        }
        String str3 = quicStatsMap.get("server_ip");
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        metrics.setConnectIps(Arrays.asList(str3));
        metrics.setSuccessIp(str3);
    }

    private void a(UrlRequest.Builder builder, String str, String str2) {
        if (str2 == null) {
            return;
        }
        builder.addHeader(str, str2);
    }

    private void a(UrlRequest.Builder builder, Headers headers) {
        if (builder == null || headers == null) {
            return;
        }
        boolean z2 = false;
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            builder.addHeader(name, headers.value(i));
            if (!z2 && StringUtils.toLowerCase(name).equals(FeedbackWebConstants.USER_AGENT)) {
                z2 = true;
            }
        }
        if (z2) {
            return;
        }
        builder.addHeader("User-Agent", j1.getUserAgent(ContextHolder.getAppContext()));
    }

    private void a(ExperimentalBidirectionalStream.Builder builder, Headers headers) {
        if (builder == null || headers == null) {
            return;
        }
        boolean z2 = false;
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            builder.addHeader(name, headers.value(i));
            if (!z2 && StringUtils.toLowerCase(name).equals(FeedbackWebConstants.USER_AGENT)) {
                z2 = true;
            }
        }
        if (z2) {
            return;
        }
        builder.addHeader("User-Agent", j1.getUserAgent(ContextHolder.getAppContext()));
    }

    public class b extends BidirectionalStream.Callback {

        /* renamed from: a, reason: collision with root package name */
        public LinkedBlockingQueue<Object> f5320a;

        public void onWriteCompleted(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer, boolean z) {
            try {
                j2.this.f = urlResponseInfo;
                this.f5320a.poll();
            } catch (IllegalArgumentException unused) {
                Logger.w(j2.s, "queue extraction exception");
            }
        }

        public void onSucceeded(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            j2.this.f = urlResponseInfo;
            j2.this.a((IOException) null);
        }

        public void onStreamReady(BidirectionalStream bidirectionalStream) {
            this.f5320a = new LinkedBlockingQueue<>(5);
            if (j2.this.j.getBody() != null) {
                try {
                    j2.this.j.getBody().writeTo(new b2(j2.this.p, j2.this.j.getBody(), this.f5320a));
                } catch (IOException unused) {
                    Logger.w(j2.s, "data stream writing exception");
                }
            }
            bidirectionalStream.flush();
        }

        public void onResponseHeadersReceived(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            ((i2) j2.this.m).getMetrics().setProtocol(urlResponseInfo.getNegotiatedProtocol());
            j2.this.f = urlResponseInfo;
            j2.this.i = true;
            try {
                bidirectionalStream.read(ByteBuffer.allocateDirect(16384));
            } catch (RuntimeException unused) {
                Logger.w(j2.s, "Unexpected read attempt");
            }
            j2.this.q.countDown();
        }

        public void onReadCompleted(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer, boolean z) {
            j2.this.f = urlResponseInfo;
            j2.this.i = true;
            j2.this.b.quit();
        }

        public void onFailed(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            if (cronetException == null) {
                throw new IllegalStateException("Exception cannot be null in onFailed.");
            }
            j2.this.f = urlResponseInfo;
            ((i2) j2.this.m).setException(cronetException);
            j2.this.a((IOException) cronetException);
        }

        public void onCanceled(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            j2.this.f = urlResponseInfo;
            j2.this.a(new IOException("disconnect() called"));
        }

        public b() {
        }
    }

    private void a(Map<String, String> map) {
        String str = map.get(A);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            l1.getInstance().onBandwidthMessageReceived(str, this.j.getUrl());
        } catch (Exception unused) {
            Logger.w(s, "the receivedPacketEstimatedBandwidthBps has exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IOException iOException) {
        this.g = iOException;
        f2 f2Var = this.e;
        if (f2Var != null) {
            f2Var.a(iOException);
        }
        this.i = true;
        this.b.quit();
    }

    public class c extends UrlRequest.Callback {
        public void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            Logger.v(j2.s, "onSucceeded:");
            j2.this.f = urlResponseInfo;
            j2.this.a(urlResponseInfo);
            j2.this.a((IOException) null);
        }

        public void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            ((i2) j2.this.m).getMetrics().setProtocol(urlResponseInfo.getNegotiatedProtocol());
            j2.this.f = urlResponseInfo;
            j2.this.i = true;
            j2.this.b.quit();
        }

        public void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            Logger.v(j2.s, "onRedirectReceived newLocationUrl: %s", str);
            j2.this.h = true;
            try {
                boolean equals = new URL(str).getProtocol().equals(j2.this.f());
                if ((j2.this.c.getPolicyExecutor() == null || StringUtils.stringToBoolean(j2.this.c.getPolicyExecutor().getValue("", PolicyNetworkService.ClientConstants.FOLLOW_REDIRECTS), true)) && equals) {
                    j2.this.d.followRedirect();
                    return;
                }
            } catch (MalformedURLException e) {
                Logger.v(j2.s, "onRedirectReceived occur exception:" + e.getClass().getSimpleName());
            }
            j2.this.f = urlResponseInfo;
            j2.this.d.cancel();
            j2.this.a((IOException) null);
        }

        public void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            j2.this.f = urlResponseInfo;
            j2.this.b.quit();
        }

        public void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            if (cronetException == null) {
                throw new IllegalStateException("Exception cannot be null in onFailed.");
            }
            j2.this.f = urlResponseInfo;
            j2.this.a(urlResponseInfo);
            ((i2) j2.this.m).setException(cronetException);
            j2.this.a((IOException) cronetException);
        }

        public void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            Logger.v(j2.s, "onCanceled:");
            j2.this.f = urlResponseInfo;
            j2.this.a(urlResponseInfo);
            j2.this.a(new IOException("disconnect() called"));
        }

        public c() {
        }
    }

    private void a(h1.d dVar) throws IOException {
        if (dVar.getBody() == null || !dVar.getBody().isDuplex()) {
            l();
        } else {
            k();
        }
    }

    private String a(Map<String, List<String>> map, String str) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        for (String str2 : map.keySet()) {
            if (str.equalsIgnoreCase(str2)) {
                List<String> list = map.get(str2);
                if (list == null || list.isEmpty()) {
                    return null;
                }
                return list.get(list.size() - 1);
            }
        }
        return null;
    }

    private long a(HashMap<String, List<String>> hashMap) {
        if (a(hashMap, w, -1L) > 2147483647L) {
            return -1L;
        }
        return (int) r3;
    }

    public j2(CronetEngine cronetEngine, k2 k2Var) {
        this.f5319a = cronetEngine;
        this.c = k2Var;
    }
}
