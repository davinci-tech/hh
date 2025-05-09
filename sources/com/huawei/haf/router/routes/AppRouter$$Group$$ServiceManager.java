package com.huawei.haf.router.routes;

import com.huawei.framework.servicemgr.HmfProviderService;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$ServiceManager implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/ServiceManager/service/serviceprovider", RouteMeta.build(RouteType.HANDLER, HmfProviderService.class, -1, Integer.MIN_VALUE, null));
    }
}
