package com.huawei.maps.offlinedata.network;

import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.framework.network.restclient.hwhttp.url.HttpUrl;
import com.huawei.hms.mlsdk.common.AgConnectInfo;
import com.huawei.hms.network.base.common.MediaType;
import com.huawei.hms.network.base.common.ResponseBodyProviders;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Request;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.ResponseBody;
import com.huawei.maps.offlinedata.logpush.dto.a;
import com.huawei.maps.offlinedata.service.cloud.utils.f;
import com.huawei.maps.offlinedata.utils.e;
import com.huawei.maps.offlinedata.utils.g;
import com.huawei.secure.android.common.util.SafeString;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class a extends Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, String> f6457a = new HashMap<String, String>() { // from class: com.huawei.maps.offlinedata.network.a.1
        {
            put("/open-service/v1/offline-data/authenticate", "authenticate");
            put("/open-service/v1/offline-data/getWorldMap", "getWorldMap");
            put("/open-service/v1/offline-data/getCountryMapList", "getCountryMapList");
            put("/open-service/v1/offline-data/getRegionMapList", "getRegionMapList");
            put("/open-service/v1/offline-data/getDownloadUrl", "getDownloadUrl");
            put("/open-service/v1/offline-data/getRegionsByLatLngs", "getRegionsByLatLngs");
        }
    };

    @Override // com.huawei.hms.network.httpclient.Interceptor
    public Response<ResponseBody> intercept(Interceptor.Chain chain) throws IOException {
        ResponseBody errorBody;
        HttpUrl httpUrl = new HttpUrl(chain.request().getUrl());
        String str = f6457a.get(httpUrl.getURL().getPath());
        com.huawei.maps.offlinedata.logpush.dto.a c = a.C0167a.b().a(str).c();
        com.huawei.maps.offlinedata.network.converter.b a2 = a(chain);
        Request.Builder newBuilder = chain.request().newBuilder();
        String a3 = com.huawei.maps.offlinedata.service.cloud.utils.d.a(str, com.huawei.maps.offlinedata.service.cloud.utils.b.a(com.huawei.maps.offlinedata.utils.a.a()));
        c.a(a3);
        String a4 = com.huawei.maps.offlinedata.service.cloud.utils.a.a(com.huawei.maps.offlinedata.utils.a.a(), AgConnectInfo.AgConnectKey.API_KEY);
        newBuilder.addHeader("offlineClientVersion", String.valueOf(f.a()));
        newBuilder.addHeader("Authorization", HealthEngineRequestManager.PREFIX_ACCESS_TOKEN + a4);
        if ("GET".equals(chain.request().getMethod())) {
            a(newBuilder, a3);
        } else if ("POST".equals(chain.request().getMethod())) {
            newBuilder.addHeader("Content-Type", "application/json");
            a(a2, newBuilder, a3);
        }
        Request build = newBuilder.url(httpUrl.getUrl()).build();
        g.b("CommonInterceptor", String.format(Locale.ENGLISH, "start: [%s]", httpUrl.getURL().getPath()));
        long currentTimeMillis = System.currentTimeMillis();
        Response<ResponseBody> proceed = chain.proceed(build);
        g.b("CommonInterceptor", String.format(Locale.ENGLISH, "end: [%s] result：%s [cost time]: %s", httpUrl.getURL().getPath(), Boolean.valueOf(proceed.isOK()), Integer.valueOf((int) (System.currentTimeMillis() - currentTimeMillis))));
        if (proceed.isSuccessful()) {
            errorBody = proceed.getBody();
        } else {
            errorBody = proceed.getErrorBody();
        }
        if (errorBody == null) {
            a(proceed, c);
            return proceed;
        }
        ByteArrayOutputStream a5 = a(errorBody.getInputStream());
        try {
            if (a5 != null) {
                ResponseBody create = ResponseBodyProviders.create(MediaType.parse(errorBody.getContentType()), errorBody.getContentLength(), new ByteArrayInputStream(a5.toByteArray()));
                ResponseBody create2 = ResponseBodyProviders.create(MediaType.parse(errorBody.getContentType()), errorBody.getContentLength(), new ByteArrayInputStream(a5.toByteArray()));
                Response<ResponseBody> build2 = proceed.createBuilder().body(create).build();
                a(proceed.createBuilder().body(create2).build(), c);
                if (a5 != null) {
                    a5.close();
                }
                return build2;
            }
            a(proceed, c);
            if (a5 != null) {
                a5.close();
            }
            return proceed;
        } catch (Throwable th) {
            if (a5 != null) {
                try {
                    a5.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public void a(Response<ResponseBody> response, com.huawei.maps.offlinedata.logpush.dto.a aVar) {
        String str;
        ResponseBody body = response.getBody();
        str = "";
        if (body != null) {
            try {
                JsonObject a2 = com.huawei.maps.offlinedata.utils.d.a(new String(body.bytes(), StandardCharsets.UTF_8));
                str = a2.has("returnCode") ? a2.get("returnCode").getAsString() : "";
                e.a("CommonInterceptor", response);
            } catch (JsonParseException | IOException unused) {
                e.a("CommonInterceptor", response);
            } catch (Throwable th) {
                e.a("CommonInterceptor", response);
                throw th;
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = String.valueOf(response.getCode());
        }
        g.a("CommonInterceptor", "response body returnCode:" + str);
        aVar.b(str);
        com.huawei.maps.offlinedata.logpush.b.a(aVar);
    }

    private void a(Request.Builder builder, String str) {
        builder.addHeader("requestId", str);
    }

    private com.huawei.maps.offlinedata.network.converter.b a(Interceptor.Chain chain) {
        JSONObject jSONObject = null;
        if (chain.request().getBody() == null || TextUtils.isEmpty(chain.request().getBody().contentType())) {
            return null;
        }
        com.huawei.maps.offlinedata.network.converter.b bVar = (com.huawei.maps.offlinedata.network.converter.b) com.huawei.maps.offlinedata.utils.d.a(chain.request().getBody().contentType(), com.huawei.maps.offlinedata.network.converter.b.class);
        if (bVar != null) {
            try {
                jSONObject = new JSONObject(new String(bVar.b(), a(bVar.a())));
            } catch (UnsupportedEncodingException | JSONException unused) {
                g.d("CommonInterceptor", "resetContentType error");
            }
            if (jSONObject != null) {
                chain.request().newBuilder().requestBody(com.huawei.maps.offlinedata.network.converter.a.b("application/json; charset=utf-8", String.valueOf(jSONObject).getBytes(StandardCharsets.UTF_8)));
            }
        }
        return bVar;
    }

    private void a(com.huawei.maps.offlinedata.network.converter.b bVar, Request.Builder builder, String str) {
        if (bVar != null) {
            try {
                JSONObject jSONObject = new JSONObject(new String(bVar.b(), a(bVar.a())));
                if (!jSONObject.has("requestId")) {
                    jSONObject.put("requestId", str);
                }
                builder.requestBody(com.huawei.maps.offlinedata.network.converter.a.b("application/json; charset=utf-8", String.valueOf(jSONObject).getBytes(StandardCharsets.UTF_8)));
            } catch (UnsupportedEncodingException | JSONException e) {
                g.d("CommonInterceptor", e.getMessage());
            }
        }
    }

    private String a(String str) {
        int indexOf;
        String name = StandardCharsets.UTF_8.name();
        return (str == null || (indexOf = str.indexOf("charset=")) == -1) ? name : SafeString.substring(str, indexOf + 8);
    }

    private ByteArrayOutputStream a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read > -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            byteArrayOutputStream.flush();
                            e.a("CommonInterceptor", byteArrayOutputStream);
                            return byteArrayOutputStream;
                        }
                    }
                } catch (IOException unused) {
                    g.d("CommonInterceptor", "IOException");
                    e.a("CommonInterceptor", byteArrayOutputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                e.a("CommonInterceptor", byteArrayOutputStream2);
                throw th;
            }
        } catch (IOException unused2) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            e.a("CommonInterceptor", byteArrayOutputStream2);
            throw th;
        }
    }
}
