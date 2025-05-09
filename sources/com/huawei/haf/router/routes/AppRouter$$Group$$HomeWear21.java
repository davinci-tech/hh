package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.ParamTypeMap;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.homewear21.home.WearHomeActivity;
import com.huawei.ui.homewear21.home.WearHomeHealthMonitoringActivity;
import com.huawei.ui.homewear21.home.hwofflinemapmgr.OfflineMapPretreatmentService;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$HomeWear21 implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/HomeWear21/WearHomeActivity", RouteMeta.build(RouteType.ACTIVITY, WearHomeActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HomeWear21/WearHomeHealthMonitoringActivity", RouteMeta.build(RouteType.ACTIVITY, WearHomeHealthMonitoringActivity.class, -1, 83886080, new ParamTypeMap().append("device_id", 8)));
        map.put("/HomeWear21/service/mapkit/pretreatment", RouteMeta.build(RouteType.HANDLER, OfflineMapPretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
