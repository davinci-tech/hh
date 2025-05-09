package com.huawei.hmf.md.bootstrap;

import com.huawei.hmf.repository.Repository;
import com.huawei.hmf.services.ApiSet;
import com.huawei.hmf.services.ModuleProviderWrapper;
import defpackage.mpi;
import defpackage.mpl;

/* loaded from: classes4.dex */
public final class PluginHealthZoneModuleBootstrap {
    public static final void register(Repository repository) {
        ApiSet.Builder builder = ApiSet.builder();
        builder.add(mpi.class, "com.huawei.health.pluginhealthzone.FamilyHealthZoneApi");
        new ModuleProviderWrapper(new mpl(), 1).bootstrap(repository, name(), builder.build());
    }

    public static final String name() {
        return "PluginHealthZone";
    }
}
