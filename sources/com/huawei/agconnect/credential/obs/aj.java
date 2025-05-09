package com.huawei.agconnect.credential.obs;

import android.text.TextUtils;
import androidx.webkit.ProxyConfig;
import com.huawei.agconnect.common.api.BackendService;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.credential.Server;
import com.huawei.hms.network.embedded.g2;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes2.dex */
final class aj implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    private static final String f1748a = "UrlInterceptorV2";
    private List<ag> b;

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        z.a().a(request.header("sdkServiceName"));
        if (!Server.GW.equals(request.url().scheme() + "://" + request.url().host()) || this.b.isEmpty()) {
            return chain.proceed(request);
        }
        UnknownHostException unknownHostException = null;
        int i = 0;
        UnknownHostException unknownHostException2 = null;
        Response response = null;
        while (true) {
            if (i >= this.b.size()) {
                unknownHostException = unknownHostException2;
                break;
            }
            ag agVar = this.b.get(i);
            if (!TextUtils.isEmpty(agVar.e())) {
                return a(chain, agVar.c());
            }
            String a2 = agVar.a();
            String b = agVar.b();
            Response a3 = a(chain, a2);
            if (a3 == null) {
                response = a(chain, b);
                if (response != null) {
                    agVar.a(b, true);
                    break;
                }
                unknownHostException2 = new UnknownHostException("no host can access");
                i++;
            } else {
                agVar.a(a2, false);
                response = a3;
                break;
            }
        }
        if (unknownHostException == null) {
            return response;
        }
        throw unknownHostException;
    }

    private Response a(Interceptor.Chain chain, String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Request request = chain.request();
        String[] split = str.split(":");
        int length = split.length;
        int i = g2.n;
        try {
            if (length == 2) {
                try {
                    i = Integer.parseInt(split[1]);
                    str2 = split[0];
                } catch (NumberFormatException unused) {
                    Logger.e(f1748a, "port is error:" + i + ", use default 443");
                }
                return chain.proceed(request.newBuilder().url(request.url().newBuilder().scheme(ProxyConfig.MATCH_HTTPS).host(str2).port(i).build()).build());
            }
            return chain.proceed(request.newBuilder().url(request.url().newBuilder().scheme(ProxyConfig.MATCH_HTTPS).host(str2).port(i).build()).build());
        } catch (IOException e) {
            if (!(e instanceof UnknownHostException)) {
                throw e;
            }
            Logger.e(f1748a, "UnknownHostException" + str);
            return null;
        }
        str2 = str;
    }

    public aj(String str, String str2) {
        this.b = Collections.singletonList(new ag(str, str2));
    }

    public aj(BackendService.Options options) {
        this.b = z.a().a(options.getApp());
    }
}
