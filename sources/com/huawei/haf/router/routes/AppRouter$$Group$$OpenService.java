package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.openservice.ui.OpenServiceActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$OpenService implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/OpenService/1.0/OpenServiceActivity", RouteMeta.build(RouteType.ACTIVITY, OpenServiceActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
