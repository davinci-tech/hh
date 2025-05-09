package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.nhe;
import defpackage.nhh;

/* loaded from: classes4.dex */
public final class StressGameModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(nhe.class, "com.huawei.health.algorithm.api.StressGameApi");
        new ModuleProviderWrapper(new nhh(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "StressGame";
    }
}
