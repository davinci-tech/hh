package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.pluginachievement.manager.service.AchieveKaKaPretreatmentService;
import com.huawei.pluginachievement.ui.AchieveKaKaActivity;
import com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity;
import com.huawei.pluginachievement.ui.report.AchieveMonthReportActivity;
import com.huawei.pluginachievement.ui.report.AchieveWeekReportActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginAchievement implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginAchievement/AchieveKaKaActivity", RouteMeta.build(RouteType.ACTIVITY, AchieveKaKaActivity.class, -1, 83886080, null));
        map.put("/PluginAchievement/AchieveKaKaDetailActivity", RouteMeta.build(RouteType.ACTIVITY, AchieveKaKaDetailActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginAchievement/AchieveKakaExchangeActivity", RouteMeta.build(RouteType.ACTIVITY, AchieveKakaExchangeActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginAchievement/AchieveMonthReportActivity", RouteMeta.build(RouteType.ACTIVITY, AchieveMonthReportActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginAchievement/AchieveWeekReportActivity", RouteMeta.build(RouteType.ACTIVITY, AchieveWeekReportActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginAchievement/service/pretreatment", RouteMeta.build(RouteType.HANDLER, AchieveKaKaPretreatmentService.class, -1, Integer.MIN_VALUE, null));
    }
}
