package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.ServiceGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Providers$$HuaweiHealth implements ServiceGroup {
    @Override // com.huawei.haf.router.facade.template.ServiceGroup
    public void loadInto(Map<String, String> map) {
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/Arkui/service/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/accountCenter/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/achievement/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/column/pretreatment");
        map.put("com.huawei.haf.router.facade.service.ExtendRouteService", "/HuaweiHealth/service/customroute");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/fa/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/groupqrcode/pretreatment");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/linkagelogin/pretreatment");
        map.put("com.huawei.haf.router.facade.service.ExtrasFormatService", "/HuaweiHealth/service/router/extras");
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/HuaweiHealth/service/trade/pretreatment");
    }
}
