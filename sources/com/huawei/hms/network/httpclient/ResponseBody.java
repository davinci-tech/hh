package com.huawei.hms.network.httpclient;

import com.huawei.hms.framework.common.IoUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public abstract class ResponseBody implements Closeable {
    public Charset charSet;
    public Reader reader;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public abstract long getContentLength();

    public abstract String getContentType();

    public abstract InputStream getInputStream();

    public final Reader charStream() {
        InputStream inputStream = getInputStream();
        if (inputStream == null) {
            return null;
        }
        if (this.reader == null) {
            Charset charset = this.charSet;
            if (charset == null) {
                charset = Charset.forName("UTF-8");
            }
            this.reader = new InputStreamReader(inputStream, charset);
        }
        return this.reader;
    }

    public final byte[] bytes() throws IOException {
        long contentLength = getContentLength();
        try {
            if (contentLength > 2147483647L) {
                throw new IOException("Cannot buffer entire body for content length: " + contentLength);
            }
            InputStream inputStream = getInputStream();
            if (inputStream == null) {
                return new byte[0];
            }
            byte[] byteArray = IoUtils.toByteArray(inputStream);
            if (contentLength != -1 && contentLength != byteArray.length) {
                throw new IOException("Content-Length (" + contentLength + ") and stream length (" + byteArray.length + ") disagree");
            }
            return byteArray;
        } finally {
            close();
        }
    }
}
