package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.main.stories.member.MemberRelayActivity;
import com.huawei.ui.main.stories.member.MemberTypeSelectActivity;
import com.huawei.ui.main.stories.soical.activity.FunctionMenuActivity;
import com.huawei.ui.main.stories.soical.activity.SocialAssessmentActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$OperationBundle implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/OperationBundle/FunctionMenuActivity", RouteMeta.build(RouteType.ACTIVITY, FunctionMenuActivity.class, -1, 16777216, null));
        map.put("/OperationBundle/MemberRelayActivity", RouteMeta.build(RouteType.ACTIVITY, MemberRelayActivity.class, -1, 16777216, null));
        map.put("/OperationBundle/MemberTypeSelectActivity", RouteMeta.build(RouteType.ACTIVITY, MemberTypeSelectActivity.class, -1, 16777216, null));
        map.put("/OperationBundle/SocialAssessmentActivity", RouteMeta.build(RouteType.ACTIVITY, SocialAssessmentActivity.class, -1, 16777216, null));
    }
}
