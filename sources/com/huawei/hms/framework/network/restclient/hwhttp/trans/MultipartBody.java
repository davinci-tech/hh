package com.huawei.hms.framework.network.restclient.hwhttp.trans;

import com.huawei.hms.framework.common.IoUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.restclient.Headers;
import com.huawei.hms.framework.network.restclient.hwhttp.MediaType;
import com.huawei.hms.framework.network.restclient.hwhttp.RequestBody;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;

@Deprecated
/* loaded from: classes4.dex */
public class MultipartBody extends RequestBody {
    private static final String TAG = "MultipartBody";
    private final ByteString boundary;
    private long contentLength = -1;
    private final MediaType contentType;
    private final MediaType originalType;
    private final List<Part> parts;
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    public static final MediaType FORM = MediaType.get(RequestBody.HEAD_VALUE_CONTENT_TYPE_FORM_DATA);
    private static final byte[] COLONSPACE = {58, 32};
    private static final byte[] CRLF = {13, 10};
    private static final byte[] DASHDASH = {45, 45};

    MultipartBody(Builder builder) {
        ByteString byteString = builder.boundaryString;
        this.boundary = byteString;
        MediaType mediaType = builder.type;
        this.originalType = mediaType;
        this.parts = builder.parts;
        this.contentType = MediaType.get(mediaType + "; boundary=" + byteString.utf8());
    }

    public MediaType type() {
        return this.originalType;
    }

    public String boundary() {
        return this.boundary.utf8();
    }

    public int size() {
        return this.parts.size();
    }

    public List<Part> parts() {
        return this.parts;
    }

    public Part part(int i) {
        return this.parts.get(i);
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public String contentType() {
        MediaType mediaType = this.contentType;
        if (mediaType == null) {
            return null;
        }
        return mediaType.toString();
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public void writeTo(OutputStream outputStream) throws IOException {
        writeOrCountBytes(outputStream, false);
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public long contentLength() throws IOException {
        long j = this.contentLength;
        if (j != -1) {
            return j;
        }
        this.contentLength = writeOrCountBytes(null, true);
        Logger.i(TAG, "get the contentLength,and the contentLength =" + this.contentLength);
        return this.contentLength;
    }

    private long writeOrCountBytes(OutputStream outputStream, boolean z) throws IOException {
        CounterOutputStream counterOutputStream = new CounterOutputStream();
        if (z) {
            outputStream = counterOutputStream;
        }
        int size = this.parts.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            Part part = this.parts.get(i);
            Headers headers = part.headers;
            RequestBody requestBody = part.body;
            outputStream.write(DASHDASH);
            outputStream.write(StringUtils.getBytes(boundary()));
            outputStream.write(CRLF);
            if (headers != null) {
                int size2 = headers.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    outputStream.write(StringUtils.getBytes(headers.name(i2)));
                    outputStream.write(COLONSPACE);
                    outputStream.write(StringUtils.getBytes(headers.value(i2)));
                    outputStream.write(CRLF);
                }
            }
            String contentType = requestBody.contentType();
            if (contentType != null) {
                outputStream.write(StringUtils.getBytes("Content-Type: "));
                outputStream.write(StringUtils.getBytes(contentType));
                outputStream.write(CRLF);
            }
            long contentLength = requestBody.contentLength();
            if (contentLength != -1) {
                outputStream.write(StringUtils.getBytes("Content-Length: "));
                outputStream.write(StringUtils.getBytes(contentLength));
                outputStream.write(CRLF);
            } else if (z) {
                return -1L;
            }
            byte[] bArr = CRLF;
            outputStream.write(bArr);
            if (z) {
                j += contentLength;
            } else {
                requestBody.writeTo(outputStream);
            }
            outputStream.write(bArr);
        }
        byte[] bArr2 = DASHDASH;
        outputStream.write(bArr2);
        outputStream.write(StringUtils.getBytes(boundary()));
        outputStream.write(bArr2);
        outputStream.write(CRLF);
        if (z) {
            j += counterOutputStream.getLenth();
            IoUtils.closeSecure((OutputStream) counterOutputStream);
        }
        Logger.v(TAG, "the length of the requestBody is %s", Long.valueOf(j));
        return j;
    }

    static StringBuilder appendQuotedString(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\n') {
                sb.append("%0A");
            } else if (charAt == '\r') {
                sb.append("%0D");
            } else if (charAt == '\"') {
                sb.append("%22");
            } else {
                sb.append(charAt);
            }
        }
        sb.append('\"');
        return sb;
    }

    @Deprecated
    public static final class Part {
        final RequestBody body;

        @Nullable
        final Headers headers;

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
            if (headers != null && headers.get("Content-Length") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
            return new Part(headers, requestBody);
        }

        public static Part createFormData(String str, String str2) {
            return createFormData(str, null, RequestBody.create((MediaType) null, str2));
        }

        public static Part createFormData(String str, @Nullable String str2, RequestBody requestBody) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            StringBuilder sb = new StringBuilder("form-data; name=");
            MultipartBody.appendQuotedString(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                MultipartBody.appendQuotedString(sb, str2);
            }
            return create(Headers.of("Content-Disposition", sb.toString()), requestBody);
        }

        private Part(@Nullable Headers headers, RequestBody requestBody) {
            this.body = requestBody;
            this.headers = headers;
        }

        @Nullable
        public Headers headers() {
            return this.headers;
        }

        public RequestBody body() {
            return this.body;
        }
    }

    @Deprecated
    public static final class Builder {
        private final ByteString boundaryString;
        private final List<Part> parts;
        private MediaType type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String str) {
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundaryString = ByteString.encodeUtf8(str);
        }

        public Builder setType(MediaType mediaType) {
            if (mediaType == null) {
                throw new NullPointerException("type == null");
            }
            if (!mediaType.type().equals("multipart")) {
                throw new IllegalArgumentException("multipart != " + mediaType);
            }
            this.type = mediaType;
            return this;
        }

        public Builder addPart(RequestBody requestBody) {
            return addPart(Part.create(requestBody));
        }

        public Builder addPart(@Nullable Headers headers, RequestBody requestBody) {
            return addPart(Part.create(headers, requestBody));
        }

        public Builder addFormDataPart(String str, String str2) {
            return addPart(Part.createFormData(str, str2));
        }

        public Builder addFormDataPart(String str, @Nullable String str2, RequestBody requestBody) {
            return addPart(Part.createFormData(str, str2, requestBody));
        }

        public Builder addPart(Part part) {
            if (part == null) {
                throw new NullPointerException("part == null");
            }
            this.parts.add(part);
            return this;
        }

        public MultipartBody build() {
            if (this.parts.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new MultipartBody(this);
        }
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    @Deprecated
    public byte[] body() {
        return new byte[0];
    }
}
