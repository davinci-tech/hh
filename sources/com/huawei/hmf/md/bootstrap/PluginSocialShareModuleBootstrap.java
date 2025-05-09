package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.pluginsocialshare.impl.SocialShareCenterImpl;
import com.huawei.pluginsocialshare.impl.SocialShareModule;

/* loaded from: classes4.dex */
public final class PluginSocialShareModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(SocialShareCenterImpl.class, "com.huawei.health.socialshare.api.SocialShareCenterApi");
        new ModuleProviderWrapper(new SocialShareModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginSocialShare";
    }
}
