package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.dus;
import defpackage.dut;

/* loaded from: classes4.dex */
public final class LinkagePlatformModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(dut.class, "com.huawei.linkage.sportlinkage.LinkagePlatformApi");
        new ModuleProviderWrapper(new dus(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "LinkagePlatform";
    }
}
