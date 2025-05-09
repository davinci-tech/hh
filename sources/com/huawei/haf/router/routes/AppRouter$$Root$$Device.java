package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.haf.router.facade.template.RouteRoot;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Root$$Device implements RouteRoot {
    @Override // com.huawei.haf.router.facade.template.RouteRoot
    public void loadInto(Map<String, Class<? extends RouteGroup>> map) {
        map.put("Device", AppRouter$$Group$$Device.class);
        map.put("HeartRate", AppRouter$$Group$$HeartRate.class);
    }
}
