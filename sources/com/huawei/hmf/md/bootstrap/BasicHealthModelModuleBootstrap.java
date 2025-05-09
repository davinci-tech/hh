package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.asy;
import defpackage.asz;

/* loaded from: classes4.dex */
public final class BasicHealthModelModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(asy.class, "com.huawei.health.healthmodel.HealthLifeApi");
        new ModuleProviderWrapper(new asz(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "BasicHealthModel";
    }
}
