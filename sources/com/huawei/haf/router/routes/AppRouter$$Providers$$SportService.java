package com.huawei.haf.router.routes;

import com.huawei.haf.router.facade.template.ServiceGroup;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import java.util.Map;

/* loaded from: classes.dex */
public final class AppRouter$$Providers$$SportService implements ServiceGroup {
    @Override // com.huawei.haf.router.facade.template.ServiceGroup
    public void loadInto(Map<String, String> map) {
        map.put("com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface", AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD);
        map.put("com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface", AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_PROVIDER);
    }
}
