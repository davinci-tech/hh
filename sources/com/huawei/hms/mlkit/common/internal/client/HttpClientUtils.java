package com.huawei.hms.mlkit.common.internal.client;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactoryNew;
import com.huawei.secure.android.common.ssl.SecureX509SingleInstance;
import com.huawei.secure.android.common.ssl.hostname.StrictHostnameVerifier;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes9.dex */
public class HttpClientUtils {
    public static final String TAG = "HttpClientUtils";
    public static final int TIMEOUT_SECONDS = 20000;
    public static HttpClientUtils instance;
    public HttpClient okHttpClient;

    private Request buildRequest(String str, Map<String, String> map, String str2) {
        return requestBuilder(str, map, str2).build();
    }

    public static HttpClientUtils getInstance() {
        HttpClientUtils httpClientUtils;
        synchronized (HttpClientUtils.class) {
            if (instance == null) {
                instance = new HttpClientUtils();
            }
            httpClientUtils = instance;
        }
        return httpClientUtils;
    }

    private Request.Builder requestBuilder(String str, Map<String, String> map, String str2) {
        Request.Builder newRequest = this.okHttpClient.newRequest();
        newRequest.url(str);
        newRequest.method("POST");
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                newRequest.addHeader(entry.getKey(), entry.getValue());
            }
            newRequest.requestBody(RequestBodyProviders.create(MediaType.parse("application/json; charset=utf-8"), str2));
        }
        return newRequest;
    }

    public <T> Response<T> httpPost(String str, Map<String, String> map, String str2) throws IOException {
        return (Response<T>) this.okHttpClient.newSubmit(buildRequest(str, map, str2)).execute();
    }

    public void initHttpsClient(Context context) {
        HttpClient.Builder builder = new HttpClient.Builder();
        try {
            builder.sslSocketFactory((SSLSocketFactory) SecureSSLSocketFactoryNew.getInstance(context), (X509TrustManager) SecureX509SingleInstance.getInstance(context));
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        } catch (IllegalAccessException e2) {
            Log.e(TAG, e2.getMessage());
        } catch (KeyManagementException e3) {
            Log.e(TAG, e3.getMessage());
        } catch (KeyStoreException e4) {
            Log.e(TAG, e4.getMessage());
        } catch (NoSuchAlgorithmException e5) {
            Log.e(TAG, e5.getMessage());
        } catch (CertificateException e6) {
            Log.e(TAG, e6.getMessage());
        }
        builder.hostnameVerifier((HostnameVerifier) new StrictHostnameVerifier()).connectTimeout(20000).callTimeout(20000).readTimeout(20000);
        this.okHttpClient = builder.build();
        NetworkKit.init(context, new NetworkKit.Callback() { // from class: com.huawei.hms.mlkit.common.internal.client.HttpClientUtils.1
            @Override // com.huawei.hms.network.NetworkKit.Callback
            public void onResult(boolean z) {
                if (z) {
                    Log.i(HttpClientUtils.TAG, "Network Kit init Success");
                } else {
                    Log.e(HttpClientUtils.TAG, "Network kit init failed");
                }
            }
        });
    }
}
