package com.huawei.openalliance.ad.net.http;

import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public class Response<DATA> {
    private static final String TAG = "Response";
    private long contentLength;
    private DATA data;
    private long dataConverterCost;
    private long hmsRecEngineCost;

    @com.huawei.openalliance.ad.annotations.f
    private HttpConnection httpConnection;
    private long infoCost;
    private long netDuration1;
    private long netDuration2;
    private long netEndTS;
    private long netStartTS;
    private String origData;
    private long recEngineCost;
    private boolean reqBodyGzipped;

    @com.huawei.openalliance.ad.annotations.f
    private Throwable throwable;
    private int useHuaweiDNS;
    private int httpCode = -1;
    private String exception = "";
    private String exception1 = "";
    private boolean dnserror = false;
    private int requestType = 0;
    private int resultAdReqType = 0;

    public int t() {
        return this.resultAdReqType;
    }

    public long s() {
        return this.hmsRecEngineCost;
    }

    public long r() {
        return this.recEngineCost;
    }

    public long q() {
        return this.netEndTS;
    }

    public long p() {
        return this.netStartTS;
    }

    public boolean o() {
        return this.reqBodyGzipped;
    }

    public Throwable n() {
        return this.throwable;
    }

    public HttpConnection m() {
        return this.httpConnection;
    }

    public long l() {
        return this.dataConverterCost;
    }

    public long k() {
        return this.infoCost;
    }

    public String j() {
        return this.exception1;
    }

    public int i() {
        return this.useHuaweiDNS;
    }

    public int h() {
        return this.requestType;
    }

    public long g() {
        return this.netDuration2;
    }

    public void f(long j) {
        this.hmsRecEngineCost = j;
    }

    public long f() {
        return this.netDuration1;
    }

    public boolean e() {
        return this.dnserror;
    }

    public void e(long j) {
        this.recEngineCost = j;
    }

    public void d(long j) {
        if (j < 0) {
            return;
        }
        this.dataConverterCost = j;
        ho.b(TAG, "setDataConverterCost " + j);
    }

    public void d(int i) {
        this.resultAdReqType = i;
    }

    public String d() {
        return this.exception;
    }

    public void c(long j) {
        if (j < 0) {
            return;
        }
        this.infoCost = j;
        ho.b(TAG, "setInfoCost " + j);
    }

    public void c(int i) {
        this.useHuaweiDNS = i;
    }

    public long c() {
        return this.contentLength;
    }

    public void b(boolean z) {
        this.reqBodyGzipped = z;
    }

    public void b(String str) {
        this.origData = str;
    }

    public void b(long j) {
        if (j < 0) {
            return;
        }
        this.netDuration2 = j;
        ho.b(TAG, "setNetDuration2 " + j);
    }

    public void b(int i) {
        this.requestType = i;
    }

    public DATA b() {
        return this.data;
    }

    public void a(boolean z) {
        this.dnserror = z;
    }

    public void a(Throwable th) {
        if (th == null) {
            return;
        }
        this.exception = th.getClass().getSimpleName() + ":" + th.getMessage();
        this.throwable = th;
    }

    public void a(String str) {
        this.exception1 = str;
    }

    public void a(DATA data) {
        this.data = data;
    }

    public void a(Response response) {
        if (response == null) {
            return;
        }
        this.contentLength = response.contentLength;
        this.dataConverterCost = response.dataConverterCost;
        this.dnserror = response.dnserror;
        this.exception = response.exception;
        this.exception1 = response.exception1;
        this.httpCode = response.httpCode;
        this.httpConnection = response.httpConnection;
        this.infoCost = response.infoCost;
        this.netDuration1 = response.netDuration1;
        this.netDuration2 = response.netDuration2;
        this.netEndTS = response.netEndTS;
        this.netStartTS = response.netStartTS;
        this.reqBodyGzipped = response.reqBodyGzipped;
        this.requestType = response.requestType;
        this.throwable = response.throwable;
        this.useHuaweiDNS = response.useHuaweiDNS;
        this.recEngineCost = response.recEngineCost;
        this.hmsRecEngineCost = response.hmsRecEngineCost;
    }

    public void a(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    public void a(long j, long j2) {
        if (j <= 0 || j >= j2) {
            return;
        }
        this.netStartTS = j;
        this.netEndTS = j2;
        this.netDuration1 = j2 - j;
        ho.b(TAG, "setNetDuration1 " + this.netDuration1);
    }

    public void a(long j) {
        this.contentLength = j;
    }

    public void a(int i) {
        this.httpCode = i;
    }

    public int a() {
        return this.httpCode;
    }
}
