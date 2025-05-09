package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.suggestion.service.FitnessPretreatmentServiceImpl;
import com.huawei.health.suggestion.ui.fitness.activity.CoachActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionDetailActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessResultActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessTopicActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessTopicListActivity;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.TrainDetail;
import com.huawei.health.suggestion.ui.plan.activity.AiPlanActivity;
import com.huawei.health.suggestion.ui.plan.activity.PlanListActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginFitnessAdvice implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginFitnessAdvice/AiPlanActivity", RouteMeta.build(RouteType.ACTIVITY, AiPlanActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/CoachActivity", RouteMeta.build(RouteType.ACTIVITY, CoachActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/FitnessActionDetailActivity", RouteMeta.build(RouteType.ACTIVITY, FitnessActionDetailActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/FitnessResultActivity", RouteMeta.build(RouteType.ACTIVITY, FitnessResultActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/FitnessTopicActivity", RouteMeta.build(RouteType.ACTIVITY, FitnessTopicActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/FitnessTopicListActivity", RouteMeta.build(RouteType.ACTIVITY, FitnessTopicListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/PlanListActivity", RouteMeta.build(RouteType.ACTIVITY, PlanListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/SportAllCourseActivity", RouteMeta.build(RouteType.ACTIVITY, SportAllCourseActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/TrainDetail", RouteMeta.build(RouteType.ACTIVITY, TrainDetail.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginFitnessAdvice/service/pretreatment", RouteMeta.build(RouteType.HANDLER, FitnessPretreatmentServiceImpl.class, -1, Integer.MIN_VALUE, null));
    }
}
