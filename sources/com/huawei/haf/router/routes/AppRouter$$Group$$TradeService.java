package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.tradeservice.activity.RedeemCodeScanActivity;
import com.huawei.health.tradeservice.activity.TradeOrderDetailActivity;
import com.huawei.health.tradeservice.activity.TradeOrderListActivity;
import com.huawei.health.tradeservice.activity.TradePayResultActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$TradeService implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/TradeService/RedeemCodeScanActivity", RouteMeta.build(RouteType.ACTIVITY, RedeemCodeScanActivity.class, -1, 16777216, null));
        map.put("/TradeService/TradeOrderDetailActivity", RouteMeta.build(RouteType.ACTIVITY, TradeOrderDetailActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/TradeService/TradeOrderListActivity", RouteMeta.build(RouteType.ACTIVITY, TradeOrderListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/TradeService/TradePayResultActivity", RouteMeta.build(RouteType.ACTIVITY, TradePayResultActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
