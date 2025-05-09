package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.ikg;
import defpackage.iky;
import defpackage.inl;
import defpackage.ipm;

/* loaded from: classes4.dex */
public final class HealthKitModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(ipm.class, "com.huawei.hihealthservice.hmsauth.HmsAuthApi");
        builder.add(inl.class, "com.huawei.hihealthservice.HiHealthKitApi");
        builder.add(ikg.class, "com.huawei.hihealthservice.healthtrend.HealthTrendApi");
        new ModuleProviderWrapper(new iky(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HealthKit";
    }
}
