package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.ServiceGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Providers$$HWDeviceMgr implements ServiceGroup {
    @Override // com.huawei.haf.router.facade.template.ServiceGroup
    public void loadInto(Map<String, String> map) {
        map.put("com.huawei.haf.router.facade.service.PretreatmentService", "/DeviceScreenshot/service/pretreatment");
    }
}
