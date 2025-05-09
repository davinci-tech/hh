package com.huawei.hms.iapfull.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.RestClientGlobalInstance;
import com.huawei.hms.framework.network.restclient.converter.gson.GsonConverterFactory;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import com.huawei.hms.iapfull.j0;
import com.huawei.hms.network.embedded.y;
import com.huawei.secure.android.common.ssl.SecureSSLSocketFactory;
import java.io.ByteArrayInputStream;

/* loaded from: classes4.dex */
public class f {
    public static RestClient.Builder a(Context context, String str, int i) {
        RestClientGlobalInstance.getInstance().init(context);
        if (i <= 0) {
            i = y.c;
        }
        return new RestClient.Builder(context).baseUrl(str).httpClient(new HttpClient.Builder().connectTimeout(i).readTimeout(i).retryTimeOnConnectionFailure(1).addInterceptor(new j0()).hostnameVerifier(SecureSSLSocketFactory.STRICT_HOSTNAME_VERIFIER).build()).addConverterFactory(GsonConverterFactory.create()).validateEagerly(true);
    }

    public static Response a(Response response, byte[] bArr) {
        return new Response.Builder().body(new ResponseBody.Builder().contentLength((int) r0.getContentLength()).contentType(response.getBody().getContentType()).inputStream(new ByteArrayInputStream(bArr)).charSet("UTF-8").build()).code(response.getCode()).headers(response.getHeaders()).message(response.getMessage()).build();
    }
}
