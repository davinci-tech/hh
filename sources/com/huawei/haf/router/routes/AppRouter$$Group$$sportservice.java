package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.model.RouteMeta;
import com.huawei.haf.router.facade.model.RouteType;
import com.huawei.haf.router.facade.template.RouteGroup;
import com.huawei.health.sportservice.download.business.AudioResDownloadImpl;
import com.huawei.health.sportservice.download.business.AudioResProviderImpl;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Group$$sportservice implements RouteGroup {
    @Override // com.huawei.haf.router.facade.template.RouteGroup
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD, RouteMeta.build(RouteType.HANDLER, AudioResDownloadImpl.class, -1, Integer.MIN_VALUE, null));
        map.put(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_PROVIDER, RouteMeta.build(RouteType.HANDLER, AudioResProviderImpl.class, -1, Integer.MIN_VALUE, null));
    }
}
