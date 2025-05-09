package com.huawei.hwnetworkmodel;

import defpackage.kue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* loaded from: classes5.dex */
public class WrappedHttpEntity implements HttpEntity {
    private HttpEntity mEntity;
    private kue mInputStream;

    public WrappedHttpEntity(HttpEntity httpEntity) {
        this.mEntity = httpEntity;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return this.mEntity.isRepeatable();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.mEntity.isChunked();
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return this.mEntity.getContentLength();
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.mEntity.getContentType();
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.mEntity.getContentEncoding();
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        kue kueVar = new kue(this.mEntity.getContent());
        this.mInputStream = kueVar;
        return kueVar;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        this.mEntity.writeTo(outputStream);
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.mEntity.isStreaming();
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException {
        this.mEntity.consumeContent();
    }
}
