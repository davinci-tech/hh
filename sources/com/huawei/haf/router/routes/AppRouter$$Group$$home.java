package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.ParamTypeMap;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.MainActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$home implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/home/main", RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, -1, Integer.MIN_VALUE, new ParamTypeMap().append(BleConstants.SPORT_TYPE, 3).append(Constants.HOME_TAB_NAME, 8)));
    }
}
