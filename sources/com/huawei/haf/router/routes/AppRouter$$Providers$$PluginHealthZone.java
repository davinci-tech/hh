package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.ServiceGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Providers$$PluginHealthZone implements ServiceGroup {
    @Override // com.huawei.haf.router.facade.template.ServiceGroup
    public void loadInto(Map<String, String> map) {
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/PluginHealthZone/service/familyHealthGlanceService");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/PluginHealthZone/service/pretreatment");
    }
}
