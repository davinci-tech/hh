package com.huawei.hms.framework.network.restclient.hwhttp;

import com.huawei.hms.framework.common.Logger;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* loaded from: classes9.dex */
public class FormBody extends RequestBody {
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
    private static final String TAG = "FormBody";
    private byte[] formbody;
    private final List<String> nameAndValues;

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public void writeTo(OutputStream outputStream) throws IOException {
    }

    private FormBody(Builder builder) {
        this.nameAndValues = builder.nameAndValues;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public byte[] body() {
        if (this.formbody == null) {
            StringBuilder sb = new StringBuilder();
            int size = this.nameAndValues.size();
            for (int i = 0; i < size; i += 2) {
                if (i > 0) {
                    sb.append('&');
                }
                sb.append(this.nameAndValues.get(i));
                sb.append('=');
                sb.append(this.nameAndValues.get(i + 1));
            }
            try {
                this.formbody = sb.toString().getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                this.formbody = new byte[0];
                Logger.w(TAG, "UnsupportedEncodingException", e);
            }
        }
        return (byte[]) this.formbody.clone();
    }

    @Deprecated
    public static final class Builder {
        private final List<String> nameAndValues = new ArrayList();

        public Builder add(String str, String str2) {
            if (str != null && str2 != null) {
                this.nameAndValues.add(str);
                this.nameAndValues.add(str2);
            }
            return this;
        }

        public FormBody build() {
            return new FormBody(this);
        }
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.RequestBody
    public String contentType() {
        return CONTENT_TYPE;
    }
}
