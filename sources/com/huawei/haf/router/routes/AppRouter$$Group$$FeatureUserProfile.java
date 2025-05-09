package com.huawei.haf.router.routes;

import com.huawei.featureuserprofile.feedback.FeedbackPretreatmentService;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$FeatureUserProfile implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/FeatureUserProfile/service/feedback", RouteMeta.build(RouteType.HANDLER, FeedbackPretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
