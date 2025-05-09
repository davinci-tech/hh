package com.huawei.hms.network.base.common;

import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.base.common.trans.ByteString;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public class ResponseBodyProviders {

    /* renamed from: a, reason: collision with root package name */
    private static final String f5129a = "ResponseBodyProviders";
    private static final Charset b = Charset.forName("UTF-8");

    public static ResponseBody create(@Nullable MediaType mediaType, byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        return create(mediaType, bArr.length, new ByteArrayInputStream(bArr));
    }

    public static ResponseBody create(@Nullable MediaType mediaType, String str) {
        if (str == null) {
            throw new NullPointerException("content == null");
        }
        Charset charset = b;
        if (mediaType != null) {
            Charset charset2 = mediaType.charset();
            if (charset2 == null) {
                mediaType = MediaType.parse(mediaType + "; charset=utf-8");
            } else {
                charset = charset2;
            }
        }
        return create(mediaType, str.getBytes(charset));
    }

    public static ResponseBody create(@Nullable MediaType mediaType, ByteString byteString) {
        if (byteString != null) {
            return create(mediaType, byteString.toByteArray());
        }
        throw new NullPointerException("content == null");
    }

    public static ResponseBody create(@Nullable final MediaType mediaType, final long j, final InputStream inputStream) {
        if (inputStream != null) {
            return new ResponseBody() { // from class: com.huawei.hms.network.base.common.ResponseBodyProviders.1
                @Override // com.huawei.hms.network.httpclient.ResponseBody
                public InputStream getInputStream() {
                    return inputStream;
                }

                @Override // com.huawei.hms.network.httpclient.ResponseBody
                public String getContentType() {
                    MediaType mediaType2 = mediaType;
                    if (mediaType2 == null) {
                        return null;
                    }
                    return mediaType2.toString();
                }

                @Override // com.huawei.hms.network.httpclient.ResponseBody
                public long getContentLength() {
                    return j;
                }

                @Override // com.huawei.hms.network.httpclient.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    InputStream inputStream2 = inputStream;
                    if (inputStream2 != null) {
                        try {
                            IoUtils.closeSecure(inputStream2);
                        } catch (IllegalBlockingModeException unused) {
                            Logger.w(ResponseBodyProviders.f5129a, "closeSecure IllegalBlockingModeException");
                        }
                    }
                    Reader reader = this.reader;
                    if (reader != null) {
                        try {
                            IoUtils.closeSecure(reader);
                        } catch (IllegalBlockingModeException unused2) {
                            Logger.w(ResponseBodyProviders.f5129a, "closeSecure IllegalBlockingModeException");
                        }
                    }
                }
            };
        }
        throw new NullPointerException("inputStream == null");
    }
}
