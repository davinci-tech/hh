package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.fcb;
import defpackage.fcc;

/* loaded from: classes4.dex */
public final class HuaweiHealthModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(fcb.class, "com.huawei.health.huaweihealth.HuaweiHealthApi");
        new ModuleProviderWrapper(new fcc(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HuaweiHealth";
    }
}
