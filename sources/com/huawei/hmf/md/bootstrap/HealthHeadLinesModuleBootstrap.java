package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.oks;
import defpackage.okz;

/* loaded from: classes4.dex */
public final class HealthHeadLinesModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(oks.class, "com.huawei.health.healthheadlines.HealthHeadLinesJsApi");
        new ModuleProviderWrapper(new okz(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HealthHeadLines";
    }
}
