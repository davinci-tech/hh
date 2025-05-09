package com.huawei.hwnetworkmodel;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.params.HttpParams;

/* loaded from: classes5.dex */
public class WrappedHttpResponse implements HttpResponse {
    private static final String TAG = "WrappedHttpResponse";
    private HttpResponse mHttpResponse;
    private WrappedHttpEntity mWrappedHttpEntity;

    public WrappedHttpResponse(HttpResponse httpResponse) {
        if (httpResponse == null) {
            LogUtil.h(TAG, "WrappedHttpResponse null");
        } else {
            this.mHttpResponse = httpResponse;
            this.mWrappedHttpEntity = new WrappedHttpEntity(httpResponse.getEntity());
        }
    }

    @Override // org.apache.http.HttpResponse
    public void setStatusLine(StatusLine statusLine) {
        this.mHttpResponse.setStatusLine(statusLine);
    }

    @Override // org.apache.http.HttpResponse
    public void setStatusLine(ProtocolVersion protocolVersion, int i) {
        this.mHttpResponse.setStatusLine(protocolVersion, i);
    }

    @Override // org.apache.http.HttpResponse
    public void setStatusLine(ProtocolVersion protocolVersion, int i, String str) {
        this.mHttpResponse.setStatusLine(protocolVersion, i, str);
    }

    @Override // org.apache.http.HttpResponse
    public StatusLine getStatusLine() {
        return this.mHttpResponse.getStatusLine();
    }

    @Override // org.apache.http.HttpResponse
    public void setStatusCode(int i) throws IllegalStateException {
        this.mHttpResponse.setStatusCode(i);
    }

    @Override // org.apache.http.HttpResponse
    public void setReasonPhrase(String str) throws IllegalStateException {
        this.mHttpResponse.setReasonPhrase(str);
    }

    @Override // org.apache.http.HttpResponse
    public HttpEntity getEntity() {
        return this.mWrappedHttpEntity;
    }

    @Override // org.apache.http.HttpResponse
    public void setEntity(HttpEntity httpEntity) {
        LogUtil.b(TAG, "setEntity new a WrappedHttpEntity");
        this.mWrappedHttpEntity = new WrappedHttpEntity(httpEntity);
    }

    @Override // org.apache.http.HttpResponse
    public Locale getLocale() {
        return this.mHttpResponse.getLocale();
    }

    @Override // org.apache.http.HttpResponse
    public void setLocale(Locale locale) {
        this.mHttpResponse.setLocale(locale);
    }

    @Override // org.apache.http.HttpMessage
    public void addHeader(String str, String str2) {
        this.mHttpResponse.addHeader(str, str2);
    }

    @Override // org.apache.http.HttpMessage
    public void addHeader(Header header) {
        this.mHttpResponse.addHeader(header);
    }

    @Override // org.apache.http.HttpMessage
    public void setHeader(Header header) {
        this.mHttpResponse.setHeader(header);
    }

    @Override // org.apache.http.HttpMessage
    public void setHeader(String str, String str2) {
        this.mHttpResponse.setHeader(str, str2);
    }

    @Override // org.apache.http.HttpMessage
    public ProtocolVersion getProtocolVersion() {
        return this.mHttpResponse.getProtocolVersion();
    }

    @Override // org.apache.http.HttpMessage
    public boolean containsHeader(String str) {
        return this.mHttpResponse.containsHeader(str);
    }

    @Override // org.apache.http.HttpMessage
    public Header[] getHeaders(String str) {
        return this.mHttpResponse.getHeaders(str);
    }

    @Override // org.apache.http.HttpMessage
    public Header getFirstHeader(String str) {
        return this.mHttpResponse.getFirstHeader(str);
    }

    @Override // org.apache.http.HttpMessage
    public Header getLastHeader(String str) {
        return this.mHttpResponse.getLastHeader(str);
    }

    @Override // org.apache.http.HttpMessage
    public Header[] getAllHeaders() {
        return this.mHttpResponse.getAllHeaders();
    }

    @Override // org.apache.http.HttpMessage
    public void removeHeader(Header header) {
        this.mHttpResponse.removeHeader(header);
    }

    @Override // org.apache.http.HttpMessage
    public void removeHeaders(String str) {
        this.mHttpResponse.removeHeaders(str);
    }

    @Override // org.apache.http.HttpMessage
    public void setHeaders(Header[] headerArr) {
        this.mHttpResponse.setHeaders(headerArr);
    }

    @Override // org.apache.http.HttpMessage
    public HeaderIterator headerIterator() {
        return this.mHttpResponse.headerIterator();
    }

    @Override // org.apache.http.HttpMessage
    public HeaderIterator headerIterator(String str) {
        return this.mHttpResponse.headerIterator(str);
    }

    @Override // org.apache.http.HttpMessage
    public HttpParams getParams() {
        return this.mHttpResponse.getParams();
    }

    @Override // org.apache.http.HttpMessage
    public void setParams(HttpParams httpParams) {
        this.mHttpResponse.setParams(httpParams);
    }
}
