package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.haf.router.facade.template.RouteRoot;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Root$$PluginMotionTrack implements RouteRoot {
    @Override // com.huawei.haf.router.facade.template.RouteRoot
    public void loadInto(Map<String, Class<? extends RouteGroup>> map) {
        map.put(AppRoute$$Info$$HuaweiHealth.M_51, AppRouter$$Group$$PluginMotionTrack.class);
    }
}
