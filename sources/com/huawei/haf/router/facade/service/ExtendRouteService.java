package com.huawei.haf.router.facade.service;

import com.huawei.haf.router.Guidepost;
import com.huawei.haf.router.NaviCallback;
import com.huawei.haf.router.NaviPluginCallback;
import com.huawei.haf.router.facade.template.SingleServiceProvider;

/* loaded from: classes.dex */
public interface ExtendRouteService extends NaviPluginCallback, SingleServiceProvider {

    public interface RouteInfo {
        String getModuleOrGroup();

        int getPathExtras();

        String getRunningProcess();
    }

    boolean handleRoute(Guidepost guidepost, NaviCallback naviCallback, String str);

    boolean handleRunningProcess(Guidepost guidepost, NaviCallback naviCallback, String str);

    boolean handleStartComponent(Guidepost guidepost, NaviCallback naviCallback, String str);

    RouteInfo tryExtractRouteInfo(Guidepost guidepost);
}
