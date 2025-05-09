package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import com.huawei.hwhealthdatamgr.impl.HealthDataMgrModule;
import defpackage.kpq;

/* loaded from: classes4.dex */
public final class HWHealthDataMgrModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(kpq.class, "com.huawei.health.healthdatamgr.api.HealthDataMgrApi");
        new ModuleProviderWrapper(new HealthDataMgrModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HWHealthDataMgr";
    }
}
