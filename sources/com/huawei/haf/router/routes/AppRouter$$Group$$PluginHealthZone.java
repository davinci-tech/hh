package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.pluginhealthzone.FamilyHealthGlanceService;
import com.huawei.pluginhealthzone.FamilyHealthPretreatmentService;
import com.huawei.pluginhealthzone.activity.FamilyHealthTempActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginHealthZone implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginHealthZone/FamilyHealthTempActivity", RouteMeta.build(RouteType.ACTIVITY, FamilyHealthTempActivity.class, -1, 83886080, null));
        map.put("/PluginHealthZone/service/familyHealthGlanceService", RouteMeta.build(RouteType.HANDLER, FamilyHealthGlanceService.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginHealthZone/service/pretreatment", RouteMeta.build(RouteType.HANDLER, FamilyHealthPretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
