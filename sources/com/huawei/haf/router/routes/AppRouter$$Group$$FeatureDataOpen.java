package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.ui.thirdpartservice.activity.ThirdPartServiceActivity;
import com.huawei.ui.thirdpartservice.activity.komoot.KomootConnectActivity;
import com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticConnectActivity;
import com.huawei.ui.thirdpartservice.activity.strava.StravaConnectActivity;
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceActivity;
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceAuthorizeActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$FeatureDataOpen implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/FeatureDataOpen/KomootConnectActivity", RouteMeta.build(RouteType.ACTIVITY, KomootConnectActivity.class, -1, 16777216, null));
        map.put("/FeatureDataOpen/RuntasticConnectActivity", RouteMeta.build(RouteType.ACTIVITY, RuntasticConnectActivity.class, -1, 16777216, null));
        map.put("/FeatureDataOpen/StravaConnectActivity", RouteMeta.build(RouteType.ACTIVITY, StravaConnectActivity.class, -1, 16777216, null));
        map.put("/FeatureDataOpen/ThirdPartServiceActivity", RouteMeta.build(RouteType.ACTIVITY, ThirdPartServiceActivity.class, -1, 16777216, null));
        map.put("/FeatureDataOpen/WeChatDeviceActivity", RouteMeta.build(RouteType.ACTIVITY, WeChatDeviceActivity.class, -1, 16777216, null));
        map.put("/FeatureDataOpen/WeChatDeviceAuthorizeActivity", RouteMeta.build(RouteType.ACTIVITY, WeChatDeviceAuthorizeActivity.class, -1, 16777216, null));
    }
}
