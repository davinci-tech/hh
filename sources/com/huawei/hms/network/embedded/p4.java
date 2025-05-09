package com.huawei.hms.network.embedded;

import android.content.Context;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Interceptor;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public abstract class p4 {

    /* renamed from: a, reason: collision with root package name */
    public static volatile p4 f5411a = null;
    public static final String b = "https://";
    public static final String c = "GET";
    public static volatile HttpClient d;

    public abstract LinkedHashMapPack a(String str);

    public abstract n5 a(String str, List<InetAddress> list);

    public abstract t5 a(boolean z);

    public abstract z4 a(long j, long j2);

    public abstract void a(Context context);

    public abstract void a(Interceptor.Chain chain);

    public abstract boolean a();

    public abstract String b();

    public abstract void b(boolean z);

    public abstract boolean b(long j, long j2);

    public abstract Map<String, Integer> c();

    public abstract void c(boolean z);

    public abstract void d();

    public static p4 f() {
        if (f5411a == null) {
            synchronized (p4.class) {
                if (f5411a == null) {
                    f5411a = new q4();
                }
            }
        }
        return f5411a;
    }

    public static HttpClient e() {
        HttpClient httpClient;
        synchronized (p4.class) {
            if (d == null) {
                d = new HttpClient.Builder().retryTimeOnConnectionFailure(0).build();
            }
            httpClient = d;
        }
        return httpClient;
    }
}
