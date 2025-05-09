package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.activity.WifiDeviceAuthRequestListActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginDevice implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginDevice/DeviceMainActivity", RouteMeta.build(RouteType.ACTIVITY, DeviceMainActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginDevice/WifiDeviceAuthRequestListActivity", RouteMeta.build(RouteType.ACTIVITY, WifiDeviceAuthRequestListActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
