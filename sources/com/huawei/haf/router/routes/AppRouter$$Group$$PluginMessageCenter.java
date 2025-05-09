package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.pluginmessagecenter.activity.DispatchSkipEventActivity;
import com.huawei.pluginmessagecenter.activity.MessageCenterActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginMessageCenter implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginMessageCenter/DispatchSkipEventActivity", RouteMeta.build(RouteType.ACTIVITY, DispatchSkipEventActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginMessageCenter/MessageCenterActivity", RouteMeta.build(RouteType.ACTIVITY, MessageCenterActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
