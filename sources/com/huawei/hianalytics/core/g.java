package com.huawei.hianalytics.core;

import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hianalytics.core.transport.net.TransportHandler;
import com.huawei.hms.network.base.common.RequestBodyProviders;
import com.huawei.hms.network.embedded.y;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.ResponseBody;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class g extends TransportHandler {
    public int d;
    public final boolean e;

    public final Response a(String str) {
        HttpClient.Builder readTimeout = new HttpClient.Builder().readTimeout(y.c);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metric_policy", this.d);
        } catch (Exception unused) {
            HiLog.w("NetworkKitHandler", "fail to build custom option");
        }
        HttpClient build = readTimeout.options(jSONObject.toString()).writeTimeout(y.c).connectTimeout(y.c).retryTimeOnConnectionFailure(0).build();
        Request.Builder method = build.newRequest().url(this.f3845a).method(str);
        Map<String, String> map = this.b;
        if (map != null) {
            for (String str2 : map.keySet()) {
                method.addHeader(str2, this.b.get(str2));
            }
        }
        if (!"GET".equals(str)) {
            method.addHeader("Conntent-Length", String.valueOf(this.c.length));
            method.requestBody(RequestBodyProviders.create("application/json; charset=UTF-8", this.c));
        }
        if (this.e) {
            HiLog.d("NetworkKitHandler", "enable http shortLink");
            method.addHeader("Connection", "close");
        }
        try {
            try {
                com.huawei.hms.network.httpclient.Response<ResponseBody> execute = build.newSubmit(method.build()).execute();
                if (execute.getBody() == null) {
                    Response response = new Response(execute.getCode(), "");
                    a(execute);
                    return response;
                }
                Response response2 = new Response(execute.getCode(), new String(execute.getBody().bytes(), StandardCharsets.UTF_8));
                a(execute);
                return response2;
            } catch (IOException e) {
                Response response3 = new Response(Response.Code.UNKNOWN_ERROR, e.getMessage());
                a((com.huawei.hms.network.httpclient.Response<ResponseBody>) null);
                return response3;
            }
        } catch (Throwable th) {
            a((com.huawei.hms.network.httpclient.Response<ResponseBody>) null);
            throw th;
        }
    }

    @Override // com.huawei.hianalytics.core.transport.net.TransportHandler
    public Response b() {
        return a("POST");
    }

    public final void a(com.huawei.hms.network.httpclient.Response<ResponseBody> response) {
        if (response == null) {
            return;
        }
        try {
            response.close();
        } catch (IOException unused) {
            HiLog.e("NetworkKitHandler", "NetworkKit IOException");
        }
    }

    @Override // com.huawei.hianalytics.core.transport.net.TransportHandler
    public Response a() {
        return a("GET");
    }

    public g(String str, Map<String, String> map, byte[] bArr, int i, boolean z) {
        super(str, map, bArr);
        this.d = i;
        this.e = z;
    }
}
