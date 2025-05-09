package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.InterceptorGroup;
import com.huawei.haf.router.facade.template.InterceptorHandler;
import com.huawei.health.routeradapter.HealthLoginInterceptorService;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Interceptors$$HuaweiHealth implements InterceptorGroup {
    @Override // com.huawei.haf.router.facade.template.InterceptorGroup
    public void loadInto(Map<Integer, Class<? extends InterceptorHandler>> map) {
        map.put(1, HealthLoginInterceptorService.class);
    }
}
