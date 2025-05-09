package com.huawei.haf.router.routes;

import com.huawei.featureuserprofile.asset.MyAssetsActivity;
import com.huawei.featureuserprofile.award.ui.MyAwardActivity;
import com.huawei.featureuserprofile.award.ui.WriteDeliveryInfoActivity;
import com.huawei.featureuserprofile.interest.InterestAndConcernActivity;
import com.huawei.featureuserprofile.me.MyTargetActivity;
import com.huawei.featureuserprofile.me.UserInfoActivity;
import com.huawei.featureuserprofile.route.activity.EditRouteActivity;
import com.huawei.featureuserprofile.route.activity.MyRouteActivity;
import com.huawei.featureuserprofile.route.activity.RouteExportActivity;
import com.huawei.featureuserprofile.sos.activity.EditContactActivity;
import com.huawei.featureuserprofile.sos.activity.EditInfoActivity;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$HWUserProfileMgr implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/HWUserProfileMgr/EditContactActivity", RouteMeta.build(RouteType.ACTIVITY, EditContactActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/EditInfoActivity", RouteMeta.build(RouteType.ACTIVITY, EditInfoActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/EditRouteActivity", RouteMeta.build(RouteType.ACTIVITY, EditRouteActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/InterestAndConcernActivity", RouteMeta.build(RouteType.ACTIVITY, InterestAndConcernActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/MyAssets", RouteMeta.build(RouteType.ACTIVITY, MyAssetsActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/MyAwardActivity", RouteMeta.build(RouteType.ACTIVITY, MyAwardActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/MyRouteActivity", RouteMeta.build(RouteType.ACTIVITY, MyRouteActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/MyTargetActivity", RouteMeta.build(RouteType.ACTIVITY, MyTargetActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/RouteExportActivity", RouteMeta.build(RouteType.ACTIVITY, RouteExportActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/UserInfoActivity", RouteMeta.build(RouteType.ACTIVITY, UserInfoActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/HWUserProfileMgr/WriteDeliveryInfoActivity", RouteMeta.build(RouteType.ACTIVITY, WriteDeliveryInfoActivity.class, -1, Integer.MIN_VALUE, null));
    }
}
