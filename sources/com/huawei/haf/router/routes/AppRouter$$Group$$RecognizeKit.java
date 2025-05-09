package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.recognizekit.api.HealthRecognizeActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$RecognizeKit implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/RecognizeKit/HealthRecognizeActivity", RouteMeta.build(RouteType.ACTIVITY, HealthRecognizeActivity.class, -1, 16777216, null));
    }
}
