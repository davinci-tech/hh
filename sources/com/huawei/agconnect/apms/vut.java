package com.huawei.agconnect.apms;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.HttpEntityWrapper;

/* loaded from: classes2.dex */
public class vut extends HttpEntityWrapper implements qpo {
    public final HttpEntity abc;
    public final wxy bcd;
    public tsr cde;

    public vut(HttpResponse httpResponse, wxy wxyVar) {
        super(httpResponse.getEntity());
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

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        try {
            this.abc.consumeContent();
        } catch (IOException e) {
            yxw.abc(this.bcd, e);
            throw e;
        }
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        tsr tsrVar = this.cde;
        if (tsrVar != null) {
            return tsrVar;
        }
        try {
            HttpEntity httpEntity = this.abc;
            boolean isChunked = httpEntity instanceof HttpEntityWrapper ? true ^ ((HttpEntityWrapper) httpEntity).isChunked() : true;
            yza.cde.remove(this.bcd);
            this.bcd.tuv = xyz.abc(r0);
            tsr tsrVar2 = new tsr(this.abc.getContent(), isChunked, 4096);
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

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.abc.getContentEncoding();
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public long getContentLength() {
        return this.abc.getContentLength();
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public Header getContentType() {
        return this.abc.getContentType();
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.abc.isChunked();
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.abc.isRepeatable();
    }

    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.abc.isStreaming();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    @Override // org.apache.http.entity.HttpEntityWrapper, org.apache.http.HttpEntity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void writeTo(java.io.OutputStream r4) throws java.io.IOException {
        /*
            r3 = this;
            com.huawei.agconnect.apms.wxy r0 = r3.bcd
            boolean r0 = r0.gfe()
            if (r0 == 0) goto Le
            org.apache.http.HttpEntity r0 = r3.abc
            r0.writeTo(r4)
            return
        Le:
            com.huawei.agconnect.apms.wxy r0 = r3.bcd     // Catch: java.lang.Exception -> L2b
            java.util.concurrent.ConcurrentLinkedQueue<java.lang.Object> r1 = com.huawei.agconnect.apms.yza.cde     // Catch: java.lang.Exception -> L2b
            r1.remove(r0)     // Catch: java.lang.Exception -> L2b
            com.huawei.agconnect.apms.wxy r0 = r3.bcd     // Catch: java.lang.Exception -> L2b
            int r1 = com.huawei.agconnect.apms.xyz.abc(r0)     // Catch: java.lang.Exception -> L2b
            long r1 = (long) r1     // Catch: java.lang.Exception -> L2b
            r0.tuv = r1     // Catch: java.lang.Exception -> L2b
            com.huawei.agconnect.apms.srq r0 = new com.huawei.agconnect.apms.srq     // Catch: java.lang.Exception -> L2b
            r0.<init>(r4)     // Catch: java.lang.Exception -> L2b
            org.apache.http.HttpEntity r4 = r3.abc     // Catch: java.lang.Exception -> L29
            r4.writeTo(r0)     // Catch: java.lang.Exception -> L29
            goto L32
        L29:
            r4 = move-exception
            goto L2d
        L2b:
            r4 = move-exception
            r0 = 0
        L2d:
            com.huawei.agconnect.apms.wxy r1 = r3.bcd
            com.huawei.agconnect.apms.yxw.abc(r1, r4)
        L32:
            if (r0 == 0) goto L3b
            com.huawei.agconnect.apms.wxy r4 = r3.bcd
            long r0 = r0.bcd
            r4.abc(r0)
        L3b:
            com.huawei.agconnect.apms.wxy r4 = r3.bcd
            int r0 = com.huawei.agconnect.apms.xyz.abc(r4)
            long r0 = (long) r0
            r4.uvw = r0
            com.huawei.agconnect.apms.wxy r4 = r3.bcd
            com.huawei.agconnect.apms.yxw.abc(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.vut.writeTo(java.io.OutputStream):void");
    }
}
