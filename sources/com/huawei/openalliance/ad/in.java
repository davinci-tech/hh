package com.huawei.openalliance.ad;

import android.text.TextUtils;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.openalliance.ad.iu;

/* loaded from: classes5.dex */
public class in implements iu {

    /* renamed from: a, reason: collision with root package name */
    private HttpClient f6949a;

    @Override // com.huawei.openalliance.ad.iu
    public void a(iv ivVar, final iu.a aVar) {
        this.f6949a.newSubmit(a(ivVar, "GET")).enqueue(new Callback() { // from class: com.huawei.openalliance.ad.in.1
            @Override // com.huawei.hms.network.httpclient.Callback
            public void onResponse(Submit submit, Response response) {
                iu.a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(in.this.a(response));
                }
            }

            @Override // com.huawei.hms.network.httpclient.Callback
            public void onFailure(Submit submit, Throwable th) {
                ho.d("NetworkKitHttpClient", "onFailure: %s", th.getClass().getSimpleName());
                iu.a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public iw a(Response response) {
        it itVar = new it();
        if (response.getHeaders() != null) {
            for (String str : response.getHeaders().keySet()) {
                itVar.a(str, response.getHeaders().get(str).toString());
            }
        }
        return new iw(itVar, response.getCode(), new is(response.getBody() == null ? null : ((ResponseBody) response.getBody()).getInputStream()), response.getMessage());
    }

    private Request a(iv ivVar, String str) {
        it b = ivVar.b();
        Request.Builder newRequest = this.f6949a.newRequest();
        newRequest.url(ivVar.a()).method(str);
        if (b != null) {
            for (String str2 : b.a()) {
                String a2 = b.a(str2);
                if (!TextUtils.isEmpty(a2)) {
                    newRequest.addHeader(str2, a2);
                }
            }
        }
        if ("POST".equalsIgnoreCase(str)) {
            newRequest.requestBody(RequestBodyProviders.create(ivVar.c()));
        }
        return newRequest.build();
    }

    public in(int i, int i2, int i3) {
        HttpClient.Builder readTimeout = new HttpClient.Builder().callTimeout(i).connectTimeout(i2).readTimeout(i3);
        if (id.a() != null && id.b() != null) {
            readTimeout.sslSocketFactory(id.a(), id.b());
        }
        this.f6949a = readTimeout.build();
    }
}
