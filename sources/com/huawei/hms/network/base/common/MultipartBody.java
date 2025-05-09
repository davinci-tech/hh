package com.huawei.hms.network.base.common;

import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.base.common.trans.ByteString;
import com.huawei.hms.network.base.common.trans.CounterOutputStream;
import com.huawei.hms.network.httpclient.RequestBody;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class MultipartBody extends RequestBody {
    private static final String f = "MultipartBody";

    /* renamed from: a, reason: collision with root package name */
    private final MediaType f5121a;
    private final ByteString b;
    private final List<Part> c;
    private final MediaType d;
    private long e = -1;
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    public static final MediaType FORM = MediaType.get(com.huawei.hms.framework.network.restclient.hwhttp.RequestBody.HEAD_VALUE_CONTENT_TYPE_FORM_DATA);
    private static final byte[] g = {58, 32};
    private static final byte[] h = {13, 10};
    private static final byte[] i = {45, 45};

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final ByteString f5122a;
        private final List<Part> b;
        private MediaType c;

        public Builder setType(MediaType mediaType) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            }
            if (mediaType.type().equals("multipart")) {
                this.c = mediaType;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + this.c);
        }

        public MultipartBody build() {
            if (this.b.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(this);
        }

        public Builder addPart(RequestBody requestBody) {
            return addPart(Part.create(requestBody));
        }

        public Builder addPart(Part part) {
            if (part == null) {
                throw new NullPointerException("part == null");
            }
            this.b.add(part);
            return this;
        }

        public Builder addPart(@Nullable Headers headers, RequestBody requestBody) {
            return addPart(Part.create(headers, requestBody));
        }

        public Builder addFormDataPart(String str, @Nullable String str2, RequestBody requestBody) {
            return addPart(Part.createFormData(str, str2, requestBody));
        }

        public Builder addFormDataPart(String str, String str2) {
            return addPart(Part.createFormData(str, str2));
        }

        public Builder(String str) {
            this.b = new ArrayList();
            this.c = MultipartBody.MIXED;
            this.f5122a = ByteString.encodeUtf8(str);
        }

        public Builder() {
            this(UUID.randomUUID().toString());
        }
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public void writeTo(OutputStream outputStream) {
        a(outputStream, false);
    }

    public MediaType type() {
        return this.f5121a;
    }

    public int size() {
        return this.c.size();
    }

    public List<Part> parts() {
        return this.c;
    }

    public static final class Part {

        /* renamed from: a, reason: collision with root package name */
        @Nullable
        final Headers f5123a;
        final RequestBody b;

        @Nullable
        public Headers headers() {
            return this.f5123a;
        }

        public RequestBody body() {
            return this.b;
        }

        public static Part createFormData(String str, @Nullable String str2, RequestBody requestBody) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            MultipartBody.a(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                MultipartBody.a(sb, str2);
            }
            return create(Headers.of("Content-Disposition", sb.toString()), requestBody);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, RequestBodyProviders.create((MediaType) null, str2));
        }

        public static Part create(RequestBody requestBody) {
            return create(null, requestBody);
        }

        public static Part create(@Nullable Headers headers, RequestBody requestBody) {
            if (requestBody == null) {
                throw new NullPointerException("body == null");
            }
            if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (headers == null || headers.get("Content-Length") == null) {
                return new Part(headers, requestBody);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        private Part(@Nullable Headers headers, RequestBody requestBody) {
            this.f5123a = headers;
            this.b = requestBody;
        }
    }

    public Part part(int i2) {
        return this.c.get(i2);
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public String contentType() {
        MediaType mediaType = this.d;
        if (mediaType == null) {
            return null;
        }
        return mediaType.toString();
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public long contentLength() {
        long j = this.e;
        if (j != -1) {
            return j;
        }
        this.e = a((OutputStream) null, true);
        Logger.i(f, "get the contentLength,and the contentLength =" + this.e);
        return this.e;
    }

    public String boundary() {
        return this.b.utf8();
    }

    static StringBuilder a(StringBuilder sb, String str) {
        String str2;
        sb.append('\"');
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\n') {
                str2 = "%0A";
            } else if (charAt == '\r') {
                str2 = "%0D";
            } else if (charAt != '\"') {
                sb.append(charAt);
            } else {
                str2 = "%22";
            }
            sb.append(str2);
        }
        sb.append('\"');
        return sb;
    }

    private long a(OutputStream outputStream, boolean z) {
        CounterOutputStream counterOutputStream = new CounterOutputStream();
        if (z) {
            outputStream = counterOutputStream;
        }
        int size = this.c.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Part part = this.c.get(i2);
            Headers headers = part.f5123a;
            RequestBody requestBody = part.b;
            outputStream.write(i);
            outputStream.write(StringUtils.getBytes(boundary()));
            outputStream.write(h);
            if (headers != null) {
                int size2 = headers.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    outputStream.write(StringUtils.getBytes(headers.name(i3)));
                    outputStream.write(g);
                    outputStream.write(StringUtils.getBytes(headers.value(i3)));
                    outputStream.write(h);
                }
            }
            String contentType = requestBody.contentType();
            if (contentType != null) {
                outputStream.write(StringUtils.getBytes("Content-Type: "));
                outputStream.write(StringUtils.getBytes(contentType));
                outputStream.write(h);
            }
            long contentLength = requestBody.contentLength();
            if (contentLength != -1) {
                outputStream.write(StringUtils.getBytes("Content-Length: "));
                outputStream.write(StringUtils.getBytes(contentLength));
                outputStream.write(h);
            } else if (z) {
                return -1L;
            }
            byte[] bArr = h;
            outputStream.write(bArr);
            if (z) {
                j += contentLength;
            } else {
                requestBody.writeTo(outputStream);
            }
            outputStream.write(bArr);
        }
        byte[] bArr2 = i;
        outputStream.write(bArr2);
        outputStream.write(StringUtils.getBytes(boundary()));
        outputStream.write(bArr2);
        outputStream.write(h);
        if (z) {
            j += counterOutputStream.getLenth();
            IoUtils.closeSecure((OutputStream) counterOutputStream);
        }
        Logger.v(f, "the length of the requestBody is %s", Long.valueOf(j));
        return j;
    }

    MultipartBody(Builder builder) {
        this.c = builder.b;
        ByteString byteString = builder.f5122a;
        this.b = byteString;
        MediaType mediaType = builder.c;
        this.f5121a = mediaType;
        this.d = MediaType.get(mediaType + "; boundary=" + byteString.utf8());
    }
}
