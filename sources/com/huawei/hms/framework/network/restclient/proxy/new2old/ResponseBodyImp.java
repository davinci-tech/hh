package com.huawei.hms.framework.network.restclient.proxy.new2old;

import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* loaded from: classes9.dex */
public class ResponseBodyImp extends ResponseBody {
    private static final String TAG = "ResponseBodyImp";
    private long contentLength;
    private String contentType;
    private InputStream inputStream;

    public ResponseBodyImp(ResponseBody responseBody) {
        this.contentType = responseBody.getContentType();
        this.contentLength = responseBody.getContentLength();
        this.inputStream = responseBody.getInputStream();
        this.reader = responseBody.charStream();
    }

    private ResponseBodyImp(Builder builder) {
        this.contentType = builder.contentType;
        this.contentLength = builder.contentLength;
        this.inputStream = builder.inputStream;
        this.charSet = builder.charSet;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            try {
                IoUtils.closeSecure(inputStream);
            } catch (IllegalBlockingModeException e) {
                Logger.w(TAG, "closeSecure IllegalBlockingModeException", e);
            }
        }
        if (this.reader != null) {
            try {
                IoUtils.closeSecure(this.reader);
            } catch (IllegalBlockingModeException e2) {
                Logger.w(TAG, "closeSecure IllegalBlockingModeException", e2);
            }
        }
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public String getContentType() {
        return this.contentType;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public long getContentLength() {
        return this.contentLength;
    }

    public Charset getCharSet() {
        return this.charSet;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public final InputStream getInputStream() {
        return this.inputStream;
    }

    public static final class Builder {
        private Charset charSet;
        private long contentLength;
        private String contentType;
        private InputStream inputStream;

        public Builder() {
        }

        public Builder(ResponseBodyImp responseBodyImp) {
            this.contentType = responseBodyImp.contentType;
            this.contentLength = responseBodyImp.contentLength;
            this.charSet = responseBodyImp.charSet;
            this.inputStream = responseBodyImp.inputStream;
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

        public ResponseBodyImp build() {
            return new ResponseBodyImp(this);
        }
    }
}
