package com.huawei.haf.router.routes;

import com.huawei.featureuserprofile.todo.activity.TodoCardActivity;
import com.huawei.featureuserprofile.todo.activity.TodoListActivity;
import com.huawei.featureuserprofile.todo.activity.TodoSettingActivity;
import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$todoTask implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put("/todoTask/TodoCardActivity", RouteMeta.build(RouteType.ACTIVITY, TodoCardActivity.class, -1, 16777216, null));
        map.put("/todoTask/TodoListActivity", RouteMeta.build(RouteType.ACTIVITY, TodoListActivity.class, -1, Integer.MIN_VALUE, null));
        map.put("/todoTask/TodoSettingActivity", RouteMeta.build(RouteType.ACTIVITY, TodoSettingActivity.class, -1, 16777216, null));
    }
}
