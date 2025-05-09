package com.huawei.openalliance.ad.download;

import com.huawei.agconnect.apms.instrument.URLConnectionInstrumentation;
import com.huawei.hms.network.embedded.j2;
import com.huawei.openalliance.ad.beans.inner.HttpConnection;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.net.http.HttpsConfig;
import com.huawei.openalliance.ad.utils.cy;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes5.dex */
public class i extends c {

    /* renamed from: a, reason: collision with root package name */
    private HttpURLConnection f6811a;

    @Override // com.huawei.openalliance.ad.download.c
    public HttpConnection d() {
        return new HttpConnection();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        cy.a(this.f6811a);
    }

    @Override // com.huawei.openalliance.ad.download.c
    public int c() {
        return this.f6811a.getContentLength();
    }

    @Override // com.huawei.openalliance.ad.download.c
    public int b() {
        return this.f6811a.getResponseCode();
    }

    @Override // com.huawei.openalliance.ad.download.c
    public String a(String str) {
        return this.f6811a.getHeaderField(str);
    }

    @Override // com.huawei.openalliance.ad.download.c
    public InputStream a() {
        return this.f6811a.getInputStream();
    }

    public i(String str, long j) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) URLConnectionInstrumentation.openConnection(new URL(str).openConnection());
        this.f6811a = httpURLConnection;
        HttpsConfig.a(httpURLConnection, true, false);
        this.f6811a.setConnectTimeout(10000);
        this.f6811a.setReadTimeout(10000);
        this.f6811a.setUseCaches(false);
        if (j > 0) {
            this.f6811a.setRequestProperty("Range", "bytes=" + j + Constants.LINK);
        }
        this.f6811a.setRequestProperty(j2.v, "identity");
        this.f6811a.connect();
    }
}
