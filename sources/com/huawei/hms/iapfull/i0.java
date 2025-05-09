package com.huawei.hms.iapfull;

import com.huawei.hms.framework.network.restclient.hwhttp.Interceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import java.io.IOException;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
public abstract class i0 implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    protected int f4718a = 1;
    protected int b = 0;

    abstract boolean a(String str);

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        byte[] bArr = null;
        do {
            try {
                if (this.b != 0) {
                    Thread.sleep(300L);
                }
                this.b++;
                response = chain.proceed(request);
                bArr = response.getBody().bytes();
                if (!a(new String(bArr, Charset.forName("UTF-8")))) {
                    return com.huawei.hms.iapfull.network.f.a(response, bArr);
                }
            } catch (InterruptedException unused) {
                y0.b("AutoRetryInterceptor", "reQueryResult occur InterruptedException");
            }
        } while (this.b < this.f4718a);
        this.b = 0;
        if (response == null || bArr == null) {
            throw new IOException("Canceled");
        }
        return com.huawei.hms.iapfull.network.f.a(response, bArr);
    }
}
