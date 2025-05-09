package com.huawei.hms.network.base.common;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.network.httpclient.RequestBody;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class FormBody extends RequestBody {
    private static final String c = "FormBody";
    private static final String d = "application/x-www-form-urlencoded; charset=UTF-8";

    /* renamed from: a, reason: collision with root package name */
    private final List<String> f5116a;
    private final byte[] b;

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.b);
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private final List<String> f5117a = new ArrayList();

        public FormBody build() {
            return new FormBody(this);
        }

        public Builder add(String str, String str2) {
            if (str != null && str2 != null) {
                this.f5117a.add(str);
                this.f5117a.add(str2);
            }
            return this;
        }
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public String contentType() {
        return d;
    }

    @Override // com.huawei.hms.network.httpclient.RequestBody
    public long contentLength() {
        if (this.b.length == 0) {
            return -1L;
        }
        return r0.length;
    }

    private byte[] a() {
        StringBuilder sb = new StringBuilder();
        int size = this.f5116a.size();
        for (int i = 0; i < size; i += 2) {
            if (i > 0) {
                sb.append('&');
            }
            sb.append(this.f5116a.get(i));
            sb.append('=');
            sb.append(this.f5116a.get(i + 1));
        }
        try {
            return sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            byte[] bArr = new byte[0];
            Logger.w(c, "UnsupportedEncodingException", e);
            return bArr;
        }
    }

    private FormBody(Builder builder) {
        this.f5116a = builder.f5117a;
        this.b = a();
    }
}
