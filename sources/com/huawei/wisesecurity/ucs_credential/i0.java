package com.huawei.wisesecurity.ucs_credential;

import android.content.Context;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509TrustManager;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkRequest;
import com.huawei.wisesecurity.ucs.credential.outer.NetworkResponse;
import defpackage.tua;
import defpackage.twb;
import defpackage.twc;
import defpackage.twf;
import defpackage.two;
import defpackage.twu;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes7.dex */
public class i0 implements NetworkCapability {
    public j0 b;
    public Context e;

    public final String c(Response<String> response) {
        if (response.isSuccessful()) {
            return response.getBody();
        }
        try {
            return new String(response.getErrorBody().bytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            StringBuilder e2 = twf.e("getErrorBody fail : ");
            e2.append(e.getMessage());
            twb.e("RemoteRestClient", e2.toString(), new Object[0]);
            return "";
        }
    }

    @Override // com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability
    public void initConfig(int i, int i2) throws twc {
        Context context = this.e;
        try {
            NetworkKit.init(context, new twu(this));
            this.b = (j0) new RestClient.Builder().httpClient(new HttpClient.Builder().connectTimeout(i).callTimeout(i).sslSocketFactory((SSLSocketFactory) SecureSSLSocketFactoryNew.getInstance(context, tua.a()), (X509TrustManager) new SecureX509TrustManager(context)).retryTimeOnConnectionFailure(i2).build()).build().create(j0.class);
        } catch (Exception e) {
            StringBuilder e2 = twf.e("RemoteRestClient init failed, ");
            e2.append(e.getMessage());
            String sb = e2.toString();
            throw two.e("RemoteRestClient", sb, new Object[0], 2001L, sb);
        }
    }

    @Override // com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability
    public NetworkResponse post(NetworkRequest networkRequest) throws IOException {
        NetworkResponse networkResponse = new NetworkResponse();
        Response<String> execute = this.b.a(networkRequest.getUrl(), networkRequest.getHeaders(), networkRequest.getBody()).execute();
        networkResponse.setCode(execute.getCode());
        networkResponse.setHeaders(execute.getHeaders());
        networkResponse.setBody(c(execute));
        return networkResponse;
    }

    @Override // com.huawei.wisesecurity.ucs.credential.outer.NetworkCapability
    public NetworkResponse get(NetworkRequest networkRequest) throws IOException {
        NetworkResponse networkResponse = new NetworkResponse();
        Response<String> execute = this.b.a(networkRequest.getUrl(), networkRequest.getHeaders()).execute();
        networkResponse.setCode(execute.getCode());
        networkResponse.setHeaders(execute.getHeaders());
        networkResponse.setBody(c(execute));
        return networkResponse;
    }

    public i0(Context context) {
        this.e = context;
    }
}
