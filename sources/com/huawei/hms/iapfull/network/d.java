package com.huawei.hms.iapfull.network;

import android.content.Context;
import com.huawei.hms.framework.network.restclient.RestClient;
import com.huawei.hms.framework.network.restclient.hwhttp.HttpClient;
import com.huawei.hms.iapfull.k0;
import com.huawei.hms.iapfull.l0;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.network.embedded.y;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static volatile IAPFullQueryResultService f4734a;
    private static volatile IAPFullQueryResultService b;
    private static volatile IAPFullQueryResultService c;
    private static volatile IAPFullQueryResultService d;

    public static IAPFullQueryResultService d(Context context, String str) {
        y0.b("QueryResultClient", "getWxWithholdResultService");
        if (d == null) {
            RestClient build = f.a(context, str, y.c).build();
            HttpClient.Builder newBuilder = build.getHttpClient().newBuilder();
            newBuilder.addInterceptor(new l0());
            d = a(build, newBuilder);
        }
        return d;
    }

    public static IAPFullQueryResultService c(Context context, String str) {
        y0.b("QueryResultClient", "getWxPayResultService");
        if (c == null) {
            RestClient build = f.a(context, str, y.c).build();
            HttpClient.Builder newBuilder = build.getHttpClient().newBuilder();
            newBuilder.addInterceptor(new k0());
            c = a(build, newBuilder);
        }
        return c;
    }

    public static IAPFullQueryResultService b(Context context, String str) {
        y0.b("QueryResultClient", "getAlipayWithholdResultService");
        if (b == null) {
            RestClient build = f.a(context, str, 20000).build();
            b = a(build, build.getHttpClient().newBuilder());
        }
        return b;
    }

    public static IAPFullQueryResultService a(RestClient restClient, HttpClient.Builder builder) {
        return (IAPFullQueryResultService) restClient.newBuilder().httpClient(builder.build()).build().create(IAPFullQueryResultService.class);
    }

    public static IAPFullQueryResultService a(Context context, String str) {
        y0.b("QueryResultClient", "getAlipayPayResultService");
        if (f4734a == null) {
            RestClient build = f.a(context, str, 20000).build();
            HttpClient.Builder newBuilder = build.getHttpClient().newBuilder();
            newBuilder.addInterceptor(new k0());
            f4734a = a(build, newBuilder);
        }
        return f4734a;
    }
}
