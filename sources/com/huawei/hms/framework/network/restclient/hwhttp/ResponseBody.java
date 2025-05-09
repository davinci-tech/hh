package com.huawei.hms.framework.network.restclient.hwhttp;

import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

@Deprecated
/* loaded from: classes.dex */
public class ResponseBody implements Closeable {
    private static final Charset DEFAULT_CHAESET = Charset.forName("UTF-8");
    private Charset charSet;
    private long contentLength;
    private String contentType;
    private InputStream inputStream;
    private Reader reader;

    private ResponseBody(Builder builder) {
        this.contentType = builder.contentType;
        this.contentLength = builder.contentLength;
        this.inputStream = builder.inputStream;
        this.charSet = builder.charSet;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            IoUtils.closeSecure(inputStream);
        }
        Reader reader = this.reader;
        if (reader != null) {
            IoUtils.closeSecure(reader);
        }
    }

    public Charset getCharSet() {
        Charset charset = this.charSet;
        return charset == null ? DEFAULT_CHAESET : charset;
    }

    public String getContentType() {
        return this.contentType;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public final InputStream getInputStream() {
        return this.inputStream;
    }

    public final byte[] bytes() throws IOException {
        try {
            if (this.contentLength > 2147483647L) {
                throw new IOException("Cannot buffer entire body for content length: " + this.contentLength);
            }
            InputStream inputStream = this.inputStream;
            if (inputStream == null) {
                return new byte[0];
            }
            byte[] byteArray = IoUtils.toByteArray(inputStream);
            long j = this.contentLength;
            if (j != -1 && j != byteArray.length) {
                throw new IOException("Content-Length (" + this.contentLength + ") and stream length (" + byteArray.length + ") disagree");
            }
            return byteArray;
        } finally {
            close();
        }
    }

    public final Reader charStream() {
        InputStream inputStream = this.inputStream;
        if (inputStream == null) {
            return null;
        }
        if (this.reader == null) {
            Charset charset = this.charSet;
            if (charset == null) {
                charset = DEFAULT_CHAESET;
            }
            this.reader = new InputStreamReader(inputStream, charset);
        }
        return this.reader;
    }

    @Deprecated
    /* loaded from: classes9.dex */
    public static final class Builder {
        private Charset charSet;
        private long contentLength;
        private String contentType;
        private InputStream inputStream;

        public Builder() {
        }

        public Builder(ResponseBody responseBody) {
            this.contentType = responseBody.contentType;
            this.contentLength = responseBody.contentLength;
            this.charSet = responseBody.charSet;
            this.inputStream = responseBody.inputStream;
        }

        public Builder contentType(String str) {
            this.contentType = str;
            return this;
        }

        public Builder contentLength(long j) {
            this.contentLength = j;
            return this;
        }

        public Builder inputStream(InputStream inputStream) {
            if (inputStream == null) {
                throw new NullPointerException("inputStream not null in ResponseBody");
            }
            this.inputStream = inputStream;
            return this;
        }

        public Builder charSet(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.charSet = Charset.forName(str);
                } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
                    Logger.w("ResponseBody", "charSet error", e);
                }
            }
            return this;
        }

        public Builder charSet(Charset charset) {
            this.charSet = charset;
            return this;
        }

        public ResponseBody build() {
            return new ResponseBody(this);
        }
    }
}
