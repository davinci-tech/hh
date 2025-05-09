package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.haf.router.facade.template.RouteRoot;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Root$$HomeWear21 implements RouteRoot {
    @Override // com.huawei.haf.router.facade.template.RouteRoot
    public void loadInto(Map<String, Class<? extends RouteGroup>> map) {
        map.put("HealthMonitor", AppRouter$$Group$$HealthMonitor.class);
        map.put(AppRoute$$Info$$HuaweiHealth.M_47, AppRouter$$Group$$HomeWear21.class);
    }
}
