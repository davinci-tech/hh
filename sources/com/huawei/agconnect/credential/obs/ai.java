package com.huawei.agconnect.credential.obs;

import android.content.Context;
import com.huawei.agconnect.credential.Server;
import java.util.Collections;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes8.dex */
public class ai {

    /* renamed from: a, reason: collision with root package name */
    private final ag f1746a;
    private Boolean b = false;
    private OkHttpClient c;

    public Boolean c() {
        return this.b;
    }

    public ag b() {
        return this.f1746a;
    }

    public OkHttpClient a() {
        return this.c;
    }

    private void a(Context context) {
        this.c = new ae(context, Collections.singletonList(new Interceptor() { // from class: com.huawei.agconnect.credential.obs.ai.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) {
                Request request = chain.request();
                String str = request.url().scheme() + "://" + request.url().host();
                if (!Server.GW.equals(str)) {
                    return chain.proceed(request);
                }
                Request build = request.newBuilder().url(request.url().getUrl().replace(str, "https://" + ai.this.f1746a.c())).build();
                if (!ai.this.b.booleanValue()) {
                    ai.this.b = true;
                }
                return chain.proceed(build);
            }
        }), true).a();
    }

    public ai(Context context, ag agVar) {
        this.f1746a = agVar;
        a(context);
    }
}
