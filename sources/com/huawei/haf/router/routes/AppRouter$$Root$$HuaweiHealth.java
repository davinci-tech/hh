package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.haf.router.facade.template.RouteRoot;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Root$$HuaweiHealth implements RouteRoot {
    @Override // com.huawei.haf.router.facade.template.RouteRoot
    public void loadInto(Map<String, Class<? extends RouteGroup>> map) {
        map.put("HuaweiHealth", AppRouter$$Group$$HuaweiHealth.class);
        map.put("home", AppRouter$$Group$$home.class);
    }
}
