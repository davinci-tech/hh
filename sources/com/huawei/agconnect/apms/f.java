package com.huawei.agconnect.apms;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

/* loaded from: classes2.dex */
public class f extends ResponseBody {
    public wxy abc;
    public ResponseBody bcd;
    public BufferedSource cde;

    public f(ResponseBody responseBody, wxy wxyVar) {
        this.abc = wxyVar;
        this.bcd = responseBody;
    }

    @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.bcd.close();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentLength */
    public long getContentLength() {
        return this.bcd.getContentLength();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return this.bcd.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: source */
    public BufferedSource getBodySource() {
        if (this.cde == null) {
            this.cde = Okio.buffer(new e(this, this.bcd.getBodySource()));
        }
        return this.cde;
    }
}
