package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.iwy;
import defpackage.ixa;

/* loaded from: classes4.dex */
public final class HwAdapterWearMgrModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(ixa.class, "com.huawei.health.adapterwear.api.AdapterWearMgrApi");
        new ModuleProviderWrapper(new iwy(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "HwAdapterWearMgr";
    }
}
