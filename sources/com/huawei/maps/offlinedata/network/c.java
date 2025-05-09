package com.huawei.maps.offlinedata.network;

import android.util.ArrayMap;
import com.google.gson.JsonObject;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.embedded.y;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.Converter;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.converter.gson.GsonConverterFactory;
import com.huawei.maps.offlinedata.utils.g;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile c f6458a;
    private final ThreadPoolExecutor b = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final ArrayMap<Integer, RestClient> c;
    private final ArrayMap<Integer, HttpClient> d;

    private c() {
        ArrayMap<Integer, RestClient> arrayMap = new ArrayMap<>();
        this.c = arrayMap;
        ArrayMap<Integer, HttpClient> arrayMap2 = new ArrayMap<>();
        this.d = arrayMap2;
        NetworkKit.init(com.huawei.maps.offlinedata.utils.a.a(), new NetworkKit.Callback() { // from class: com.huawei.maps.offlinedata.network.c.1
            @Override // com.huawei.hms.network.NetworkKit.Callback
            public void onResult(boolean z) {
                if (z) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("enable_ipv6", (Boolean) true);
                    NetworkKit.getInstance().setOptions(jsonObject.toString());
                    g.a("MapNetUtils", "NetworkKit init success");
                    return;
                }
                g.d("MapNetUtils", "NetworkKit init failed");
            }
        });
        HttpClient build = new HttpClient.Builder().readTimeout(10000).writeTimeout(y.c).connectTimeout(5000).callTimeout(y.c).addInterceptor((Interceptor) new a()).build();
        arrayMap.put(0, new RestClient.Builder().httpClient(build).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).build());
        arrayMap2.put(0, build);
    }

    public static c a() {
        if (f6458a == null) {
            synchronized (c.class) {
                if (f6458a == null) {
                    f6458a = new c();
                }
            }
        }
        return f6458a;
    }

    public <Service> Service a(Class<Service> cls) {
        return (Service) this.c.get(0).create(cls);
    }

    public <Result extends d> void a(final Submit<Result> submit, final b<Result> bVar) {
        this.b.execute(new Runnable() { // from class: com.huawei.maps.offlinedata.network.c$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Submit.this.enqueue(bVar);
            }
        });
    }

    public static String a(String str) {
        return com.huawei.maps.offlinedata.service.config.a.a().i() + str;
    }
}
