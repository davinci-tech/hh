package com.huawei.hmf.md.bootstrap;

import com.huawei.health.hwhealthlinkage.impl.HWhealthLinkageModule;
import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dua;
import defpackage.dut;

/* loaded from: classes4.dex */
public final class HWhealthLinkageModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dut.class, "com.huawei.linkage.sportlinkage.LinkagePlatformApi");
        builder.add(dua.class, "com.huawei.health.healthlinkage.api.HWhealthLinkageApi");
        new ModuleProviderWrapper(new HWhealthLinkageModule(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HWhealthLinkage";
    }
}
