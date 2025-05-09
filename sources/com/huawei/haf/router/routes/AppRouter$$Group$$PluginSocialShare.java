package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.pluginsocialshare.activity.CustomizeShareActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginSocialShare implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginSocialShare/CustomizeShareActivity", RouteMeta.build(RouteType.ACTIVITY, CustomizeShareActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
