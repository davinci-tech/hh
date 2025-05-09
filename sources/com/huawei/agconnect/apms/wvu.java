package com.huawei.agconnect.apms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.message.AbstractHttpMessage;

/* loaded from: classes2.dex */
public class wvu implements HttpEntity, qpo {
    public final HttpEntity abc;
    public final wxy bcd;
    public tsr cde;

    public wvu(HttpResponse httpResponse, wxy wxyVar) {
        this.abc = httpResponse.getEntity();
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
        this.bcd.uvw = xyz.abc(r0);
        this.bcd.abc(rqpVar.abc);
        yxw.abc(this.bcd);
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
        tsr tsrVar = this.cde;
        if (tsrVar != null) {
            return tsrVar;
        }
        try {
            Object obj = this.abc;
            boolean z = true;
            if (obj instanceof AbstractHttpMessage) {
                Header lastHeader = ((AbstractHttpMessage) obj).getLastHeader("Transfer-Encoding");
                if (lastHeader != null && "chunked".equalsIgnoreCase(lastHeader.getValue())) {
                    z = false;
                }
            } else if (obj instanceof HttpEntityWrapper) {
                z = true ^ ((HttpEntityWrapper) obj).isChunked();
            }
            yza.cde.remove(this.bcd);
            this.bcd.tuv = xyz.abc(r0);
            tsr tsrVar2 = new tsr(this.abc.getContent(), z, 4096);
            this.cde = tsrVar2;
            tsrVar2.bcd(this);
            return this.cde;
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
        if (this.bcd.gfe()) {
            this.abc.writeTo(outputStream);
            return;
        }
        try {
            yza.cde.remove(this.bcd);
            this.bcd.tuv = xyz.abc(r0);
            srq srqVar = new srq(outputStream);
            this.abc.writeTo(srqVar);
            this.bcd.abc(srqVar.bcd);
            this.bcd.uvw = xyz.abc(r4);
            yxw.abc(this.bcd);
        } catch (IOException e) {
            yxw.abc(this.bcd, e);
            throw e;
        }
    }
}
