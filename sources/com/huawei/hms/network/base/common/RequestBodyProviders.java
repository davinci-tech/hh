package com.huawei.hms.network.base.common;

import com.huawei.hms.framework.common.CheckParamUtils;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.base.common.trans.ByteString;
import com.huawei.hms.network.base.common.trans.FileBinary;
import com.huawei.hms.network.httpclient.RequestBody;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class RequestBodyProviders {
    public static final String DEFAULT_CONTENT_TYPE = "text/plain; charset=UTF-8";

    /* renamed from: a, reason: collision with root package name */
    private static final Charset f5124a = Charset.forName("UTF-8");

    public static RequestBody create(@Nullable final String str, final byte[] bArr) {
        if (bArr != null) {
            return new RequestBody() { // from class: com.huawei.hms.network.base.common.RequestBodyProviders.1
                @Override // com.huawei.hms.network.httpclient.RequestBody
                public void writeTo(OutputStream outputStream) {
                    outputStream.write(bArr);
                }

                @Override // com.huawei.hms.network.httpclient.RequestBody
                @Nullable
                public String contentType() {
                    String str2 = str;
                    return str2 == null ? "text/plain; charset=UTF-8" : str2;
                }

                @Override // com.huawei.hms.network.httpclient.RequestBody
                public long contentLength() {
                    return bArr.length;
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(String str) {
        if (str != null) {
            return create("text/plain; charset=UTF-8", StringUtils.str2Byte(str));
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        CheckParamUtils.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: com.huawei.hms.network.base.common.RequestBodyProviders.2
            @Override // com.huawei.hms.network.httpclient.RequestBody
            public void writeTo(OutputStream outputStream) {
                outputStream.write(bArr, i, i2);
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            @Nullable
            public String contentType() {
                MediaType mediaType2 = MediaType.this;
                if (mediaType2 == null) {
                    return null;
                }
                return mediaType2.toString();
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            public long contentLength() {
                return i2;
            }
        };
    }

    public static RequestBody create(@Nullable MediaType mediaType, String str) {
        if (str == null) {
            throw new NullPointerException("content == null");
        }
        Charset charset = f5124a;
        if (mediaType != null) {
            Charset charset2 = mediaType.charset();
            if (charset2 == null) {
                mediaType = MediaType.parse(mediaType + "; charset=utf-8");
            } else {
                charset = charset2;
            }
        }
        byte[] bytes = str.getBytes(charset);
        return create(mediaType, bytes, 0, bytes.length);
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final File file) {
        if (file != null) {
            return new RequestBody() { // from class: com.huawei.hms.network.base.common.RequestBodyProviders.4
                @Override // com.huawei.hms.network.httpclient.RequestBody
                public void writeTo(OutputStream outputStream) {
                    new FileBinary(file).onWriteBinary(outputStream);
                }

                @Override // com.huawei.hms.network.httpclient.RequestBody
                @Nullable
                public String contentType() {
                    MediaType mediaType2 = MediaType.this;
                    if (mediaType2 == null) {
                        return null;
                    }
                    return mediaType2.toString();
                }

                @Override // com.huawei.hms.network.httpclient.RequestBody
                public long contentLength() {
                    return file.length();
                }
            };
        }
        throw new NullPointerException("content == null");
    }

    public static RequestBody create(@Nullable final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: com.huawei.hms.network.base.common.RequestBodyProviders.3
            @Override // com.huawei.hms.network.httpclient.RequestBody
            public void writeTo(OutputStream outputStream) {
                outputStream.write(StringUtils.getBytes(byteString.utf8()));
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            @Nullable
            public String contentType() {
                MediaType mediaType2 = MediaType.this;
                if (mediaType2 == null) {
                    return null;
                }
                return mediaType2.toString();
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            public long contentLength() {
                return byteString.size();
            }
        };
    }
}
