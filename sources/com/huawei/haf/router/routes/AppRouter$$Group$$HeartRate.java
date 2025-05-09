package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.device.activity.heartrate.HeartRatePretreatmentService;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$HeartRate implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/HeartRate/service/pretreatment", RouteMeta.build(RouteType.HANDLER, HeartRatePretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
