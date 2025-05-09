package com.huawei.hmf.md.bootstrap;

import com.huawei.health.impl.DistributedServiceModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dyq;

/* loaded from: classes4.dex */
public final class DistributedServiceModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dyq.class, "com.huawei.health.distributedservice.api.DistributedApi");
        new ModuleProviderWrapper(new DistributedServiceModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "DistributedService";
    }
}
