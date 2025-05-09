package com.huawei.agconnect.apms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* loaded from: classes2.dex */
public class xwv implements HttpEntity, qpo {
    public final HttpEntity abc;
    public final wxy bcd;

    public xwv(HttpEntity httpEntity, wxy wxyVar) {
        this.abc = httpEntity;
        this.bcd = wxyVar;
    }

    @Override // com.huawei.agconnect.apms.qpo
    public void abc(rqp rqpVar) {
        ((onm) rqpVar.getSource()).abc(this);
        yxw.abc(this.bcd, rqpVar.bcd);
    }

    @Override // com.huawei.agconnect.apms.qpo
    public void bcd(rqp rqpVar) {
        ((onm) rqpVar.getSource()).abc(this);
        this.bcd.bcd(rqpVar.abc);
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        try {
            this.abc.consumeContent();
        } catch (IOException e) {
            yxw.abc(this.bcd, e);
            throw e;
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        try {
            if (this.bcd.c()) {
                return this.abc.getContent();
            }
            tsr tsrVar = new tsr(this.abc.getContent(), false);
            tsrVar.bcd(this);
            return tsrVar;
        } catch (IOException e) {
            yxw.abc(this.bcd, e);
            throw e;
        } catch (IllegalStateException e2) {
            yxw.abc(this.bcd, e2);
            throw e2;
        }
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.abc.getContentEncoding();
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.abc.getContentLength();
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.abc.getContentType();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.abc.isChunked();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.abc.isRepeatable();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.abc.isStreaming();
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            if (this.bcd.c()) {
                this.abc.writeTo(outputStream);
                return;
            }
            srq srqVar = new srq(outputStream);
            this.abc.writeTo(srqVar);
            this.bcd.bcd(srqVar.bcd);
        } catch (IOException e) {
            yxw.abc(this.bcd, e);
            throw e;
        }
    }
}
