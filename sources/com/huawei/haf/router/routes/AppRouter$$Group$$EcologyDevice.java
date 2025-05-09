package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.ecologydevice.ecologydevicediscoveryfreeservice.EcologyDeviceDiscoveryFreePretreatmentService;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$EcologyDevice implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/EcologyDevice/service/ecologydevicediscoveryfree/pretreatment", RouteMeta.build(RouteType.HANDLER, EcologyDeviceDiscoveryFreePretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
