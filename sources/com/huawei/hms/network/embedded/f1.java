package com.huawei.hms.network.embedded;

import android.text.TextUtils;
import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* loaded from: classes9.dex */
public class f1 extends ResponseBody {
    public static final String d = "ResponseBodyImpl";

    /* renamed from: a, reason: collision with root package name */
    public String f5238a;
    public long b;
    public InputStream c;

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        public String f5239a;
        public InputStream b;
        public long c;
        public Charset d;

        public b inputStream(InputStream inputStream) {
            if (inputStream == null) {
                throw new NullPointerException("inputStream not null in ResponseBody");
            }
            this.b = inputStream;
            return this;
        }

        public b contentType(String str) {
            this.f5239a = str;
            return this;
        }

        public b contentLength(long j) {
            this.c = j;
            return this;
        }

        public b charSet(Charset charset) {
            this.d = charset;
            return this;
        }

        public b charSet(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.d = Charset.forName(str);
                } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
                    Logger.w("ResponseBody", "charSet error", e);
                }
            }
            return this;
        }

        public f1 build() {
            return new f1(this);
        }

        public b(f1 f1Var) {
            this.f5239a = f1Var.f5238a;
            this.c = f1Var.b;
            this.d = f1Var.charSet;
            this.b = f1Var.c;
        }

        public b() {
        }
    }

    public b newBuilder() {
        return new b(this);
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public final InputStream getInputStream() {
        return this.c;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public String getContentType() {
        return this.f5238a;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody
    public long getContentLength() {
        return this.b;
    }

    @Override // com.huawei.hms.network.httpclient.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this.c;
        if (inputStream != null) {
            try {
                IoUtils.closeSecure(inputStream);
            } catch (IllegalBlockingModeException e) {
                Logger.w(d, "closeSecure IllegalBlockingModeException", e);
            }
        }
        Reader reader = this.reader;
        if (reader != null) {
            try {
                IoUtils.closeSecure(reader);
            } catch (IllegalBlockingModeException e2) {
                Logger.w(d, "closeSecure IllegalBlockingModeException", e2);
            }
        }
    }

    public f1(b bVar) {
        this.f5238a = bVar.f5239a;
        this.b = bVar.c;
        this.c = bVar.b;
        this.charSet = bVar.d;
    }
}
