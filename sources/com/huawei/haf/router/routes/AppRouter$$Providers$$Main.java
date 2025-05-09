package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.ServiceGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Providers$$Main implements ServiceGroup {
    @Override // com.huawei.haf.router.facade.template.ServiceGroup
    public void loadInto(Map<String, String> map) {
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/ThirdPartySyncData/service/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/Main/ThreeCirclePretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/Main/service/pretreatment");
    }
}
