package com.huawei.hms.framework.network.grs;

import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.restclient.hwhttp.Interceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.Request;
import com.huawei.hms.framework.network.restclient.hwhttp.Response;
import com.huawei.hms.framework.network.restclient.hwhttp.plugin.PluginInterceptor;
import com.huawei.hms.framework.network.restclient.hwhttp.url.HttpUrl;

@Deprecated
/* loaded from: classes9.dex */
public class GrsInterceptor implements PluginInterceptor {
    private static final String TAG = "GrsInterceptor";

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.plugin.PluginInterceptor
    @Deprecated
    public String pluginName() {
        return TAG;
    }

    @Override // com.huawei.hms.framework.network.restclient.hwhttp.Interceptor
    @Deprecated
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        String url = request.getUrl().getUrl();
        if (!GrsManager.isGRSSchema(url)) {
            return chain.proceed(request);
        }
        String str = TAG;
        Logger.v(str, "request url is grs schema.");
        GrsManager.getInstance();
        Request build = request.newBuilder().url(new HttpUrl(GrsManager.parseGrs(url))).build();
        Logger.v(str, "origin url is grs schema and by intercepted,and now request is:%s", build.toString());
        return chain.proceed(build);
    }
}
