package com.huawei.maps.offlinedata.network.converter;

import android.text.TextUtils;
import com.huawei.hms.network.httpclient.RequestBody;
import com.huawei.maps.offlinedata.utils.d;
import com.huawei.maps.offlinedata.utils.g;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class a {
    public static RequestBody a(String str, final byte[] bArr) {
        b bVar = new b();
        if (str == null) {
            str = "text/plain; charset=UTF-8";
        }
        bVar.a(str);
        bVar.a(bArr);
        final String a2 = d.a(bVar);
        if (bArr == null || TextUtils.isEmpty(a2)) {
            throw new NullPointerException("content == null");
        }
        return new RequestBody() { // from class: com.huawei.maps.offlinedata.network.converter.a.1
            @Override // com.huawei.hms.network.httpclient.RequestBody
            public String contentType() {
                return a2;
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            public void writeTo(OutputStream outputStream) throws IOException {
                try {
                    outputStream.write(bArr);
                } catch (IOException e) {
                    g.c("RequestBodyProviders", "the requestBody with writeTo has other error");
                    throw new IOException(e.getMessage());
                }
            }
        };
    }

    public static RequestBody b(final String str, final byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        return new RequestBody() { // from class: com.huawei.maps.offlinedata.network.converter.a.2
            @Override // com.huawei.hms.network.httpclient.RequestBody
            public String contentType() {
                String str2 = str;
                return str2 == null ? "text/plain; charset=UTF-8" : str2;
            }

            @Override // com.huawei.hms.network.httpclient.RequestBody
            public void writeTo(OutputStream outputStream) throws IOException {
                try {
                    outputStream.write(bArr);
                } catch (IOException e) {
                    g.c("RequestBodyProviders", "the requestBody with writeTo has other error");
                    throw new IOException(e.getMessage());
                }
            }
        };
    }
}
