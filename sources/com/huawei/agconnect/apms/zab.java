package com.huawei.agconnect.apms;

import android.text.TextUtils;
import com.huawei.agconnect.apms.collect.HiAnalyticsManager;
import com.huawei.agconnect.apms.collect.model.HeaderType;
import com.huawei.agconnect.apms.collect.model.event.network.HttpEvent;
import com.huawei.agconnect.apms.log.AgentLog;
import com.huawei.agconnect.apms.log.AgentLogManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class zab extends HttpURLConnection {
    public static final AgentLog abc = AgentLogManager.getAgentLog();
    public HttpURLConnection bcd;
    public wxy cde;
    public tsr def;

    public class abc implements qpo {
        public final /* synthetic */ wxy abc;

        public abc(wxy wxyVar) {
            this.abc = wxyVar;
        }

        @Override // com.huawei.agconnect.apms.qpo
        public void abc(rqp rqpVar) {
            if (!this.abc.edc()) {
                this.abc.abc(rqpVar.abc);
            }
            zab.this.abc(rqpVar.bcd);
        }

        @Override // com.huawei.agconnect.apms.qpo
        public void bcd(rqp rqpVar) {
            if (this.abc.edc()) {
                return;
            }
            try {
                this.abc.bcd(zab.this.bcd.getResponseCode());
            } catch (IOException e) {
                zab.abc.warn("failed to get status code: " + e.getMessage());
            }
            long contentLength = zab.this.bcd.getContentLength();
            long j = rqpVar.abc;
            this.abc.def(contentLength);
            this.abc.abc(j);
            zab.this.abc(this.abc);
        }
    }

    public class bcd implements qpo {
        public final /* synthetic */ wxy abc;

        public bcd(wxy wxyVar) {
            this.abc = wxyVar;
        }

        @Override // com.huawei.agconnect.apms.qpo
        public void abc(rqp rqpVar) {
            if (!this.abc.edc()) {
                this.abc.bcd(rqpVar.abc);
            }
            zab.this.abc(rqpVar.bcd);
        }

        @Override // com.huawei.agconnect.apms.qpo
        public void bcd(rqp rqpVar) {
            if (this.abc.edc()) {
                return;
            }
            try {
                this.abc.bcd(zab.this.bcd.getResponseCode());
            } catch (IOException e) {
                zab.abc.warn("failed to get status code: " + e.getMessage());
            }
            String requestProperty = zab.this.bcd.getRequestProperty("Content-length");
            long j = rqpVar.abc;
            if (requestProperty != null) {
                try {
                    j = Long.parseLong(requestProperty);
                } catch (Throwable th) {
                    zab.abc.warn("failed to get content length: " + th.getMessage());
                }
            }
            this.abc.bcd(j);
            zab.this.abc(this.abc);
        }
    }

    public zab(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.bcd = httpURLConnection;
        bcd();
        if (httpURLConnection.getURL() != null) {
            xyz.abc(httpURLConnection.getURL().getHost(), this.cde);
        }
        if (Agent.isNuwaTracerEnable()) {
            String bcd2 = k0.bcd();
            String abc2 = k0.abc();
            abc(httpURLConnection, bcd2, abc2);
            this.cde.fgh(bcd2);
            this.cde.efg(abc2);
        }
    }

    public final void abc(wxy wxyVar) {
        wxyVar.d();
        vwx e = wxyVar.e();
        if (e == null) {
            return;
        }
        if (wxyVar.dcb()) {
            rst.cde.add(new HttpEvent(e, r0.abc()));
        } else {
            rst.cde.add(new HttpEvent(e));
        }
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.bcd.addRequestProperty(str, str2);
    }

    public final wxy bcd() {
        if (this.cde == null) {
            wxy wxyVar = new wxy();
            this.cde = wxyVar;
            xyz.abc(wxyVar, this.bcd);
        }
        return this.cde;
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        bcd().ghi = 3;
        try {
            this.bcd.connect();
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        wxy wxyVar = this.cde;
        if (wxyVar != null && !wxyVar.edc()) {
            abc(this.cde);
        }
        this.bcd.disconnect();
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.bcd.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.bcd.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        bcd();
        try {
            Object content = this.bcd.getContent();
            int contentLength = this.bcd.getContentLength();
            if (contentLength >= 0) {
                wxy bcd2 = bcd();
                if (!bcd2.edc()) {
                    bcd2.abc(contentLength);
                    abc(bcd2);
                }
            }
            return content;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        bcd();
        String contentEncoding = this.bcd.getContentEncoding();
        abc();
        return contentEncoding;
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        bcd();
        int contentLength = this.bcd.getContentLength();
        abc();
        return contentLength;
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        bcd();
        String contentType = this.bcd.getContentType();
        abc();
        return contentType;
    }

    @Override // java.net.URLConnection
    public long getDate() {
        bcd();
        abc();
        return this.bcd.getDate();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.bcd.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.bcd.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.bcd.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        bcd();
        try {
            tsr tsrVar = this.def;
            if (tsrVar == null || tsrVar.available() == 0) {
                this.def = new tsr(this.bcd.getErrorStream(), true, 4096);
            }
            return this.def;
        } catch (Throwable unused) {
            return this.bcd.getErrorStream();
        }
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        bcd();
        long expiration = this.bcd.getExpiration();
        abc();
        return expiration;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        bcd();
        String headerField = this.bcd.getHeaderField(i);
        abc();
        return headerField;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        bcd();
        long headerFieldDate = this.bcd.getHeaderFieldDate(str, j);
        abc();
        return headerFieldDate;
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        bcd();
        int headerFieldInt = this.bcd.getHeaderFieldInt(str, i);
        abc();
        return headerFieldInt;
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        bcd();
        String headerFieldKey = this.bcd.getHeaderFieldKey(i);
        abc();
        return headerFieldKey;
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        bcd();
        Map<String, List<String>> headerFields = this.bcd.getHeaderFields();
        abc();
        return headerFields;
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        bcd();
        long ifModifiedSince = this.bcd.getIfModifiedSince();
        abc();
        return ifModifiedSince;
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        wxy bcd2 = bcd();
        try {
            tsr tsrVar = new tsr(this.bcd.getInputStream(), false);
            xyz.bcd(bcd2, this.bcd);
            tsrVar.bcd(new abc(bcd2));
            return tsrVar;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.bcd.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        bcd();
        long lastModified = this.bcd.getLastModified();
        abc();
        return lastModified;
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        wxy bcd2 = bcd();
        try {
            srq srqVar = new srq(this.bcd.getOutputStream());
            String requestProperty = this.bcd.getRequestProperty("Content-Type");
            if (requestProperty == null) {
                requestProperty = "";
            }
            bcd2.efg = requestProperty;
            bcd bcdVar = new bcd(bcd2);
            pon ponVar = srqVar.cde;
            synchronized (ponVar.cde) {
                ponVar.cde.add(bcdVar);
            }
            return srqVar;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.bcd.getPermission();
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.bcd.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.bcd.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.bcd.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.bcd.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        bcd();
        try {
            int responseCode = this.bcd.getResponseCode();
            abc();
            return responseCode;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        bcd();
        try {
            String responseMessage = this.bcd.getResponseMessage();
            abc();
            return responseMessage;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.bcd.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.bcd.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.bcd.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.bcd.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.bcd.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.bcd.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.bcd.setDoInput(z);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.bcd.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.bcd.setFixedLengthStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.bcd.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.bcd.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int i) {
        this.bcd.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        bcd();
        try {
            this.bcd.setRequestMethod(str);
        } catch (ProtocolException e) {
            abc(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.bcd.setRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.bcd.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.bcd.toString();
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.bcd.usingProxy();
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        bcd();
        String headerField = this.bcd.getHeaderField(str);
        abc();
        return headerField;
    }

    public final void abc() {
        if (bcd().edc()) {
            return;
        }
        xyz.bcd(bcd(), this.bcd);
    }

    public void abc(Exception exc) {
        wxy bcd2 = bcd();
        xyz.abc(bcd2, exc);
        if (bcd2.edc()) {
            return;
        }
        xyz.bcd(bcd2, this.bcd);
        bcd2.d();
        vwx e = bcd2.e();
        if (e != null) {
            rst.cde.add(new HttpEvent(e));
        }
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        bcd();
        try {
            Object content = this.bcd.getContent(clsArr);
            abc();
            return content;
        } catch (IOException e) {
            abc(e);
            throw e;
        }
    }

    public final void abc(HttpURLConnection httpURLConnection, String str, String str2) {
        if (TextUtils.isEmpty(httpURLConnection.getRequestProperty("net-msg-id"))) {
            httpURLConnection.setRequestProperty("net-msg-id", str);
        }
        if (TextUtils.isEmpty(httpURLConnection.getRequestProperty(HeaderType.X_NUWA_SPAN_ID))) {
            httpURLConnection.setRequestProperty(HeaderType.X_NUWA_SPAN_ID, str2);
        }
        if (TextUtils.isEmpty(httpURLConnection.getRequestProperty(HeaderType.X_NUWA_MICROSERVICE_NAME))) {
            httpURLConnection.setRequestProperty(HeaderType.X_NUWA_MICROSERVICE_NAME, HiAnalyticsManager.APM_EVENT_ID);
        }
    }
}
