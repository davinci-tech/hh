package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.healthcloud.plugintrack.golf.device.GolfPretreatmentService;
import com.huawei.healthcloud.plugintrack.impl.MotionTrackPretreatmentServiceImpl;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.HistoricalRoutesActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.RunningRouteListActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackMainMapActivity;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$PluginMotionTrack implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/PluginMotionTrack/HistoricalRoutesActivity", RouteMeta.build(RouteType.ACTIVITY, HistoricalRoutesActivity.class, -1, 16777216, null));
        map.put("/PluginMotionTrack/RunningRouteDetailMapActivity", RouteMeta.build(RouteType.ACTIVITY, ClockingRankActivity.class, -1, 16777216, null));
        map.put("/PluginMotionTrack/RunningRouteListActivity", RouteMeta.build(RouteType.ACTIVITY, RunningRouteListActivity.class, -1, 16777216, null));
        map.put("/PluginMotionTrack/TrackDetailActivity", RouteMeta.build(RouteType.ACTIVITY, TrackDetailActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginMotionTrack/TrackMainMapActivity", RouteMeta.build(RouteType.ACTIVITY, TrackMainMapActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginMotionTrack/service/golf/pretreatment", RouteMeta.build(RouteType.HANDLER, GolfPretreatmentService.class, -1, Integer.MIN_VALUE, null));
        map.put("/PluginMotionTrack/service/track/pretreatment", RouteMeta.build(RouteType.HANDLER, MotionTrackPretreatmentServiceImpl.class, -1, Integer.MIN_VALUE, null));
    }
}
