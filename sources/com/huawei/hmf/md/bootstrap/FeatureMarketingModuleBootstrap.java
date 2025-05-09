package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dlh;
import defpackage.dli;
import defpackage.dmv;

/* loaded from: classes4.dex */
public final class FeatureMarketingModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dlh.class, "com.huawei.health.marketing.api.MarketingApi");
        builder.add(dmv.class, "com.huawei.health.marketrouter.api.MarketRouterApi");
        new ModuleProviderWrapper(new dli(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "FeatureMarketing";
    }
}
